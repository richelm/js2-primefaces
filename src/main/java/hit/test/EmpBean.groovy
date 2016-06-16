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
import groovy.sql.Sql
import groovy.transform.Canonical;

@Canonical
@ManagedBean
@RequestScoped
public class EmpBean extends GBaseBean {

  @Size(max=40)
  @NotNull(message="You must enter a last name.")
  String lastName

  @Size(max=40)
  @NotNull(message="You must enter a first name.")
  String firstName

  @Size(max=60)
  String email

  // Date startDate

  @Min(1L)
  @Max(24L)
  int sickDays

  double departmentID

  // @DecimalMin("1.0")
  // @DecimalMax("2.75")
  // double fringeRatio


  @PostConstruct
  public void init() {
    dataSourceName = "jdbc/DSTest"
  }

  public void update() {
    storedProcedureCall = "{? = call up_raise_error(?)}"
    params = [Sql.INTEGER,sickDays]
    updateRecord()
  }

  public void create() {
    storedProcedureCall = "{? = call up_create_employee(?,?,?,?,?,?,?)}"
    params = [Sql.INTEGER, firstName, lastName, email, '7/15/2016', sickDays, 1.35,departmentID]
    createRecord()
    if (returnValue == 0) {
      addInfoMessage("Record created succsessfully.")
    }
    else {
      addFatalMessage("Error creating record!")
    }
  }
}
