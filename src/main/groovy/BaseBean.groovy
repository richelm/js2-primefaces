package hit.test;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.InitialContext;
import groovy.sql.Sql
import groovy.transform.Canonical;

@Canonical
public class BaseBean {
  String dataSourceName
  String storedProcedureCall
  def params = []
  int returnValue

  public void updateRecord() {
    try {
      Connection conn = getConnection()
      def sql = Sql.newInstance(conn)
      sql.call(storedProcedureCall, params) {rv ->
        returnValue = rv
      }
      conn.close()
    } catch(Exception e) {
      addMessage(e.getMessage())
    }
  }

  private Connection getConnection() {
    InitialContext ctx = new InitialContext();
    DataSource dataSource = ctx.lookup(dataSourceName)
    return dataSource.getConnection()
  }

  private void addMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

}
