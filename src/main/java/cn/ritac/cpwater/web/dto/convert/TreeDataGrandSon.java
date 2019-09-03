package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;

public class TreeDataGrandSon implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String key;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
