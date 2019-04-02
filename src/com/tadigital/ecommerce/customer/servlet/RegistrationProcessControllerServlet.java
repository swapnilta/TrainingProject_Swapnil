package com.tadigital.ecommerce.customer.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tadigital.ecommerce.customer.entity.*;
import com.tadigital.ecommerce.customer.service.*;

@WebServlet("/register")
public class RegistrationProcessControllerServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("SignInSignUpForms.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Name = req.getParameter("f1");
		String email = req.getParameter("f2");
		String password = req.getParameter("f3");
		
		String fName = Name.substring(0, Name.indexOf(' '));
		String lName = Name.substring(Name.indexOf(' ') + 1);
		
		Customer customer = new Customer();
		customer.setfName(fName);
		customer.setlName(lName);
		customer.setEmail(email);
		customer.setPswd(password);
		
		CustomerService customerService = new CustomerService();
		boolean status = customerService.registerCustomer(customer);
		if(status) 
		{
			req.setAttribute("reg", "Y");
			RequestDispatcher rd = req.getRequestDispatcher("SignInSignUpForms.jsp");
			rd.forward(req, resp);
		} else 
		{
			req.setAttribute("reg", "N");
			RequestDispatcher rd = req.getRequestDispatcher("SignInSignUpForms.jsp");
			rd.forward(req, resp);
		}
	}
}