package MIDTERMDELIMETER;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class main {

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		wawa();


	}

	public static void wawa() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		System.out.println(" ***welcome***");

		System.out.println("1. login");
		System.out.println("2. register");

		int choice = in.nextInt();
		if (choice == 1) {
			login();

		} else if (choice == 2) {

			register();

		} else {
			System.out.print("Invalid");
			wawa();
		}

	}

	public static void register() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub


		System.out.println("	");
		System.out.println("INSERT THE FOLLOWING DETAILS!!");


		System.out.print("Enter username:");
		String username = in.next();
		System.out.print("Enter password:");
		String password= in.next();
		System.out.print("Confirm password: ");
		String recheck = in.next();
		System.out.print("Enter Lastname:");
		String lastname = in.next();
		System.out.print("Enter Firstname:");
		String firstname = in.next();
		System.out.print("Enter Middlename:");
		String middlename = in.next();
		System.out.print("Enter Address:");
		String address = in.next();
		System.out.print("Enter Age:");
		int age = in.nextInt();

		while(!password.equals(recheck)) {
			System.out.println("incorrect password, please try again...");

			break;
		}

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Final?autoReconnect=true&useSSL=false","root", "root");


			CallableStatement statement = conn.prepareCall("{call getInside(?,?,?,?,?,?,?)}");

			statement.setString(1,username);
			statement.setString(2,password);
			statement.setString(3,lastname);
			statement.setString(4,firstname);
			statement.setString(5,middlename);
			statement.setString(6,address);
			statement.setInt(7,age);

			statement.executeUpdate();
			wawa();
		}
		catch(Exception e) {
			//  Block of code to handle errors
		}
	}




	public static void login() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub


		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Final?autoReconnect=true&useSSL=false",	"root", "root");

		System.out.print("Username: ");
		String un = in.next();
		System.out.print("Password: ");
		String pword1 = in.next();

		CallableStatement statement3 = conn.prepareCall("call checkcred()");
		ResultSet rs3 = statement3.executeQuery();

		while (rs3.next()) {
			//System.out.println(rs3.getString(1) + " " + rs3.getString(2));
			if(un.equals(rs3.getString(1)) && pword1.equals(rs3.getString(2))){

				System.out.println("Successful Login");

				System.out.println("Do you want to update your details or solve problem?");
				System.out.println("1. update");
				System.out.println("2. solve");


				int choice = in.nextInt();

				if (choice == 1) {
					update();

				} else if (choice == 2) {
					problem();

				} else {
					System.out.print("Invalid");
					wawa();
				}
			}
		}		
	}

	public static void update() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Final?autoReconnect=true&useSSL=false",	"root", "root");

			System.out.println("Enter new value");
			System.out.println("New lastname");
			String lastname = in.next();
			System.out.println("New firstname");
			String firstname = in.next();
			System.out.println("New middlename");
			String middlename = in.next();
			System.out.println("New address");
			String address = in.next();
			System.out.println("Enter password");
			String password = in.next();

			CallableStatement statement4 = conn.prepareCall("CALL Updateinfoz(?,?,?,?,?)");
			statement4.setString(2, password);
			statement4.setString(3, lastname);
			statement4.setString(4, firstname);
			statement4.setString(5, middlename);
			statement4.setString(6, address);
			statement4.executeQuery();
			System.out.println("Please log in again");
		}catch (SQLException e) {
			wawa();
			e.printStackTrace();
		}
	}


	public static void problem() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Random ran = new Random();
		int x = ran.nextInt(10);
		System.out.println("what is the factorial of " + x);
		int answer = in.nextInt();
		int fact = 1;
		for (int i = 1; i <= x; i++) {
			fact = fact * i;
			if (answer == fact) {

				System.out.println("Congratulations! " + fact + " is the right answer!");

			} else if (answer< fact) {
				System.out.println("Incorrect answer! Login again.");
				wawa();
			} else if (answer > fact) {
				System.out.println("Incorrect answer! Login again.");
				wawa();
			}

		}	
	}
}




