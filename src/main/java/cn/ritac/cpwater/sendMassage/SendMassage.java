package cn.ritac.cpwater.sendMassage;
import cn.jsms.api.SendSMSResult;
import cn.jsms.api.common.model.BatchSMSPayload;
import cn.jsms.api.common.model.BatchSMSResult;
import cn.jsms.api.common.model.RecipientPayload;
import cn.jsms.api.common.model.SMSPayload;
import cn.ritac.cpwater.web.controller.api.BaseController;
import org.slf4j.LoggerFactory;
import cn.jsms.api.common.SMSClient;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:FanJS
 * @Date:2019-8-23 11:46
 */
public class SendMassage{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(SendMassage.class);
    //极光平台参数 masterSecret    appkey
    KetSecret ks=new KetSecret();
    private SMSClient client=new SMSClient(ks.getMASTER_SECRET(),
            ks.getAPP_KEY());

    //发送验证码
    public void sendSMS(String phone) {
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(phone)
                .setTempId(1)
                // .setSignId(1380)
                .build();

        try {
            SendSMSResult res = client.sendSMSCode(payload);
            LOG.info("Got result: " + res);
        } catch (Exception e) {
            LOG.info("Error Message: " + e.getMessage());
        }

    }

    //模板发送短信通知
    public void sendNoticeSMS(String phone,String news) {
        //多参数时使用
        //Map<String, String> test = new HashMap<String, String>();
        //test.put("msg", "电压过高");
        //test.put("uaer", "张三");
        SMSPayload payload = SMSPayload.newBuilder()
                .setMobileNumber(phone)
                .setTempId(168984)
                // .setSignId(1380)169113
                //多参数时使用
                // .setTempPara(test)
                .addTempPara("msg", news)
                .build();
        try {
            SendSMSResult res = client.sendTemplateSMS(payload);
            LOG.info("Got result: " + res);
        } catch (Exception e) {
            LOG.info("Error Message: " + e.getMessage());
        }
    }

    //模板多人发送
    public void sendBatchTemplateSMS(List phones,String news) {
        List<RecipientPayload> list = new ArrayList<RecipientPayload>();
        //1:信息格式统一直接循环添加
        for(int i=0;i<phones.size()-1;i++){
            list.add(new RecipientPayload.Builder()
                    .setMobile(phones.get(i).toString())
                    .addTempPara("msg",news)
                    .build());
        }
//        //2
//        RecipientPayload recipientPayload1 = new RecipientPayload.Builder()
//                .setMobile("15938815027")
//                .addTempPara("msg", "电压")
//                .build();
//        RecipientPayload recipientPayload2 = new RecipientPayload.Builder()
//                .setMobile("1593881502")
//                .addTempPara("msg", "电流")
//                .build();
//
//        list.add(recipientPayload1);
//        list.add(recipientPayload2);
        RecipientPayload[] recipientPayloads = new RecipientPayload[list.size()];
        BatchSMSPayload smsPayload = BatchSMSPayload.newBuilder()
                .setTempId(168984)
                // .setSignId(1380)
                .setRecipients(list.toArray(recipientPayloads))
                .build();
        try {
            BatchSMSResult result = client.sendBatchTemplateSMS(smsPayload);
            LOG.info("Got result: " + result);
        } catch (Exception e) {
            LOG.info("Error Message: " + e.getMessage());
        }
    }



}
