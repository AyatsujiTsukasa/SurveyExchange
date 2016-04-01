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
 * Servlet implementation class mySurveys
 */
@WebServlet("/mySurveys")
public class mySurveys extends HttpServlet {
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
    public mySurveys() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String ownerId = request.getParameter("ownerId");
		try {
			PreparedStatement pst = conn.prepareStatement("select title, description from SurveysInfo where ownerId=?");
			pst.setInt(1, Integer.parseInt(ownerId));
			ResultSet rs = pst.executeQuery();
			String qs = "";
			while(rs.next()){
				qs += rs.getString("title");
				qs += "#%#";
				qs += rs.getString("description");
				qs += "#%%#";
			}
			HttpSession session = request.getSession(false);
			session.setAttribute("questions", qs);
			RequestDispatcher rd=request.getRequestDispatcher("mySurveys.jsp");
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
