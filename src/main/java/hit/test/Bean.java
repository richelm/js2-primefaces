package hit.test;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@ManagedBean
public class Bean {
  @Size(min=2,max=5,message="Name must be 2 to 5 characters.")
  private String name;

  @Min(10) @Max(20)
  private Integer age;

  public Bean() {

  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String n) {
    this.name = n;
  }
}
