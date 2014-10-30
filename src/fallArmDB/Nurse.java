package fallArmDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.ConnectionDB;

public class Nurse extends Person{

	private int person_id;
	private int nurse_id;
	private int patient_level = 0;
	
	// Constructor
	public Nurse() {
		super();
	}
	
	public Nurse(String person_firstname, String person_lastname, 
			String person_address, char person_gender, int patient_level){
		super(person_firstname, person_lastname, person_address, person_gender);
		this.patient_level = patient_level;
	}
	// End Constructor

	// Access method
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getNurse_id() {
		return nurse_id;
	}

	public void setNurse_id(int nurse_id) {
		this.nurse_id = nurse_id;
	}

	public int getPatient_level() {
		return patient_level;
	}

	public void setPatient_level(int patient_level) {
		this.patient_level = patient_level;
	}
	// End Access method
	
	public void Save() throws ClassNotFoundException, SQLException {
		ConnectionDB.setConnection();		
		Statement st = ConnectionDB.conn.createStatement();
		String query = null;
		try{
			super.Save();
			query = "insert into " + ConnectionDB.dbs + ".nurse (patient_level, person_id) "
					+ "values ('" + this.getPatient_level() +  "','" + this.getPerson_id() + "');";
			st.executeUpdate(query);
			System.out.println("Successful to do an insertion of a nurse !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		ConnectionDB.stopConnection();
	}
	
	public static Nurse GetNurse(int n_id) throws ClassNotFoundException, SQLException{
		Nurse oNurse = new Nurse();
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".nurse where nurse_id = " + n_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_person_id = rs.getInt("person_id");
			oNurse.setPerson_id(i_person_id);
			oPerson = Person.GetPerson(i_person_id);			
			oNurse.setNurse_id(n_id);
			oNurse.setPatient_level(rs.getInt("patient_level"));
			
			oNurse.setPerson_firstname(oPerson.getPerson_firstname());
			oNurse.setPerson_lastname(oPerson.getPerson_lastname());
			oNurse.setPerson_address(oPerson.getPerson_address());
			oNurse.setPerson_gender(oPerson.getPerson_gender());		
			break;
		}
		ConnectionDB.stopConnection();
		return oNurse;
	}
	
	public static Nurse GetNUrseBYPersonId(int p_id) throws ClassNotFoundException, SQLException{
		Nurse oNurse = new Nurse();
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".nurse where person_id = " + p_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			oNurse.setPerson_id(p_id);
			oPerson = Person.GetPerson(p_id);
			int i_nurse_id = rs.getInt("nurse_id");			
			oNurse.setNurse_id(i_nurse_id);
			oNurse.setPatient_level(rs.getInt("patient_level"));
			
			oNurse.setPerson_firstname(oPerson.getPerson_firstname());
			oNurse.setPerson_lastname(oPerson.getPerson_lastname());
			oNurse.setPerson_address(oPerson.getPerson_address());
			oNurse.setPerson_gender(oPerson.getPerson_gender());		
			break;
		}
		ConnectionDB.stopConnection();
		return oNurse;
	}
	
	public String toString(){
		return "Nurse id: " + this.getNurse_id() + ", First Name: " + this.getPerson_firstname() + ", Last Name: " + this.getPerson_lastname() + 
				", Gender: " + this.getPerson_gender() + ", Birth: " + this.getPerson_birth() + 
				", Working group" + this.getPatient_level();
	}
}
