package main;
import java.io.IOException;


public interface IUserControl {
	
	void loginAsUser() throws IOException;
	
	void logoutAsUser();
	
	void recharge() throws IOException;
	
	void withdraw() throws IOException;
	
	void transfers() throws IOException;
	
	void showUserInformation();
	
}