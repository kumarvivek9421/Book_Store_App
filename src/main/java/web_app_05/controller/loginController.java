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

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("user_email");
		String pass = request.getParameter("password");
		
		DOAService service= new DOAServiceImpl();
		service.connectDB();
		
		boolean status = service.verifyCredentials(email, pass);
		
		if (status==true) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(15);
			
			RequestDispatcher rd1 = request.getRequestDispatcher("WEB-INF/views/index.jsp");
			rd1.forward(request, response);
			System.out.println("welcome");
		}else {
			request.setAttribute("Error", "Invalid username/password");
			
			RequestDispatcher rd2 = request.getRequestDispatcher("login.jsp");
			rd2.forward(request, response);
			System.out.println("invalid");
		}
	}
}
