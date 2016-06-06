package hit.test;

import groovy.sql.Sql
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import groovy.transform.Canonical;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.InitialContext;

@Canonical
public class HitManagedBean {

  public void updateRecord(int s) {
    String storedProcedureCall = "{? = call up_raise_error(?)}"
    def params = [Sql.INTEGER,s]
    int returnValue
    try {
      InitialContext ctx = new InitialContext();
      DataSource dataSource = ctx.lookup("jdbc/DSTest")
      Connection conn = dataSource.getConnection()
      def sql = Sql.newInstance(conn)
      sql.call(storedProcedureCall, params) {rv ->
        returnValue = rv
      }
      sql.close()
    } catch(Exception e) {
      addMessage(e.getMessage())
    }
    if (s == "XXX") {
      addMessage("Something went horribly, horribly wrong!")
    }

  }

  public void addMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

}
