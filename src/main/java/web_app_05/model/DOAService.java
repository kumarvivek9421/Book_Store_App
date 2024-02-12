package web_app_05.model;

public interface DOAService {

	public void connectDB();
	public boolean verifyCredentials(String user_email, String password);
	public void saveRegistration(String username, String user_email, String password, String user_mobile);
}
