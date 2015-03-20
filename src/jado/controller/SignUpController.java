package jado.controller;

import jado.dao.UserDao;
import jado.model.Customer;
import jado.model.Seller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.exception.DuplicateUserException;

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

		//Customer
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String checkPassword = req.getParameter("checkPassword");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		
		Customer user = new Customer(userId, password, name, phone, address);
		
		try{
			user.signUp(checkPassword);
		} catch(DuplicateUserException e){
			req.setAttribute("errorMessage", "이미 아이디가 존재 합니다. ");
		} catch(PasswordMismatchException e){
			req.setAttribute("errorMessage", "비밀번호가 일치하지 않습니다. 다시 입력해 주세요");			
		}

		//Seller
		if (req.getParameter("isSeller") != null) {
			String shopUrl = req.getParameter("shopUrl");
			String shopPhone = req.getParameter("shopPhone");
			String bank = req.getParameter("bank");
			String bankAccount = req.getParameter("bankAccount");
			UserDao.insert(new Seller(userId, shopUrl, shopPhone, bank, bankAccount));
		}
		
		resp.sendRedirect("/signUp.jsp");
	}

	private void forward(HttpServletRequest req, HttpServletResponse resp, String fromWhere)
			throws ServletException, IOException {
		req.setAttribute("fromWhere", fromWhere);
		//top.jspf 수상함. 바꿔야 할 수도 있음.
		RequestDispatcher rd = req.getRequestDispatcher("top.jspf");
		rd.forward(req, resp);
	}
}
