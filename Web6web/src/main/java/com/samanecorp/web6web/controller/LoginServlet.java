package com.samanecorp.web6web.controller;

import java.io.IOException;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.web6ejb.dto.UserDTO;
import com.samanecorp.web6ejb.service.SecurityServiceImpl;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	@EJB
	private SecurityServiceImpl loginService;
	private final String LOGIN_PAGE = "index.jsp";
	private final String WELCOME_PAGE = "welcome";
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		loadIndex(null, req, resp);
	}

	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			logger.info("Tentative de connexion avec {} et {}", email, password);
			try {
				Optional<UserDTO> userDtoOption = loginService.login(email, password);
				
				UserDTO userDto = userDtoOption.get();
				req.getSession().setAttribute("username", userDto.getEmail());
				resp.sendRedirect(WELCOME_PAGE);
			} catch (Exception e) {
				String message = "informations de connexion incorrect.";
				logger.error("{}", message);
				loadIndex(message, req, resp);
			}
				
	}
}
