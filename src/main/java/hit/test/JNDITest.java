package hit.test;

// Import required java libraries
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.annotation.Resource;
import javax.sql.DataSource;


// Extend HttpServlet class
@WebServlet(urlPatterns = {"/jndiTest"})
public class JNDITest extends HttpServlet {

    @Resource(name="jdbc/DSTest") private DataSource dataSource;

    public void doGet(HttpServletRequest request,
                HttpServletResponse response)
        throws ServletException, IOException {

        try {
            Connection con = dataSource.getConnection();

            Statement stmt = con.createStatement();
            String query = "select code, name from comdb.dbo.state";
            ResultSet rs = stmt.executeQuery(query);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<html><body>");
            out.print("<center><h1>US State</h1></center>");
            out.print("<table border=\"1\" cellspacing=\"0\" cellpadding=\"3\">");
            out.print("<tr><th>State Code</th>");
            out.print("<th>State Name</th></tr>");

            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getString("code") + "</td>");
                out.print("<td>" + rs.getString("name") + "</td>");
                out.print("</tr>");
            }
            out.print("</table></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void destroy()
    {
        // do nothing.
    }
}
