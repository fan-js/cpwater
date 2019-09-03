package cn.ritac.cpwater.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.ritac.cpwater.mybatis.mapper.AssetsMapper;
import cn.ritac.cpwater.mybatis.model.Assets;
import cn.ritac.cpwater.service.AssetsService;
import cn.ritac.cpwater.web.dto.convert.AssetsCount;

@Service
public class AssetsServiceImpl extends BaseServiceImpl<Assets> implements AssetsService {

	@Autowired
	private AssetsMapper assetsMapper;

	@Autowired
	public void setInit() {
		super.setInit(assetsMapper);
	}

	public PageInfo<Assets> findListByObj(int pageIndex, int pageSize, Assets assets) {
		PageHelper.startPage(pageIndex, pageSize);
		List<Assets> list = assetsMapper.select(assets);
		return new PageInfo<>(list);
	}

	public AssetsCount countIfAssets() {
		return assetsMapper.countIfAssets();
	}

}
