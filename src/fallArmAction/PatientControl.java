package fallArmAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import fallArmDB.DBUtils;
import fallArmDB.Patient;

public class PatientControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int i_tmp_pid;   
	
    public PatientControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = null;
		boolean b_success = false;
		if (request.getParameter("get_patient") != null){
			Patient oPatient = this.GetOnePatient(request);
			out.print(oPatient.getPatient_id() + "," + oPatient.getPatient_level() + "," + oPatient.getPerson_firstname() + "," + 
					  oPatient.getPerson_lastname() + "," + oPatient.getPerson_address() + "," + oPatient.getPerson_gender()  + "," +
					  oPatient.getPerson_birth());
		}else if (request.getParameter("delete_patient") != null){
			
		}
		out.print("<p>" + s_action + " Request = " + b_success + "!</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = null;
		boolean b_success = false;
		if (request.getParameter("add_patient") != null){
			try {
				b_success = this.AddPatient(request);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s_action = "add new patient object";
		}
		out.print("<h2>" + s_action + " Request = " + b_success + "!</h2>");
		if(b_success)
			out.print("<h2>New patient ID is : " + i_tmp_pid + "!</h2>");
	}

	// Do insertion 
	protected boolean AddPatient(HttpServletRequest request) throws ClassNotFoundException{
		boolean b_success = false;
		
		String s_firstname = request.getParameter(DBUtils.FIRSTNAME);
		String s_lastname = request.getParameter(DBUtils.LASTNAME);
		String s_address = request.getParameter(DBUtils.ADDRESS);
		String s_gender = request.getParameter(DBUtils.GENDER);
		String s_year = request.getParameter(DBUtils.BIRTHDAY_YEAR);
		String s_month = request.getParameter(DBUtils.BIRTHDAY_MONTH);
		String s_day = request.getParameter(DBUtils.BIRTHDAY_DAY);
		String s_level = request.getParameter(DBUtils.LEVEL);
		
		char c_gender = s_gender.charAt(0);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(s_year), Integer.parseInt(s_month) - 1, Integer.parseInt(s_day));	   
		Date dt_birthday = cal.getTime();
		int i_level = Integer.parseInt(s_level);
		
		Patient oPatient = new Patient(s_firstname, s_lastname, s_address, c_gender, dt_birthday, i_level);		
		try {
			oPatient.Save();
			i_tmp_pid = DBUtils.GetPatientIdByPersonId(oPatient.getPerson_id());
			b_success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b_success;
	}
	
	// Do selection
	protected Patient GetOnePatient(HttpServletRequest request){		
		int i_patient_id = Integer.parseInt(request.getParameter("patient_id"));
		Patient oPatient = new Patient();

		try {
			oPatient = Patient.GetPatient(i_patient_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return oPatient;
	}
}
