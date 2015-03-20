package jado.controller;

import jado.dao.UserDao;
import jado.model.NormalUser;
import jado.model.Seller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class SignUpController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//현재 Url받아올 수 있으면 아래 코드를 주석 해제!!!
		//String fromWhere = req.getParameter("fromWhere");
		//forward(req, resp, fromWhere);
		resp.sendRedirect("/");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//Normal User
		String userId = req.getParameter("userId");
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
			UserDao.insert((Seller)user);
			
		} else {
			user = new NormalUser(userId, password, name, phone, address);
			UserDao.insert(user);
		}
		
		resp.sendRedirect("/");
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String fromWhere)
			throws ServletException, IOException {
		req.setAttribute("fromWhere", fromWhere);
		//top.jspf 수상함. 바꿔야 할 수도 있음.
		RequestDispatcher rd = req.getRequestDispatcher("top.jspf");
		rd.forward(req, resp);
	}
}
