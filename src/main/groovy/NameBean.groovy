package hit.test;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import groovy.transform.Canonical;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Canonical
@ManagedBean
public class NameBean {
    @NotNull(message="You must enter a first name.")
    @Size(max=5,message="First name cannot exceed 5 characters.")
    String firstName

    @NotNull(message="You must enter a last name.")
    @Size(max=10,message="Last name cannot exceed 10 characters.")
    String lastName

    public void update() {
      if (firstName == "XXX") {
        addMessage("Something went horribly wrong!");
      }
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
