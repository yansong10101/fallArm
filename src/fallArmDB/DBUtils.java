package fallArmDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.ConnectionDB;

public class DBUtils {

	public static String TABLE_PERSON = "person";
	public static String TABLE_PATIENT = "patient";
	public static String TABLE_NURSE = "nurse";
	public static String TABLE_VIEWER = "viewer";
	
	public static String QUERY_SELECT_ALL_PATIENT = "select *, ps.person_id as p_id from " + ConnectionDB.dbs + ".person ps inner join " + ConnectionDB.dbs + ".patient pt on pt.person_id = ps.person_id;";
	public static String QUERY_SELECT_ALL_VIEWER = "select *, ps.person_id as p_id from " + ConnectionDB.dbs + ".person ps inner join " + ConnectionDB.dbs + ".viewer v on v.person_id = ps.person_id;";
	public static String QUERY_SELECT_ALL_NURSE = "select *, ps.person_id as p_id from " + ConnectionDB.dbs + ".person ps inner join " + ConnectionDB.dbs + ".nurse n on n.person_id = ps.person_id;";
	
	public static String QUERY_SELECT_NURSE_BY_LEVEL = "select *, ps.person_id as p_id from " + ConnectionDB.dbs + ".person ps inner join " + ConnectionDB.dbs + ".nurse n on n.person_id = ps.person_id where n.patient_level = ";
	public static String QUERY_SELECT_PATIENT_BY_LEVEL = "select *, ps.person_id as p_id from " + ConnectionDB.dbs + ".person ps inner join " + ConnectionDB.dbs + ".patient pt on pt.person_id = ps.person_id where pt.patient_level = ";
	
	public static String QUERY_SELECT_PID_BY_PERSONID = "select p.patient_id from " + ConnectionDB.dbs + ".patient p where p.person_id = ";
	
	public static String USERNAME = "uname";
	public static String PASSWD = "password";
	public static String FIRSTNAME = "firstname";
	public static String LASTNAME = "lastname";
	public static String ADDRESS = "address";
	public static String GENDER = "gender";
	public static String PATIENTLEVEL = "patient_level";
	public static String EMAIL = "email";
	public static String PHONE = "phone";
	public static String BIRTHDAY_YEAR = "birthdayYear";
	public static String BIRTHDAY_MONTH = "birthdayMonth";
	public static String BIRTHDAY_DAY = "birthdayDay";
	public static String LEVEL = "level";
	
	public static String REDIRECT_HOME = "http://localhost:8080/fallArm/home.jsp";
	
	public static ArrayList<Patient> GetAllPatient() throws ClassNotFoundException, SQLException {
		ArrayList<Patient> oPatients = new ArrayList<Patient>();
		Patient oPatient = null;
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_ALL_PATIENT);
		while(rs.next()){
			oPatient = new Patient();
			oPatient.setPerson_id(rs.getInt("p_id"));
			oPatient.setPerson_firstname(rs.getString("person_firstname"));
			oPatient.setPerson_lastname(rs.getString("person_lastname"));
			oPatient.setPerson_address(rs.getString("person_address"));
			oPatient.setPerson_gender(rs.getString("person_gender").charAt(0));
			oPatient.setPerson_birth(rs.getDate("person_birth"));
			oPatient.setPatient_id(rs.getInt("patient_id"));
			oPatient.setPatient_level(rs.getInt("patient_level"));
			
			oPatients.add(oPatient);
		}		
		ConnectionDB.stopConnection();
		return oPatients;
	}
	
	public static ArrayList<Nurse> GetAllNurse() throws ClassNotFoundException, SQLException {
		ArrayList<Nurse> oNurses = new ArrayList<Nurse>();
		Nurse oNurse = null;
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_ALL_NURSE);
		while(rs.next()){
			oNurse = new Nurse();
			oNurse.setPerson_id(rs.getInt("p_id"));
			oNurse.setPerson_firstname(rs.getString("person_firstname"));
			oNurse.setPerson_lastname(rs.getString("person_lastname"));
			oNurse.setPerson_address(rs.getString("person_address"));
			oNurse.setPerson_gender(rs.getString("person_gender").charAt(0));
			oNurse.setPerson_birth(rs.getDate("person_birth"));
			oNurse.setNurse_id(rs.getInt("nurse_id"));
			oNurse.setPatient_level(rs.getInt("patient_level"));
			
			oNurses.add(oNurse);
		}
		ConnectionDB.stopConnection();
		return oNurses;
	}
	
	public static ArrayList<Viewer> GetAllViewer() throws ClassNotFoundException, SQLException {
		ArrayList<Viewer> oViewers = new ArrayList<Viewer>();
		Viewer oViewer = null;
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_ALL_VIEWER);
		while(rs.next()){
			oViewer = new Viewer();
			oViewer.setPerson_id(rs.getInt("p_id"));
			oViewer.setPerson_firstname(rs.getString("person_firstname"));
			oViewer.setPerson_lastname(rs.getString("person_lastname"));
			oViewer.setPerson_address(rs.getString("person_address"));
			oViewer.setPerson_gender(rs.getString("person_gender").charAt(0));
			oViewer.setPerson_birth(rs.getDate("person_birth"));
			oViewer.setViewer_id(rs.getInt("viewer_id"));
			oViewer.setViewer_email(rs.getString("viewer_email"));
			oViewer.setView_phone(rs.getString("view_phone"));
			
			oViewers.add(oViewer);
		}
		ConnectionDB.stopConnection();
		return oViewers;
	}
	
	public static ArrayList<Nurse> GetNurseListByLevel(int level) throws SQLException, ClassNotFoundException{
		ArrayList<Nurse> oNurses = new ArrayList<Nurse>();
		Nurse oNurse = null;
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_NURSE_BY_LEVEL + level);
		while(rs.next()){
			oNurse = new Nurse();
			oNurse.setPerson_id(rs.getInt("p_id"));
			oNurse.setPerson_firstname(rs.getString("person_firstname"));
			oNurse.setPerson_lastname(rs.getString("person_lastname"));
			oNurse.setPerson_address(rs.getString("person_address"));
			oNurse.setPerson_gender(rs.getString("person_gender").charAt(0));
			oNurse.setPerson_birth(rs.getDate("person_birth"));
			oNurse.setNurse_id(rs.getInt("nurse_id"));
			oNurse.setPatient_level(rs.getInt("patient_level"));
			
			oNurses.add(oNurse);
		}
		ConnectionDB.stopConnection();
		return oNurses;
	}
	
	public static ArrayList<Patient> GetPatientListByLevel(int level) throws ClassNotFoundException, SQLException{
		ArrayList<Patient> oPatients = new ArrayList<Patient>();
		Patient oPatient = null;
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_PATIENT_BY_LEVEL + level);
		while(rs.next()){
			oPatient = new Patient();
			oPatient.setPerson_id(rs.getInt("p_id"));
			oPatient.setPerson_firstname(rs.getString("person_firstname"));
			oPatient.setPerson_lastname(rs.getString("person_lastname"));
			oPatient.setPerson_address(rs.getString("person_address"));
			oPatient.setPerson_gender(rs.getString("person_gender").charAt(0));
			oPatient.setPerson_birth(rs.getDate("person_birth"));
			oPatient.setPatient_id(rs.getInt("patient_id"));
			oPatient.setPatient_level(rs.getInt("patient_level"));
			
			oPatients.add(oPatient);
		}		
		ConnectionDB.stopConnection();
		return oPatients;
	}
	
	public static boolean IsUserExsist(String s_uname) throws ClassNotFoundException, SQLException{
		return Users.IsUserExist(s_uname);
	}
	
	public static boolean IsPatientExist(int p_id) throws ClassNotFoundException, SQLException{
		return Patient.IsPatientExist(p_id);
	}
	
	public static String GetRoleByUserName(String uname) throws ClassNotFoundException, SQLException{
		Users oUsers = Users.GetUser(uname);
		if(oUsers != null)
			return oUsers.getUser_role();
		return null;
	}
	
	public static int GetPersonIdByUserName(String uname) throws ClassNotFoundException, SQLException{
		Users oUsers = Users.GetUser(uname);
		if(oUsers != null)
			return oUsers.getPerson_id();
		return 0;
	}
	
	public static Viewer GetViewerByUserName(String uname) throws ClassNotFoundException, SQLException{
		int p_id = GetPersonIdByUserName(uname);
		Viewer oViewer = Viewer.GetViewerByPersonId(p_id);
		return oViewer;
	}
	
	public static Nurse GetNurseBYUserName(String uname) throws ClassNotFoundException, SQLException{
		int p_id = GetPersonIdByUserName(uname);
		Nurse oNurse = Nurse.GetNUrseBYPersonId(p_id);
		return oNurse;
	}
	
	public static int GetPatientIdByPersonId(int person_id) throws ClassNotFoundException, SQLException{		
		ConnectionDB.setConnection();
		Statement st = ConnectionDB.conn.createStatement();
		ResultSet rs = st.executeQuery(QUERY_SELECT_PID_BY_PERSONID + person_id);
		int i_p_id = 0;
		while(rs.next()){
			i_p_id = rs.getInt("patient_id");
			break;
		}		
		ConnectionDB.stopConnection();
		return i_p_id;
	}
}
