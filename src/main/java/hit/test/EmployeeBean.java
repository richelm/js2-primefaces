package hit.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.DecimalMax;
import javax.naming.InitialContext;
import java.sql.*;


@ManagedBean
@RequestScoped
public class EmployeeBean extends BaseBean {

  @Size(max=40)
  @NotNull(message="You must enter a last name.")
  String lastName;

  @Size(max=40)
  @NotNull(message="You must enter a first name.")
  String firstName;

  @Size(max=60)
  String email;

  java.util.Date startDate;

  @Min(1L)
  @Max(24L)
  int sickDays;

  @DecimalMin("1.0")
  @DecimalMax("2.75")
  double fringeRatio;

  double departmentID;

  String city;

  @PostConstruct
  public void init() {
    dataSourceName = "jdbc/DSTest";
  }

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
  public void setStartDate(java.util.Date d) {
    this.startDate = d;
  }
  public void setSickDays(int i) {
    this.sickDays = i;
  }
  public void setFringeRatio(double d) {
    this.fringeRatio = d;
  }
  public void setDepartmentID(double d) {
    this.departmentID = d;
  }
  public void setCity(String s) {
    this.city = s;
  }

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
  public java.util.Date getStartDate() {
    return this.startDate;
  }
  public int getSickDays() {
    return this.sickDays;
  }
  public double getFringeRatio() {
    return this.fringeRatio;
  }
  public double getDepartmentID() {
    return this.departmentID;
  }
  public String getCity() {
    return this.city;
  }
  // create record
  public void create() {
    String upstmt = "{? = call testdb.dbo.up_create_employee(?,?,?,?,?,?,?)}";
    try {
      Connection conn = getDSConnection();
      CallableStatement cstmt =  conn.prepareCall(upstmt);
      java.sql.Date sqlDate = new java.sql.Date(startDate.getTime());
  		cstmt.registerOutParameter(1,Types.INTEGER);
      cstmt.setString(2,firstName);
      cstmt.setString(3,lastName);
      cstmt.setString(4,email);
      cstmt.setDate(5,sqlDate);
      cstmt.setInt(6, sickDays);
      cstmt.setDouble(7,fringeRatio);
      cstmt.setDouble(8,departmentID);

      result = cstmt.execute();
  		returnValue = cstmt.getInt(1);

      if (returnValue == 0) {
        addInfoMessage("Record created successfully");
      } else {
        addFatalMessage("Create record failed with non-zero return value.");
      }
      conn.close();
    } catch(Exception e) {
      addFatalMessage(e.getMessage());
    }
  }

}
