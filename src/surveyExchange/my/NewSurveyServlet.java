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
 * Servlet implementation class NewSurveyServlet
 */
@WebServlet("/NewSurveyServlet")
public class NewSurveyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "UserInfo?useSSL=false";
	static String driver = "com.mysql.jdbc.Driver";  
	static String userName = "root";
	static String password = " ";
	static Connection conn = null;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public NewSurveyServlet() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");  
	        String author = request.getParameter("author");
	        String title = request.getParameter("title");
	        String desc = request.getParameter("desc");
	        String expNumReply = request.getParameter("expNumReply");
	        String SurveyString = request.getParameter("SurveyString");
	        int newCreditValue = Integer.parseInt(request.getParameter("newCreditValue"));
			PreparedStatement pstUserInfo = conn.prepareStatement("update UserInfo set credit= ? where username = ?");
			pstUserInfo.setInt(1, newCreditValue);
			pstUserInfo.setString(2, author);
			pstUserInfo.executeUpdate();
			pstUserInfo = conn.prepareStatement("select id from UserInfo where username = ?");
			pstUserInfo.setString(1, author);
			ResultSet rs = pstUserInfo.executeQuery();
			String ownerId = null;
			if(rs.next()){
				ownerId = rs.getString("id");
			}
			PreparedStatement pstNewSurvey = conn.prepareStatement("insert into SurveysInfo (title, description, ownerId, questionSet, expectedNumReply, currentNumReply, newReply) values (?, ?, ?, ?, ?, ?, ?)");
			pstNewSurvey.setString(1, title);
			pstNewSurvey.setString(2, desc);
			pstNewSurvey.setString(3, ownerId);
			pstNewSurvey.setString(4, SurveyString);
			pstNewSurvey.setString(5, expNumReply);
			pstNewSurvey.setString(6, "0");
			pstNewSurvey.setString(7, "0");
			pstNewSurvey.executeUpdate();
			HttpSession session = request.getSession(false);
			session.setAttribute("credit", newCreditValue);
			RequestDispatcher rd=request.getRequestDispatcher("postSuccess.jsp");
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
