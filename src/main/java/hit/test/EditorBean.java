package hit.test;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "<ul><li>This editor is provided by PrimeFaces</li></ul>";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
