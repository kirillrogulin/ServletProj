package com.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultServlet extends HttpServlet {
	private static final long serialVersionUID = 12222L;
	private static final Logger lg = Logger.getLogger(DefaultServlet.class.getName());
	private final static String TEMPLATE_PATH = "WEB-INF/views/";
	
    public DefaultServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		lg.log(Level.INFO, "##### DefaultServlet->doGet(), started...");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.setStatus(200);
		request.getRequestDispatcher(TEMPLATE_PATH + "index.html").forward(request, response);
		lg.log(Level.INFO, "##### DefaultServlet->doGet(), ended!");
	}
}
