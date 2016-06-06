package hit.test;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Max;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Employee extends HitManagedBean {

  @NotNull(message="You must enter a last name.")
  private String lastName;

  @NotNull(message="You must enter a first name.")
  private String firstName;

  @Max(10)
  private int sickDays;


  public void update() {
    updateRecord(sickDays);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getSickDays() {
    return sickDays;
  }

  public void setFirstName(String fn) {
    this.firstName = fn;
  }
  public void setLastName(String ln) {
    this.lastName = ln;
  }
  public void setSickDays(int sd) {
    this.sickDays = sd;
  }

}
