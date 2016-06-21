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
public class GBaseBean {
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
      if (returnValue == 0) {
        returnValue = -100
      }
      addFatalMessage(e.getMessage())
    }
  }

  public void createRecord() {
    try {
      Connection conn = getConnection()
      def sql = Sql.newInstance(conn)
      sql.call(storedProcedureCall, params) {rv ->
        returnValue = rv
      }
      conn.close()
    } catch(Exception e) {
      if (returnValue == 0) {
        returnValue = -100
      }
      addFatalMessage(e.getMessage())
    }
  }

  public void deleteRecord() {
    try {
      Connection conn = getConnection()
      def sql = Sql.newInstance(conn)
      sql.call(storedProcedureCall, params) {rv ->
        returnValue = rv
      }
      conn.close()
    } catch(Exception e) {
      if (returnValue == 0) {
        returnValue = -100
      }
      addFatalMessage(e.getMessage())
    }
  }

  public Connection getConnection() {
    InitialContext ctx = new InitialContext();
    DataSource dataSource = ctx.lookup(dataSourceName)
    return dataSource.getConnection()
  }

  public void addFatalMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

  public void addErrorMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

  public void addWarnMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

  public void addInfoMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

}
