package surveyExchange.my;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class toMySurvey
 */
@WebServlet("/toMySurvey")
public class toMySurvey extends HttpServlet {
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "UserInfo?useSSL=false";
	static String driver = "com.mysql.jdbc.Driver";  
	static String userName = "root";
	static String password = " ";
	static Connection conn = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public toMySurvey() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String title = request.getParameter("title");
		try {
			PreparedStatement pst = conn.prepareStatement("select * from SurveysInfo where title=?");
			pst.setString(1, title);
			HttpSession session = request.getSession(false);
			ResultSet rs = pst.executeQuery();
			PreparedStatement exe = conn.prepareStatement("update SurveysInfo set newReply=? where title=?");
			exe.setInt(1, 0);
			exe.setString(2, title);
			exe.executeUpdate();
			if(rs.next()){
				session.setAttribute("title", title);
				session.setAttribute("desc", rs.getString("description"));
				session.setAttribute("qs", rs.getString("questionSet"));
				session.setAttribute("expNumReply", rs.getInt("expectedNumReply"));
				session.setAttribute("curNumReply", rs.getInt("currentNumReply"));
			}
			RequestDispatcher rd=request.getRequestDispatcher("mySurvey.jsp");
            rd.forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
