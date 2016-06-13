package hit.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.InitialContext;
import java.lang.Integer;
import java.sql.*;
import java.util.*;

@ManagedBean
@RequestScoped
public class EmployeeBean {

  @Size(max=40)
  @NotNull(message="You must enter a last name.")
  String lastName;

  @Size(max=40)
  @NotNull(message="You must enter a first name.")
  String firstName;

  @Size(max=60)
  String email;

  //java.lang.Date startDate;

  @Min(1L)
  @Max(24L)
  int sickDays;

  //@DecimalMin("1.0")
  //@DecimalMax("2.75")
  //Double fringeRatio;

  // setters
  public void setFirstName(String s) {
    this.firstName = s;
  }
  public void setLastName(String s) {
    this.lastName = s;
  }
  public void setEmail(String s) {
     this.email = s;
  }
  // public void setStartData(Date d) {
  //   this.startDate = d;
  // }
  public void setSickDays(int i) {
    this.sickDays = i;
  }
  // public void set(doulbe d) {
  //   this.fringeRatio = d;
  // }

  // getters
  public String getFirstName() {
    return this.firstName;
  }
  public String getLastName() {
    return this.lastName;
  }
  public String getEmail() {
   return this.email;
  }
  // public Date getDate() {
  //   return this.startDate;
  // }
  public int getSickDays() {
    return this.sickDays;
  }
  // public double getFringeRatio() {
  //   return this.fringeRatio;
  // }

  // save record
  public void create() {
    boolean result;
    int returnValue;
    if (sickDays == 7) {
      addErrorMessage("You must never enter seven for sickDays. Shame on you!");
    }
    String upstmt = "{? = call testdb.dbo.up_create_employee(?,?,?,?,?,?)}";
    try {
      String dataSourceName = "jdbc/DSTest";
      InitialContext ctx = new InitialContext();
      DataSource ds = (DataSource)ctx.lookup(dataSourceName);
      Connection conn = ds.getConnection();
      CallableStatement cstmt =  conn.prepareCall(upstmt);
  		cstmt.registerOutParameter(1,Types.INTEGER);
      cstmt.setString(2,firstName);
      cstmt.setString(3,lastName);
      cstmt.setString(4,email);
      cstmt.setString(5,"6/1/2016");
      cstmt.setInt(6, sickDays);
      cstmt.setDouble(7,1.2);

      result = cstmt.execute();
  		returnValue = cstmt.getInt(1);

      if (returnValue == 0) {
        addInfoMessage("Record created successfully");
      }
      conn.close();
    } catch(Exception e) {
      addErrorMessage(e.getMessage());
    }
  }

  private void addErrorMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

  private void addInfoMessage(String summary) {
      FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
      FacesContext.getCurrentInstance().addMessage(null, errorMessage);
  }

}
