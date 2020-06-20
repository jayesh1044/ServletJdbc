import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {
	
	private String dburl = "jdbc:mysql://localhost:3306/test";
	private String dbuname = "root";
	private String dbpasword = "jayesh";
	private String dbdriver = "com.mysql.jdbc.Driver";

	public void loadDriver(String dbdriver)
	{
		try {
			getClass().forName(dbdriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection()
	{
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl,dbuname,dbpasword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public String insert(Member member) 
	{
		loadDriver(dbdriver);
		Connection con = getConnection();
		System.out.println("Connected");
		String sql = "insert into test.member values (?,?,?,?)";
		String result = "Data Entered successfully";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, member.getUname());
			ps.setString(2,member.getPassword());
			ps.setString(3, member.getEmail());
			ps.setString(4, member.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Data not entered";
		}
		return result;
	}
}
