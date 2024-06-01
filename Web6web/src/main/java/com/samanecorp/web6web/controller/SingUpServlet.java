package com.samanecorp.web6web.controller;

import java.io.IOException;


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


/**
 * Servlet implementation class SingUpServlet
 */
@WebServlet(name = "singup", value = "/singup")
public class SingUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	@EJB
	private SecurityServiceImpl loginService;
	
	private final String LOGIN_PAGE = "index.jsp";
	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loadIndex(null, req, resp);
	}

	private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", message);
		req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("mail");
		String password = req.getParameter("pwd");
		logger.info("Tentative d'inscription avec {}", email);
		String message = null;
		try {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(email);
			userDTO.setPassword(password);
			int result = loginService.save(userDTO);
			if (result == 1) {
				message = "informations ajoutées dans la base de données.";
				logger.info("{}", message);
			}
		} catch (Exception e) {
			message = "informations non ajoutées.";
			logger.error("{}", message);
			logger.error("Error", e);
		}
		loadIndex(message, req, resp);
	}

}
