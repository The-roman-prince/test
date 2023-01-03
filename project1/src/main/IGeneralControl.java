package main;
import java.io.IOException;


public interface IGeneralControl {
	
	void onLoading() throws IOException;
	
	void onUpdating() throws IOException;
	
	void showLoginControl() throws IOException;
	
	void showMenuControl() throws IOException;
	
}