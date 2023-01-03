package main;
import java.io.IOException;


public interface IAdminControl {
	
	void loginAsAdmin() throws IOException;
	
	void logoutAsAdmin();
	
	void addUser() throws IOException;
	
	void deleteUser() throws IOException;
	
	void showAllUsersInformation();
	
}