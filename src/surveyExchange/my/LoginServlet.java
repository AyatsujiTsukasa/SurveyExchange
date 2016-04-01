package surveyExchange.my;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	static String url = "jdbc:mysql://localhost:3306/";
	static String dbName = "UserInfo?useSSL=false";
	static String driver = "com.mysql.jdbc.Driver";  
	static String userName = "root";
	static String password = " ";
	static Connection conn = null;
	
	public static String[] getInfo(String email){
		String[] ans = new String[5];
		try {
			PreparedStatement pst = conn.prepareStatement("select * from UserInfo where email=?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
        	if(rs.next()){
        		for(int i=0; i<5; i++) {
        	        ans[i] = rs.getString(i+1);
        	    }
        	}
		} catch (Exception e) {  
            System.out.println(e);  
        }
		return ans;
	}
	
	public static boolean validate(String email, String pwd) {          
        boolean status = false;  
        PreparedStatement pst = null;
        ResultSet rs = null;  
  
        try {  
            Class.forName(driver).newInstance();  
  
            pst = conn.prepareStatement("select * from UserInfo where email=? and pwd=?");  
            pst.setString(1, email);
            pst.setString(2, pwd);  
  
            rs = pst.executeQuery();
            status = rs.next();
  
        } catch (Exception e) {  
            System.out.println(e);  
        } 
        return status;  
    }
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() throws SQLException {
        super();
        conn = DriverManager.getConnection(url + dbName, userName, password);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String[] info = getInfo(email);
        try {
			PreparedStatement selfFeed = conn.prepareStatement("select title, description from SurveysInfo where ownerId=? and newReply=?");
			selfFeed.setString(1, info[0]);
			selfFeed.setInt(2, 1);
			HttpSession session = request.getSession(false);
	        if(session!=null){
	        	session.setAttribute("name", info[1]);
	            session.setAttribute("pwd", info[3]);
	            session.setAttribute("id", info[0]);
	            session.setAttribute("credit", info[4]);
	            session.setAttribute("email", email);
	        }
			ResultSet rs = selfFeed.executeQuery();
			String[] selfFeedArr = new String[5];
			for(int j=0; j<5; j++)
				selfFeedArr[j] = "NULL";
			int i=0;
			while(rs.next() && i<5 && session != null){
				selfFeedArr[i] = rs.getString("title") + "#%#" + rs.getString("description");
				i++;
			}
			session.setAttribute("selfFeed", String.join("#%%#", selfFeedArr));
			
			PreparedStatement otherFeed = conn.prepareStatement("select title, description from SurveysInfo where ownerId!=? and currentNumReply < expectedNumReply");
			otherFeed.setString(1, info[0]);
			rs = otherFeed.executeQuery();
			String[] otherFeedArr = new String[3];
			for(int j=0; j<3; j++)
				otherFeedArr[j] = "NULL";
			i=0;
			while(rs.next() && i<3 && session != null){
				otherFeedArr[i] = rs.getString("title") + "#%#" + rs.getString("description");
				i++;
			}
			session.setAttribute("otherFeed", String.join("#%%#", otherFeedArr));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if(validate(email, pwd)){
            RequestDispatcher rd=request.getRequestDispatcher("userHome.jsp");
            rd.forward(request,response);
        }    
        else{    
            out.print("<p style=\"color:red\">Sorry, either username or password is wrong.</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
            rd.include(request,response);
        }
  
        out.close(); 
	}

}
