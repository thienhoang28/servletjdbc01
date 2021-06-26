package com.javaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaweb.model.NewModel;
import com.javaweb.model.UserModel;
import com.javaweb.service.INewService;
import com.javaweb.utils.HttpUtil;
import com.javaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-news"})
public class NewAPI extends HttpServlet{

	private static final long serialVersionUID = 3388997804035947690L;
	
	@Inject
	private INewService newService;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel newmd = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newmd.setCreatedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		newmd = newService.addNew(newmd);
		mapper.writeValue(resp.getOutputStream(), newmd);
	}
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel updNew = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		updNew.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updNew = newService.updateNew(updNew);
		mapper.writeValue(resp.getOutputStream(), updNew);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		NewModel newmd = HttpUtil.of(req.getReader()).toModel(NewModel.class);
		newService.deleteNew(newmd.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}
	
	

}
