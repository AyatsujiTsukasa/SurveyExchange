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
 * Servlet implementation class toServey
 */
@WebServlet("/toSurvey")
public class toSurvey extends HttpServlet {
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
    public toSurvey() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        String title = request.getParameter("surveyTitle");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
			PreparedStatement pst = conn.prepareStatement("select * from SurveysInfo where title=?");
			pst.setString(1, title);
			ResultSet rs = pst.executeQuery();
			String desc = null;
			String ownerId = null;
			String surveyString = null;
			if(rs.next()){
				desc = rs.getString("description");
				ownerId = rs.getString("ownerId");
				surveyString = rs.getString("questionSet");
			}
			PreparedStatement pst2 = conn.prepareStatement("select * from UserInfo where id=?");
			pst2.setInt(1, Integer.parseInt(ownerId));
			String author = null;
			String email = null;
			rs = pst2.executeQuery();
			if(rs.next()){
				author = rs.getString("username");
				email = rs.getString("email");
			}
			HttpSession session = request.getSession(false);
			if(session != null){
				session.setAttribute("title", title);
				session.setAttribute("desc", desc);
				session.setAttribute("surveyString", surveyString);
				session.setAttribute("author", author);
				session.setAttribute("email", email);
				session.setAttribute("participatorId", id);
			}
			RequestDispatcher rd=request.getRequestDispatcher("doSurvey.jsp");
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
		doGet(request, response);
	}

}
