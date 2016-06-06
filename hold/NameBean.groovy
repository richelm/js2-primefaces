package hit.test;

import groovy.sql.Sql
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import groovy.transform.Canonical;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.annotation.Resource;
import javax.sql.DataSource;

@Canonical
@ManagedBean
public class NameBean {
    @NotNull(message="You must enter a first name.")
    @Size(max=5,message="First name cannot exceed 5 characters.")
    String firstName

    @NotNull(message="You must enter a last name.")
    @Size(max=10,message="Last name cannot exceed 10 characters.")
    String lastName

    @Max(10)
    java.lang.Long myNum

    @Resource(name="jdbc/DSTest") private DataSource dataSource;
    String storedProcedureCall = "{? = call up_raise_error(?)}"
    List params = [Sql.INTEGER,myNum]

    public void save() {
      try {
        def sql = new Sql(dataSource)
        sql.call(storedProcedureCall, params) {rv ->
          returnValue = rv
        }
        sql.close()
      } catch(Exception e) {
        addMessage(e.getMessage())
      }
    }

    public void update() {
      if (firstName == "XXX") {
        addMessage("Something went horribly wrong!");
      }
    }

    public void addMessage(String summary) {
        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }

}
