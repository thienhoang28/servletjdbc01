package com.javaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.constant.SystemConstant;
import com.javaweb.model.NewModel;
import com.javaweb.paging.IPageable;
import com.javaweb.paging.PageRequest;
import com.javaweb.service.ICategoryService;
import com.javaweb.service.INewService;
import com.javaweb.sort.Sorter;
import com.javaweb.utils.FormUtil;
import com.javaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = -2662059961810446402L;

	@Inject
	private INewService newService;
	
	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NewModel mdel = FormUtil.toModel(NewModel.class, request);
		String view = "";
		if (mdel.getType().equals(SystemConstant.LIST)) {
			
			IPageable pageable = new PageRequest(mdel.getPage(), mdel.getMaxPageItem(),
					new Sorter(mdel.getSortName(), mdel.getSortBy()));
			mdel.setListResult(newService.readAll(pageable));
			mdel.setTotalItem(newService.getTotalItemDB());
			mdel.setTotalPage((int) Math.ceil((double) mdel.getTotalItem() / mdel.getMaxPageItem()));
			view = "/views/admin/new/list.jsp";
		}
		else if (mdel.getType().equals(SystemConstant.EDIT)) {
			if(mdel.getId() != null) {
				mdel = newService.findOne(mdel.getId());
			}
			request.setAttribute("categories", categoryService.readAllCategory());
			view = "/views/admin/new/edit.jsp";
		}
		MessageUtil.showMessage(request);
		request.setAttribute(SystemConstant.MODEL, mdel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
