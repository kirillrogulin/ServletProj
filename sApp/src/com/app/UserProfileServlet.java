package com.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 134L;
	private static final Logger lg = Logger.getLogger(RegistrationServlet.class.getName());
	private final static String TEMPLATE_PATH = "WEB-INF/views/reg_views/";
	
    public UserProfileServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lg.log(Level.INFO, "##### Inside UserProfileServlet->doGet(), started...");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String regOK = request.getParameter("regOK");
		lg.log(Level.INFO, "##### UserProfileServlet->doGet(), regOK = " + regOK);
		if (regOK != null) {
			if (regOK.equals("1")) {
				lg.log(Level.INFO, "##### UserProfileServlet->doGet(), user is registered!");
				response.setStatus(200);
				request.getRequestDispatcher(TEMPLATE_PATH + "profile.html").forward(request, response);
				lg.log(Level.INFO, "##### UserProfileServlet->doGet(), ended.");
				return;
			}
		}
		lg.log(Level.INFO, "##### UserProfileServlet->doGet(), user is not registered!");
		response.setStatus(401);
		request.getRequestDispatcher(TEMPLATE_PATH + "fault_unauthorized.html").forward(request, response);
		lg.log(Level.INFO, "##### UserProfileServlet->doGet(), ended.");
	}

}
