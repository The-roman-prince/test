package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Management implements IGeneralControl, IAdminControl, IUserControl {
	
	private ArrayList<User> userList;
	private User currentUser;
	private boolean adminMode;
	private Scanner sc = new Scanner(System.in);
	
	public Management() {
		this.userList = new ArrayList<User>();
		this.currentUser = null;
		this.adminMode = false;
	}
	
	public void run() throws IOException {
		this.onLoading();
		this.showLoginControl();
	}
	
/*
 * IGeneralControl
 */
	@Override
	public void onLoading() throws IOException {
		File file = new File("src/data/users.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNext()) {
			String[] userArray = sc.nextLine().split(",");
			this.userList.add(new User(userArray[0], userArray[1], userArray[2], Double.parseDouble(userArray[3])));
		}
		sc.close();
	}
	
	@Override
	public void onUpdating() throws IOException {
		File file = new File("src/data/users.txt");
		FileWriter fw = new FileWriter(file);
		for(User user : this.userList) {
			fw.write(user.toString() + "\n");
		}
		fw.close();
	}
	
	private void showLogin() {
		System.out.println("1 -> Login as user");
		System.out.println("2 -> Login as admin");
		System.out.println("0 -> Exit");
	}
	
	@Override
	public void showLoginControl() throws IOException {
		boolean run = true;
		while(run) {
			this.showLogin();
			System.out.print("Input: ");
			int choose = this.sc.nextInt();
			switch(choose) {
				case 1: {
					this.loginAsUser();
					break;
				}
				case 2:{
					this.loginAsAdmin();
					break;
				}
				case 0: {
					run = false;
					break;
				}
				default: {
					System.out.println("Not valid!");
				}
			}
		}
		sc.close();
		System.out.println("Good bye!");
	}
	
	private void showMenu() {
		if(this.adminMode) {
			System.out.println("1 -> Add");
			System.out.println("2 -> Delete");
			System.out.println("3 -> Show all users information");
			System.out.println("0 -> Logout");
		}else {
			System.out.println("1 -> Recharge");
			System.out.println("2 -> Withdraw");
			System.out.println("3 -> Transfers");
			System.out.println("4 -> Show information");
			System.out.println("0 -> Logout");
		}
	}
	
	@Override
	public void showMenuControl() throws IOException {
		boolean run = true;
		while(run) {
			System.out.println("------------------------------------------");
			this.showMenu();
			if(this.adminMode) {
				System.out.print("Input: ");
				int choose = this.sc.nextInt();
				switch(choose) {
					case 1: {
						this.addUser();
						break;
					}
					case 2: {
						this.deleteUser();
						break;
					}
					case 3: {
						this.showAllUsersInformation();
						break;
					}
					case 0: {
						run = false;
						break;
					}
					default: {
						System.out.println("Not valid");
					}
				}
			}else {
				System.out.print("Input: ");
				int choose = this.sc.nextInt();
				switch(choose) {
					case 1: {
						this.recharge();
						break;
					}
					case 2: {
						this.withdraw();
						break;
					}
					case 3:{
						this.transfers();
						break;
					}
					case 4: {
						this.showUserInformation();
						break;
					}
					case 0: {
						this.logoutAsUser();
						run = false;
						break;
					}
					default: {
						System.out.println("Not valid");
					}
				}
			}
		}
		System.out.println("------------------------------------------");
	}
	
/*
 * IAdminControl
 */
	@Override
	public void loginAsAdmin() throws IOException {
		System.out.println("------------------------------------------");
		this.sc.nextLine();
		File file = new File("src/data/admin.txt");
		Scanner sc = new Scanner(file);
		String correctUsername = sc.nextLine();
		String correctPassword = sc.nextLine();
		boolean run = true;
		while(run) {
			System.out.print("USERNAME: ");
			String username = this.sc.nextLine();
			if(username.equals(correctUsername)) {
				while(true) {
					System.out.print("PASSWORD: ");
					String password = this.sc.nextLine();
					if(password.equals(correctPassword)) {
						this.adminMode = true;
						this.showMenuControl();
						run = false;
						break;
					}
				}
			}
		}
		sc.close();
	}
	
	@Override
	public void logoutAsAdmin() {
		this.adminMode = false;
	}
	
	@Override
	public void addUser() throws IOException {
		System.out.println("------------------------------------------");
		this.sc.nextLine();
		System.out.print("ID: ");
		String id = this.sc.nextLine();
		System.out.print("PIN: ");
		String pin = this.sc.nextLine();
		System.out.print("NAME: ");
		String name = this.sc.nextLine();
		System.out.print("BALANCE: ");
		double balance = this.sc.nextDouble();
		this.userList.add(new User(id, pin, name, balance));
		this.onUpdating();
	}
	
	@Override
	public void deleteUser() throws IOException {
		System.out.println("------------------------------------------");
		this.sc.nextLine();
		boolean run = true;
		while(run) {
			System.out.print("ID: ");
			String id = this.sc.nextLine();
			for(User user : this.userList) {
				if(user.getId().equals(id)) {
					this.userList.remove(user);
					this.onUpdating();
					run = false;
					break;
				}
			}
		}
	}
	
	@Override
	public void showAllUsersInformation() {
		System.out.println("------------------------------------------");
		for(User user : this.userList) {
			System.out.println(user);
		}
	}
	
/*
 * IUserControl
 */
	@Override
	public void loginAsUser() throws IOException {
		System.out.println("------------------------------------------");
		this.sc.nextLine();
		boolean run = true;
		while(run) {
			System.out.print("ID: ");
			String id = this.sc.nextLine();
			for(User user : this.userList) {
				if(user.getId().equals(id)) {
					while(true) {
						System.out.print("PIN: ");
						String pin = this.sc.nextLine();
						if(user.getPin().equals(pin)) {
							this.currentUser = user;
							this.showMenuControl();
							run = false;
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	@Override
	public void logoutAsUser() {
		this.currentUser = null;
	}
	
	@Override
	public void recharge() throws IOException {
		System.out.println("------------------------------------------");
		while(true) {
			System.out.print("Money to recharge: ");
			double money = this.sc.nextDouble();
			if(money > 0) {
				this.currentUser.setBalance(this.currentUser.getBalance() + money);
				this.onUpdating();
				break;
			}
		}
	}
	
	@Override
	public void withdraw() throws IOException {
		System.out.println("------------------------------------------");
		while(true) {
			System.out.print("Money to withdraw: ");
			double money = this.sc.nextDouble();
			if(money > 0) {
				this.currentUser.setBalance(this.currentUser.getBalance() - money);
				this.onUpdating();
				break;
			}
		}
	}
	
	@Override
	public void transfers() throws IOException {
		System.out.println("------------------------------------------");
		this.sc.nextLine();
		boolean run = true;
		while(run) {
			System.out.print("ID: ");
			String id = this.sc.nextLine();
			for(User user : this.userList) {
				if(user.getId().equals(id)) {
					while(true) {
						System.out.print("Money to transfers: ");
						double money = this.sc.nextDouble();
						if(money > 0) {
							this.currentUser.setBalance(this.currentUser.getBalance() - money);
							user.setBalance(user.getBalance() + money);
							this.onUpdating();
							run = false;
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	@Override
	public void showUserInformation() {
		System.out.println("------------------------------------------");
		System.out.println("ID: " + this.currentUser.getId());
		System.out.println("PIN: " + this.currentUser.getPin());
		System.out.println("NAME: " + this.currentUser.getName());
		System.out.println("BALANCE: " + this.currentUser.getBalance());
	}
	
}
