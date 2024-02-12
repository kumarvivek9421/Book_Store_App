package web_app_05.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web_app_05.model.DOAService;
import web_app_05.model.DOAServiceImpl;

@WebServlet("/RegServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("email")==null) {
		String Name= request.getParameter("name");
		String Email= request.getParameter("email");
		String Password= request.getParameter("pass");
		String Mobile= request.getParameter("contact");
		
		DOAService ref= new DOAServiceImpl();
		ref.connectDB();
		
		ref.saveRegistration(Name, Email, Password, Mobile);
		
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/registration.jsp");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
