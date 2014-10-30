package fallArmAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("listallpatients")!=null)
			getServletContext().getRequestDispatcher("/listAllPatient.jsp").forward(request, response);
		else if(request.getParameter("listallnurses")!=null){
			getServletContext().getRequestDispatcher("/listAllNurse.jsp").forward(request, response);
		}
		else if(request.getParameter("listallviewers")!=null){
			getServletContext().getRequestDispatcher("/listAllViewer.jsp").forward(request, response);
		}
		else if(request.getParameter("patient_level")!=null){
			int i_level = Integer.parseInt(request.getParameter("patient_level"));
			request.setAttribute("patient_level", i_level);
			System.out.println("********");
			getServletContext().getRequestDispatcher("/listByGroup.jsp?patient_level=" + i_level).forward(request, response);
		}
		System.out.println("############");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
