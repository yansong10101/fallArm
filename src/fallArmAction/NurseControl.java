package fallArmAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import fallArmDB.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NurseControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NurseControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = null;
		boolean b_success = false;
		if (request.getParameter("get_nurse") != null){
			Nurse oNurse = this.GetOneNurse(request);
			out.print(oNurse.getNurse_id() + "," + oNurse.getPatient_level() + "," + oNurse.getPerson_firstname() + "," + 
					oNurse.getPerson_lastname() + "," + oNurse.getPerson_address() + "," + oNurse.getPerson_gender()  + "," +
					oNurse.getPerson_birth());
		}else if (request.getParameter("delete_nurse") != null){
			
		}
		out.print("<p>" + s_action + " Request = " + b_success + "!</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = "failed";
		boolean b_success = false;
		if (request.getParameter("add_nurse") != null){			
			try {
				String s_uname = request.getParameter("uname");
				if(DBUtils.IsUserExsist(s_uname))
					out.print("<h2>User name is already exist!!</h2>");
				else{
					b_success = this.AddNurse(request);
					s_action = "add new nurse object";
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.print("<p>" + s_action + " Request = " + b_success + "!</h2>");
	}

	protected boolean AddNurse(HttpServletRequest request) throws ClassNotFoundException{
		boolean b_success = false;
		
		String s_firstname = request.getParameter(DBUtils.FIRSTNAME);
		String s_lastname = request.getParameter(DBUtils.LASTNAME);
		String s_address = request.getParameter(DBUtils.ADDRESS);
		String s_gender = request.getParameter(DBUtils.GENDER);		
		String s_uname = request.getParameter(DBUtils.USERNAME);
		String s_passwd = request.getParameter(DBUtils.PASSWD);
		String s_patient_level = request.getParameter(DBUtils.PATIENTLEVEL);
		
		char c_gender = s_gender.charAt(0);
		System.out.println(s_patient_level);
		int i_patient_level = Integer.parseInt(s_patient_level);
		
		Nurse oNurse = new Nurse(s_firstname, s_lastname, s_address, c_gender, i_patient_level);
		try {
			oNurse.Save();
			Users oUsers = new Users(oNurse.getPerson_id(), s_uname, s_passwd, "n");
			oUsers.Save();
			b_success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return b_success;
	}
	
	// Do selection
	protected Nurse GetOneNurse(HttpServletRequest request){		
		int i_nurse_id = Integer.parseInt(request.getParameter("nurse_id"));
		Nurse oNurse = new Nurse();

		try {
			oNurse = Nurse.GetNurse(i_nurse_id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return oNurse;
	}
}
