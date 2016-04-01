package surveyExchange.my;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
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
    public SignUpServlet() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (
			Statement stmt1 = conn.createStatement();
			Statement stmt2 = conn.createStatement();
		) {
			String inputEmail = request.getParameter("email");
			String inputUsername = request.getParameter("username");
			String Info = "select * from UserInfo where email='" + inputEmail + "' or username='" + inputUsername + "'";
	        ResultSet rset = stmt1.executeQuery(Info);
            PrintWriter errorWriter = response.getWriter();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/signUp.jsp");
            boolean pass = true;
            if(rset.next()){
            	pass = false;
            }
            if(!pass){
            	errorWriter.println("<font color=red>Your email address or username already exists!</font>");
            	rd.include(request, response);
            	pass = false;
            }
            ResultSet maxId = stmt2.executeQuery("select max(id) as total from UserInfo");
            maxId.next();
            int newId = maxId.getInt("total") + 1;
	        if(pass){
	        	String inputPwd = request.getParameter("pwd");
	        	String inputPwd2 = request.getParameter("pwd2");
	        	if(inputPwd.equals(inputPwd2)){
	        		String addAccount = "insert into UserInfo values(" + newId + ", '" + inputUsername + "', '" + inputEmail + "', '" + inputPwd + "', 0)";
	        		stmt2.execute(addAccount);
	        		response.sendRedirect("signUpSuccess.jsp");
	        	} else {
	                errorWriter.println("<font color=red>The two passwords you entered does not match!</font>");
	                rd.include(request, response);
	        	}
	        }
		} catch(SQLException ex) {
			ex.printStackTrace();
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
