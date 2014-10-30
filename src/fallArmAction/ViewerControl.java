package fallArmAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import fallArmDB.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ViewerControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = null;
		boolean b_success = false;
		if (request.getParameter("get_viewers") != null){
			ArrayList<Viewer> oViewerList = this.GetViewerList(request);
			for(int i = 0; i < oViewerList.size(); i++)
			{
				out.print("<p>" + oViewerList.get(i).toString() + "</p>");
			}
		}else if (request.getParameter("delete_viewers") != null){
			
		}
		out.print("<p>" + s_action + " Request = " + b_success + "!</h2>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String s_action = "failed";
		boolean b_success = false;
		if (request.getParameter("add_viewer") != null){
			try {
				String s_uname = request.getParameter("uname");
				if(DBUtils.IsUserExsist(s_uname))
					out.print("<h2>User name is already exist!!</h2>");
				else{
					b_success = this.AddViewer(request);
					s_action = "add new viewer object";
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		out.print("<h2>" + s_action + " Request = " + b_success + "!</h2>");
	}
	
	protected boolean AddViewer(HttpServletRequest request) throws ClassNotFoundException{
		boolean b_success = false;
		
		String s_firstname = request.getParameter(DBUtils.FIRSTNAME);
		String s_lastname = request.getParameter(DBUtils.LASTNAME);
		String s_address = request.getParameter(DBUtils.ADDRESS);
		String s_email = request.getParameter(DBUtils.EMAIL);
		String s_phone = request.getParameter(DBUtils.PHONE);
		String s_patient_id = request.getParameter("patient_id");		
		String s_uname = request.getParameter(DBUtils.USERNAME);
		String s_passwd = request.getParameter(DBUtils.PASSWD);		
		
		int i_patient_id = Integer.parseInt(s_patient_id);
		
		Viewer oViewer = new Viewer(s_firstname, s_lastname, s_address, s_email, s_phone, i_patient_id);		
		try {
			oViewer.Save();
			Users oUsers = new Users(oViewer.getPerson_id(), s_uname, s_passwd, "v");
			oUsers.Save();
			b_success = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b_success;
	}
	
	// Do selection
	protected ArrayList<Viewer> GetViewerList(HttpServletRequest request){
		int i_patient_id = Integer.parseInt(request.getParameter("patient_id"));
		ArrayList<Viewer> v_List = new ArrayList<Viewer>();
		try {
			v_List = Viewer.GetViewers(i_patient_id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return v_List;
	}
}
