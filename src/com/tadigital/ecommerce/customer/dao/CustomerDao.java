package com.tadigital.ecommerce.customer.dao;
import com.tadigital.ecommerce.customer.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.tadigital.ecommerce.customer.entity.Customer;


public class CustomerDao extends Dao 
{

	//validate email password
	public boolean selectCustomerByEmailAndPassword(Customer customer)
	{
		boolean status=false;
		
		Connection con=openConnection();
		Statement stmt=openStatement(con);
		ResultSet rs=null;
		
		try
		{

			String sql = "SELECT * FROM cust_info WHERE cust_email = '" + customer.getEmail() + "' AND cust_pswd = '" + customer.getPswd() + "'";
		
			rs = stmt.executeQuery(sql);
			if(rs.next()) 
				status = true;	
			System.out.println("status   "+status);
			return status;
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeResultSet(rs);
			closeConnection(con);
		}
		
		return status;
	}

	public boolean insertCustomer(Customer customer) {
		boolean status = false;
		
		Connection con = openConnection();
		Statement stmt = openStatement(con);
		
		try {
			String sql = "INSERT INTO cust_info(cust_fname,cust_lname, cust_email, cust_pswd) " +
						 "VALUES('" + customer.getfName() + "', '"+customer.getlName()+  "', '"+ customer.getEmail() + "', '" + customer.getPswd() + "')";
			
			int rows = stmt.executeUpdate(sql);
			if(rows != 0) {
				status = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			closeStatement(stmt);
			closeConnection(con);
		}
		
		return status;
	}
	
	
}
