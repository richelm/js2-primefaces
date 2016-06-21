package hit.test;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import java.sql.Connection;
import groovy.transform.Canonical;
import groovy.sql.Sql
import groovy.sql.GroovyRowResult

@Canonical
@ManagedBean
@RequestScoped
public class DepartmentDD extends GBaseBean {

  Map<String,Double> departments;

  @PostConstruct
  public void init() {
    dataSourceName = "jdbc/DSTest"
    retrieveDepartments()
  }

  public Map<String,Double> getDepartments() {
      return departments;
  }

  public void retrieveDepartments() {
    departments = new HashMap<String,Double>()
    departments.put("Department A",101D)
    try {
      departments.put("Department B",102D)
      Connection conn = getConnection()
      departments.put("Department C",103D)
      sql.execute("{call up_retrieve_all_department()}",[]) { isResultSet, result ->
        departments.put("Department Z",104D)
        if (isResultSet) {
          departments.put("Department X",105D)
          for (GroovyRowResult row : result) {
            departments.put(row["department_name"],row["department_id"])
          }
        }
      }
    } catch(Exception e) {
      departments.put("Department ERROR",107D)
      addFatalMessage(e.getMessage())
    }
  }

}
