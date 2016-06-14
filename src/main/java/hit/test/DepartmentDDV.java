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

  private Map<Double,String> departments;

  @PostConstruct
  public void init() {
    dataSourceName = "jdbc/DSTest";
    retrieveDepartments();
  }

  public Map<Double, String> getDepartments() {
      return departments;
  }

  public void retrieveDepartments() {
    departments = new HashMap<Double,String>();
    String upstmt = "{? = call testdb.dbo.up_retrieve_all_department()}";
    try {
      Connection conn = getDSConnection();
      CallableStatement cstmt =  conn.prepareCall(upstmt);
      cstmt.registerOutParameter(1,Types.INTEGER);
      ResultSet rs = cstmt.executeQuery(upstmt);
      while (rs.next()) {
        departments.put(rs.getDouble("department_id"),rs.getString("department_name"));
      }
    } catch(Exception e) {
      addFatalMessage(e.getMessage());
    }

  }

}
