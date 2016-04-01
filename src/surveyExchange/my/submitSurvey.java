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
 * Servlet implementation class submitSurvey
 */
@WebServlet("/submitSurvey")
public class submitSurvey extends HttpServlet {
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
    public submitSurvey() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String title = request.getParameter("surveyTitle").trim();
		int pId = Integer.parseInt(request.getParameter(("pId").trim()));
		try {
			PreparedStatement pst = conn.prepareStatement("select * from SurveysInfo where title=?");
			pst.setString(1, title);
			ResultSet rs = pst.executeQuery();
			String qs = null;
			int numReply = -1;
			if(rs.next()){
				qs = rs.getString("questionSet");
				numReply = rs.getInt("currentNumReply");
			}
			numReply++;
			String[] questions = qs.split("#\\$%\\^");
			for(int i=0; i<questions.length; i++){
				String[] info = questions[i].split("#\\$#");
				if(info[1].equals("text")){
					String[] temp = new String[info.length + 1];
					System.arraycopy(info, 0, temp, 0, info.length);
					temp[info.length] = request.getParameter("" + i);
					info = temp;
				} else if(info[1].equals("radio")){
					int index = info.length / 2 + Integer.parseInt(request.getParameter("" + i)) + 1;
					info[index] = (Integer.parseInt(info[index]) + 1) + "";
				} else {
					String[] results = request.getParameterValues("" + i);
					for(String str : results){
						int index = info.length / 2 + Integer.parseInt(str) + 1;
						info[index] = (Integer.parseInt(info[index]) + 1) + "";
					}
				}
				questions[i] = String.join("#$#", info);
			}
			String newQuestionSet = String.join("#$%^", questions);
			pst = conn.prepareStatement("select credit from UserInfo where id=?");
			pst.setInt(1, pId);
			rs = pst.executeQuery();
			int credit = -1;
			if(rs.next())
				credit = rs.getInt("credit") + 1;
			pst = conn.prepareStatement("update UserInfo set credit=? where id=?");
			pst.setInt(1, credit);
			pst.setInt(2, pId);
			pst.executeUpdate();
			pst = conn.prepareStatement("update SurveysInfo set questionSet=?, currentNumReply=?, newReply=?");
			pst.setString(1, newQuestionSet);
			pst.setInt(2, numReply);
			pst.setInt(3, 1);
			pst.executeUpdate();
			HttpSession session = request.getSession(false);
			session.setAttribute("credit", credit);
			RequestDispatcher rd=request.getRequestDispatcher("submitSuccess.jsp");
            rd.forward(request,response);
		} catch (SQLException e) {
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
