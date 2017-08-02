package com.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.model.User;

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
		lg.log(Level.INFO, "##### Inside RegistrationServlet->doGet(), started...");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.getRequestDispatcher(TEMPLATE_PATH + "registration.html").forward(request, response);
		lg.log(Level.INFO, "##### Inside RegistrationServlet->doGet(), ended!");
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	lg.log(Level.INFO, "##### Inside RegistrationServlet->doPost(), started...");

    	User newUser = null;
    	if (isFilledUser(request)) {
    		newUser = new User();
    		newUser.setUsername(request.getParameter("username"));
    		newUser.setPassword(request.getParameter("password"));
        	response.setContentType("text/html");
    		response.setCharacterEncoding("utf-8");
    		response.setStatus(200);
    		request.getRequestDispatcher(TEMPLATE_PATH + "successful.html").forward(request, response);
    		lg.log(Level.INFO, "##### Inside RegistrationServlet->doGet(), User object created!");
    	} else {
    		response.setContentType("text/html");
    		response.setCharacterEncoding("utf-8");
    		response.setStatus(401);
    		request.getRequestDispatcher(TEMPLATE_PATH + "fault.html").forward(request, response);
    		lg.log(Level.INFO, "Inside RegistrationServlet->doGet(), no User created!");
    	}
    	lg.log(Level.INFO, "##### Inside RegistrationServlet->doGet(), ended!");
	}
    
    private boolean isFilledUser(HttpServletRequest req) {
    	lg.log(Level.INFO, "Inside RegistrationServlet->isFilledUser(), started...");
    	String username = req.getParameter("username");
    	String password = req.getParameter("password");
        if (username != null && password != null) {
        	if (!username.isEmpty() && !password.isEmpty()) {
        		lg.log(Level.INFO, "##### Inside RegistrationServlet->isFilledUser(), returns TRUE!");
        		return true;
        	}
        }
        lg.log(Level.INFO, "##### Inside RegistrationServlet->isFilledUser(), returns FALSE!");
        return false;
    }
}
