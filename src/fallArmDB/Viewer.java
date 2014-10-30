package fallArmDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionDB;

public class Viewer extends Person{

	private int person_id;
	private int viewer_id;
	private String viewer_email = "";
	private String view_phone = "";
	private int patient_id;
	
	// Constructor
	public Viewer() {
		super();
	}

	public Viewer(String person_firstname, String person_lastname, String person_address, String email, String phone, int patient_id){
		super(person_firstname, person_lastname, person_address);
		this.viewer_email = email;
		this.view_phone = phone;
		this.patient_id = patient_id;
	}
	// End Constructor
	
	// Access method
	public int getPerson_id() {
		return person_id;
	}

	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}

	public int getViewer_id() {
		return viewer_id;
	}

	public void setViewer_id(int viewer_id) {
		this.viewer_id = viewer_id;
	}

	public String getViewer_email() {
		return viewer_email;
	}

	public void setViewer_email(String viewer_email) {
		this.viewer_email = viewer_email;
	}

	public String getView_phone() {
		return view_phone;
	}

	public void setView_phone(String view_phone) {
		this.view_phone = view_phone;
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	// End Access method
	
	public void Save() throws ClassNotFoundException, SQLException{
		ConnectionDB.setConnection();
		
		Statement st = ConnectionDB.conn.createStatement();
		String query = null;
		try{
			super.Save();
			query = "insert into " + ConnectionDB.dbs + ".viewer (viewer_email, view_phone, patient_id, person_id) "
					+ "values ('"+viewer_email+"','"+view_phone+"','"+patient_id +  "','" + this.getPerson_id() +"');";
			st.executeUpdate(query);
			System.out.println("Successful to do an insertion of a viewer !");
		}catch(SQLException ex){
			System.out.println(ex.getMessage());
		}
		
		ConnectionDB.stopConnection();
	}
	
	public static Viewer GetViewer(int v_id) throws ClassNotFoundException, SQLException{
		Viewer oViewer = new Viewer();
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".viewer where viewer_id = " + v_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_person_id = rs.getInt("person_id");
			oViewer.setPerson_id(i_person_id);
			oPerson = Person.GetPerson(i_person_id);			
			oViewer.setViewer_id(rs.getInt("viewer_id"));
			oViewer.setViewer_email(rs.getString("viewer_email"));
			oViewer.setView_phone(rs.getString("view_phone"));
			oViewer.setPatient_id(v_id);
			
			oViewer.setPerson_firstname(oPerson.getPerson_firstname());
			oViewer.setPerson_lastname(oPerson.getPerson_lastname());
			oViewer.setPerson_address(oPerson.getPerson_address());	
			break;
		}
		ConnectionDB.stopConnection();
		return oViewer;
	}
	
	public static Viewer GetViewerByPersonId(int p_id) throws ClassNotFoundException, SQLException{
		Viewer oViewer = new Viewer();
		Person oPerson = new Person();		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select * from " + ConnectionDB.dbs + ".viewer where person_id = " + p_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_viewer_id = rs.getInt("viewer_id");
			oViewer.setPerson_id(p_id);
			oPerson = Person.GetPerson(p_id);			
			oViewer.setViewer_id(i_viewer_id);
			oViewer.setViewer_email(rs.getString("viewer_email"));
			oViewer.setView_phone(rs.getString("view_phone"));
			oViewer.setPatient_id(rs.getInt("patient_id"));
			
			oViewer.setPerson_firstname(oPerson.getPerson_firstname());
			oViewer.setPerson_lastname(oPerson.getPerson_lastname());
			oViewer.setPerson_address(oPerson.getPerson_address());	
			break;
		}
		ConnectionDB.stopConnection();
		return oViewer;
	}
	
	public static ArrayList<Viewer> GetViewers(int pt_id) throws ClassNotFoundException, SQLException{
		ArrayList<Viewer> oViewers = new ArrayList<Viewer>();
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		String query = "select viewer_id from " + ConnectionDB.dbs + ".viewer where patient_id = " + pt_id;		
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			int i_viewer_id = rs.getInt("viewer_id");
			Viewer oViewer = Viewer.GetViewer(i_viewer_id);
			if(oViewer != null)
				oViewers.add(oViewer);			
		}		
		return oViewers;
	}
	
	public String toString(){
		return "Viewer id: " + this.getViewer_id() + ", First Name: " + this.getPerson_firstname() + ", Last Name: " + this.getPerson_lastname() + 
				", Gender: " + this.getPerson_gender() + ", Birth: " + this.getPerson_birth() + 
				", email: " + this.getViewer_email() + ", Phone: " + this.getView_phone();
	}
}
