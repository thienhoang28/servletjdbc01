package com.javaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.model.NewModel;
import com.javaweb.model.UserModel;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.FormUtil;
import com.javaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/home", "/login", "/logout" })
public class HomeController extends HttpServlet{

	private static final long serialVersionUID = -6622126168801261536L;
	
	@Inject
	private ICategoryService cateSer;
	
	@Inject
	private IUserService userService;
	
	ResourceBundle messagebundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			String alert = request.getParameter("alert");
			String message = request.getParameter("message");
			if (message != null && alert != null) {
				request.setAttribute("message", messagebundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}
		else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/home");
		}
		else {
			request.setAttribute("categories", cateSer.readAllCategory());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
		
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel mdel = FormUtil.toModel(UserModel.class, req);
			mdel = userService.readAccount(mdel.getUserName(), mdel.getPassword(), 1);
			if(mdel != null) {
				SessionUtil.getInstance().putValue(req, "USERMODEL", mdel);
				if(mdel.getRole().getCode().equals("user")) {
					resp.sendRedirect(req.getContextPath()+"/home");
				}else if(mdel.getRole().getCode().equals("admin")){
					resp.sendRedirect(req.getContextPath()+"/admin-home");
					
				}
			}else {
				resp.sendRedirect(req.getContextPath()+"/login?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}
}
