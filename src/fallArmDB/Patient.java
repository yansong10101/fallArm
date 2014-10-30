package fallArmDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import config.ConnectionDB;

public class Patient extends Person{

	private int person_id;
	private int patient_id;
	private int patient_level = 0;
	
	// Constructor
	public Patient() {
		super();
	}

	public Patient(String person_firstname, String person_lastname, String person_address, 
				   char person_gender, Date person_birth, int level){
		super(person_firstname, person_lastname, person_address, person_gender, person_birth);
		this.patient_level = level;
	}
	// End Constructor
	
	// Access method
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	
	public int getPatient_level() {
		return patient_level;
	}

	public void setPatient_level(int patient_level) {
		this.patient_level = patient_level;
	}
	// End Access method
	
	public void Save() throws ClassNotFoundException, SQLException
	{
		ConnectionDB.setConnection();		
		Statement st = ConnectionDB.conn.createStatement();
		String query = null;
		try{
			super.Save();
			query = "insert into " + ConnectionDB.dbs + 
					".patient (patient_level, person_id) values ('" + patient_level + "','" + this.getPerson_id() + "');";
			st.executeUpdate(query);
			System.out.println("Successful to do an insertion of a Patient !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}		
		ConnectionDB.stopConnection();		
	}
	
	public static Patient GetPatient(int pt_id) throws ClassNotFoundException, SQLException{
		Patient oPatient = new Patient();
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".patient where patient_id = " + pt_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_person_id = rs.getInt("person_id");
			oPatient.setPerson_id(i_person_id);
			oPerson = Person.GetPerson(i_person_id);			
			oPatient.setPatient_id(pt_id);
			oPatient.setPatient_level(rs.getInt("patient_level"));
			
			oPatient.setPerson_firstname(oPerson.getPerson_firstname());
			oPatient.setPerson_lastname(oPerson.getPerson_lastname());
			oPatient.setPerson_address(oPerson.getPerson_address());
			oPatient.setPerson_gender(oPerson.getPerson_gender());
			oPatient.setPerson_birth(oPerson.getPerson_birth());	
			break;
		}
		System.out.println(oPatient);
		return oPatient;
	}
	
	public static boolean IsPatientExist(int p_id) throws ClassNotFoundException, SQLException{
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".patient where patient_id = " + p_id;		
		ResultSet rs = st.executeQuery(query);
		if(rs == null)
			return false;
		return true;
	}
	
	public String toString(){
		return "Patient id: " + this.getPatient_id() + ", First Name: " + this.getPerson_firstname() + ", Last Name: " + this.getPerson_lastname() + 
				", Gender: " + this.getPerson_gender() + ", Birth: " + this.getPerson_birth();
	}
}
