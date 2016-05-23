package hit.test;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;

@ManagedBean
public class Person {

  @NotNull(message = "You must select Male or Female.")
  private boolean sex;

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
}
