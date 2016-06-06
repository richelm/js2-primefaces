package hit.test;

import groovy.sql.Sql
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import groovy.transform.Canonical;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;


@ManagedBean
public class EmployeeBean {

    @NotNull(message="You must enter a first name.")
    @Size(max=40,message="First name cannot exceed 40 characters.")
    String firstName

    @NotNull(message="You must enter a last name.")
    @Size(max=40,message="Last name cannot exceed 40 characters.")
    String lastName

    int sickDays

    int returnValue

    public void save() {
      Connection connection = null;
      String storedProcedureCall = "{? = call up_raise_error(?)}"
      def params = [Sql.INTEGER,sickDays]
      try {
        InitialContext ctx = new InitialContext();
        DataSource dataSource = ctx.lookup("jdbc/DSTest")
        Connection conn = dataSource.getConnection()
        connection = dataSource.getConnection();

        def sql = new Sql(connection)
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
