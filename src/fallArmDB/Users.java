package fallArmDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.ConnectionDB;

public class Users {
	private int person_id;
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_role;
		
	public Users(){
	}
	
	public Users(int pid, String uname, String passwd, String role){
		this.person_id = pid;
		this.user_name = uname;
		this.user_password = passwd;
		this.user_role = role;
	}
	
	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	
	public void Save() throws ClassNotFoundException, SQLException {
		ConnectionDB.setConnection();		
		Statement st = ConnectionDB.conn.createStatement();
		String query = null;
		try{
			query = "insert into " + ConnectionDB.dbs + ".users (user_name, user_password, person_id, user_role) "
					+ "values ('" + this.getUser_name() +  "','" + this.getUser_password()  +  "','" + this.getPerson_id() + "','" + this.getUser_role() +  "');";
			st.executeUpdate(query);
			System.out.println("Successful to do an insertion of a user !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		ConnectionDB.stopConnection();
	}
	
	public static Users GetUser(String s_uname) throws ClassNotFoundException, SQLException{
		Users oUsers = new Users();
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".users where user_name = '" + s_uname + "'";		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_person_id = rs.getInt("person_id");
			oUsers.setPerson_id(i_person_id);
			oUsers.setUser_id(rs.getInt("user_id"));
			oUsers.setUser_name(rs.getString("user_name"));
			oUsers.setUser_password(rs.getString("user_password"));
			oUsers.setUser_role(rs.getString("user_role"));
			
			break;
		}
		ConnectionDB.stopConnection();
		return oUsers;
	}
	
	public static boolean IsUserExist(String s_uname) throws ClassNotFoundException, SQLException {
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".users where user_name = '" + s_uname + "'";		
		ResultSet rs = st.executeQuery(query);		
		while(rs.next())
		{
			return true;
		}
		return false;
	}
}
