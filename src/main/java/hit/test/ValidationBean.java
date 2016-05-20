package hit.test;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@ManagedBean
public class ValidationBean {
 
  @Min(10) @Max(20)
  private Integer age;
  
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
