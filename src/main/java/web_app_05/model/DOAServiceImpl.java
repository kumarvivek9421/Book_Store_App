package web_app_05.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class DOAServiceImpl implements DOAService {

	private Connection con;
	private Statement stmnt;
	@Override
	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false", "root", "viv123");
			stmnt=con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public boolean verifyCredentials(String user_email, String password) {
		try {
			ResultSet result = stmnt.executeQuery("select * from users where user_email='"+user_email+"' and password='"+password+"'");
			return result.next();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void saveRegistration(String username, String user_email, String password, String user_mobile) {
		try {
			stmnt.executeUpdate("insert into users(username,password,user_email,user_mobile) values('"+username+"','"+password+"','"+user_email+"','"+user_mobile+"')");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
