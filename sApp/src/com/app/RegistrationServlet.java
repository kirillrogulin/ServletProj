package com.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.HttpStatus;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 12223L;
	private static final Logger lg = Logger.getLogger(RegistrationServlet.class.getName());
	private final static String TEMPLATE_PATH = "WEB-INF/views/reg_views/";
	
    public RegistrationServlet() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), started...");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.getRequestDispatcher(TEMPLATE_PATH + "registration.html").forward(request, response);
		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), ended!");
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	lg.log(Level.INFO, "##### RegistrationServlet->doPost(), started...");

    	String username = "";
    	String pass = "";
    	switch(isFilledUser(request)) {
	    	case OK:
	    		username = request.getParameter("username");
	    		pass = request.getParameter("password");
	    		response.setContentType("text/html");
	    		response.setCharacterEncoding("utf-8");
	    		response.setStatus(200);
	    		request.getRequestDispatcher(TEMPLATE_PATH + "successful.html").forward(request, response);
	    		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), User object created!");
	    		break;
	    	case UNAUTHORIZED:
	    		response.setContentType("text/html");
	    		response.setCharacterEncoding("utf-8");
	    		response.setStatus(401);
	    		request.getRequestDispatcher(TEMPLATE_PATH + "fault_unauthorized.html").forward(request, response);
	    		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), no User created!");
	    		break;
	    	case NO_PASSWORD:
	    		response.setContentType("text/html");
	    		response.setCharacterEncoding("utf-8");
	    		response.setStatus(401);
	    		request.getRequestDispatcher(TEMPLATE_PATH + "fault_nopass.html").forward(request, response);
	    		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), no User created!");
	    		break;
	    	case BAD_POST_REQUEST:
	    		response.setContentType("text/html");
	    		response.setCharacterEncoding("utf-8");
	    		response.setStatus(401);
	    		request.getRequestDispatcher(TEMPLATE_PATH + "fault_badpost.html").forward(request, response);
	    		lg.log(Level.INFO, "##### RegistrationServlet->doGet(), no User created!");
	    		break;
    	}
    	
    	lg.log(Level.INFO, "##### RegistrationServlet->doGet(), ended!");
	}
    
    private HttpStatus isFilledUser(HttpServletRequest req) {
    	lg.log(Level.INFO, "Inside RegistrationServlet->isFilledUser(), started...");
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
        if (username != null && password != null) {
        	if (!username.isEmpty() && !password.isEmpty()) {
        		lg.log(Level.INFO, "##### RegistrationServlet->isFilledUser(), returns TRUE with HttpStatus.OK!");
        		return HttpStatus.OK;
        	} else {
        		if (!username.isEmpty() && password.isEmpty()) {
        			lg.log(Level.INFO, "##### RegistrationServlet->isFilledUser(), returns TRUE with HttpStatus.NO_PASSWORD!");
        			return HttpStatus.NO_PASSWORD;
        		} else {
        			lg.log(Level.INFO, "##### RegistrationServlet->isFilledUser(), returns TRUE with HttpStatus.UNAUTHORIZED!");
        			return HttpStatus.UNAUTHORIZED;
        		}
        	}
        }
        lg.log(Level.INFO, "##### RegistrationServlet->isFilledUser(), returns FALSE with HttpStatus.BAD_POST_REQUEST!");
        return HttpStatus.BAD_POST_REQUEST;
    }
}
