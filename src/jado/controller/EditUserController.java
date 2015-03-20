package jado.controller;

import jado.dao.UserDao;
import jado.model.NormalUser;
import jado.model.Seller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/edit")
public class EditUserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");

		NormalUser user = UserDao.findUser(userId); 
		req.setAttribute("user", user);
		resp.sendRedirect("/");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		//Normal User
		String userId = (String)session.getAttribute("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		NormalUser user = null;

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = req.getParameter("shopUrl");
			String shopPhone = req.getParameter("shopPhone");
			String bank = req.getParameter("bank");
			String bankAccount = req.getParameter("bankAccount");
			user = new Seller(userId, password, name, phone, address, shopUrl, shopPhone, bank, bankAccount);
			UserDao.update(user);
			
		} else {
			user = new NormalUser(userId, password, name, phone, address);
			UserDao.update(user);
		}
		
		resp.sendRedirect("/");
		//userdao.update();
		
	}
}