package hit.test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.HashMap;
import java.sql.*;

@ManagedBean
@RequestScoped
public class DepartmentDDV extends BaseBean {

  private Map<String,Double> departments;
  private Map<String,String> cities;

  @PostConstruct
  public void init() {
    dataSourceName = "jdbc/DSTest";

    // departments = new HashMap<String,Double>();
    // departments.put("Dean's Office",1D);
    // departments.put("Family Medicine",2D);
    // departments.put("Pediatrics",3D);
    // departments.put("Surgery",4D);
    retrieveDepartments();

    cities = new HashMap<String,String>();
    cities.put("london","London");
    cities.put("Detroit","detroit");
  }

  public Map<String,Double> getDepartments() {
      return departments;
  }

  public Map<String, String> getCities() {
      return cities;
  }

  public void retrieveDepartments() {
    departments = new HashMap<String,Double>();
    departments.put("Department Z",98D);
    String upstmt = "{? = call testdb.dbo.up_retrieve_all_department()}";
    try {
      Connection conn = getDSConnection();
      CallableStatement cstmt =  conn.prepareCall(upstmt);
      cstmt.registerOutParameter(1,Types.INTEGER);
      ResultSet rs = cstmt.executeQuery(upstmt);
      while (rs.next()) {
        departments.put("Department X",99D);
        departments.put(rs.getString("department_name"),rs.getDouble("department_id"));
      }
    } catch(Exception e) {
      addFatalMessage(e.getMessage());
    }
  }

}
