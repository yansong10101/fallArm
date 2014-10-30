package fallArmDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import config.ConnectionDB;

public class Person {

	public static char GENDER_MAN = 'M';
	public static char GENDER_FEMALE = 'F';
	private int person_id;
	private String person_firstname = "";
	private String person_lastname = "";
	private String person_address = "";
	private char person_gender = 'N';
	private Date person_birth = new Date();
	
	// Constructor
	public Person() {	// for all
		
	}
	
	public Person(String firstname, String lastname, String address){	// for viewer
		this.person_firstname = firstname;
		this.person_lastname = lastname;
		this.person_address = address;
	}
	
	public Person(String firstname, String lastname, String address, char gender){	// for nurse
		this.person_firstname = firstname;
		this.person_lastname = lastname;
		this.person_address = address;
		this.person_gender = gender;
	}
	
	public Person(String firstname, String lastname, String address, char gender, Date birth){	// for patient
		this.person_firstname = firstname;
		this.person_lastname = lastname;
		this.person_address = address;
		this.person_gender = gender;
		this.person_birth = birth;
	}
	// End Constructor
	
	// Access method	
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public String getPerson_firstname() {
		return person_firstname;
	}

	public void setPerson_firstname(String person_firstname) {
		this.person_firstname = person_firstname;
	}

	public String getPerson_lastname() {
		return person_lastname;
	}

	public void setPerson_lastname(String person_lastname) {
		this.person_lastname = person_lastname;
	}

	public String getPerson_address() {
		return person_address;
	}

	public void setPerson_address(String person_address) {
		this.person_address = person_address;
	}

	public char getPerson_gender() {
		return person_gender;
	}

	public void setPerson_gender(char person_gender) {
		this.person_gender = person_gender;
	}

	public Date getPerson_birth() {
		return person_birth;
	}

	public void setPerson_birth(Date person_birth) {
		this.person_birth = person_birth;
	}
	// End Access method

	protected void Save() throws ClassNotFoundException, SQLException{		
		java.sql.Date sqlDate = new java.sql.Date(this.person_birth.getTime());
		Statement st = ConnectionDB.conn.createStatement();
		String query = "insert into " + ConnectionDB.dbs + ".person (person_firstname, person_lastname, person_address, person_gender, person_birth)  "
				+ "values ('"+person_firstname+"','"+person_lastname+"','"+person_address+"','"+person_gender+"','"+ sqlDate +"');";
		try{
			st.executeUpdate(query);
			System.out.println("Successful to do an insertion of a person!");
			// query this person id
			Statement st2 = ConnectionDB.conn.createStatement();
			query = "SELECT `AUTO_INCREMENT` AS p_id FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '" + ConnectionDB.dbs + "' AND TABLE_NAME = 'person';";
			ResultSet rs = st2.executeQuery(query);
			while(rs.next()){
				// minus one to match person table person_id
				this.setPerson_id(rs.getInt("p_id") - 1);
				break;
			}
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static Person GetPerson(int p_id) throws ClassNotFoundException, SQLException{
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".person where person_id = " + p_id;
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			oPerson.setPerson_id(rs.getInt("person_id"));
			oPerson.setPerson_firstname(rs.getString("person_firstname"));
			oPerson.setPerson_lastname(rs.getString("person_lastname"));
			oPerson.setPerson_address(rs.getString("person_address"));
			oPerson.setPerson_gender((rs.getString("person_gender").charAt(0)));
			java.util.Date dt_BirthDate = rs.getTimestamp("person_birth");
			oPerson.setPerson_birth(dt_BirthDate);			
			break;
		}
		ConnectionDB.stopConnection();
		return oPerson;
	}
	
	public String toString(){
		return "Person Name:" + this.getPerson_firstname() + " " + this.getPerson_lastname() + " Gender:" + this.getPerson_gender() + " Birthday: " + this.getPerson_birth();
	}
}
