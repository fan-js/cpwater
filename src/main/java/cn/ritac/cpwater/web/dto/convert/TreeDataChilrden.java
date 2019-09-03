package cn.ritac.cpwater.web.dto.convert;

import java.io.Serializable;
import java.util.List;

public class TreeDataChilrden implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String title;
	private String key;
	private List<Object> children;

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

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

}
