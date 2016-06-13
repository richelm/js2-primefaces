package hit.test;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.InitialContext;

public class BaseBean {

  public String dataSourceName;
  public int returnValue;
  public boolean result;


  public Connection getDSConnection() throws Exception {
    InitialContext ctx = new InitialContext();
    DataSource ds = (DataSource)ctx.lookup(dataSourceName);
    return ds.getConnection();
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
