package fallArmAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fallArmDB.*;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession(true);
		response.setContentType("text/html");
		String name = request.getParameter("uname");
		String s_role;
		try {
			s_role = DBUtils.GetRoleByUserName(name);
			if(s_role==null)
				out.print("<h2>User name or password is incorrect!!</h2>");
			else if(s_role.equals("n")){
				Nurse oNurse = DBUtils.GetNurseBYUserName(name);
				request.setAttribute("nurse", oNurse);
				getServletContext().getRequestDispatcher("/nurse_home.jsp").forward(request, response);
			}
			else if(s_role.equals("v")){
				Viewer oViewer = DBUtils.GetViewerByUserName(name);
				request.setAttribute("viewer", oViewer);
				System.out.println(oViewer.getPatient_id() + "*********************");
				getServletContext().getRequestDispatcher("/viewer_home.jsp").forward(request, response);
			}
			else if(s_role.equals("a")){
				getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
