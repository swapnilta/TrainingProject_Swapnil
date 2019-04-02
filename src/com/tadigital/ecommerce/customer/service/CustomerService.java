package com.tadigital.ecommerce.customer.service;

import com.tadigital.ecommerce.customer.entity.Customer;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.tadigital.ecommerce.customer.dao.*;
import com.tadigital.ecommerce.customer.entity.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.Part;


public class CustomerService {
	CustomerDao customerDao = new CustomerDao();
		
	public boolean loginCustomer(Customer customer) {
		CustomerDao customerDao=new CustomerDao();
		boolean status = customerDao.selectCustomerByEmailAndPassword(customer);
		
		return status;
	}
	
	public boolean registerCustomer(Customer customer) {
		boolean status = customerDao.insertCustomer(customer);
		if(status)
		{
			sendWelcomeMail(customer.getfName() + " " + customer.getlName(), customer.getEmail());
		}
		
		return status;
	}
	
	private String processFileUpload(Collection<Part> partsCollection, String uploadFilePath) {
		String fileName = "";
		
		for (Part part : partsCollection) {
        	String contentDisp = part.getHeader("content-disposition");
            String[] items = contentDisp.split(";");
            
            for (String item : items) {
                if (item.trim().startsWith("filename")) {
                    fileName = item.substring(item.indexOf("=") + 2, item.length()-1);
                    
                    try {
						part.write(uploadFilePath + File.separator + fileName);
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
                }
            }          
        }
		
		return fileName;
	}
	
	public String sendWelcomeMail(String name, String email) {
		String status = "NOT SENT";
		
		//MAIL SERVER CONFIGURATION
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",	"javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "465");
		
		//CONNECT TO MAIL SERVER
		Session session = Session.getDefaultInstance(properties,new javax.mail.Authenticator() {
																	protected PasswordAuthentication getPasswordAuthentication() {
																		return new PasswordAuthentication("jaindes@gmail.com","pleasesmile");
																	}
															});

		try {
			//COMPOSE MESSAGE
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("jaindes@gmail.com"));
			mimeMessage.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			mimeMessage.setSubject("Welcome to the world of TA Digital!");
			MimeMultipart mp = new MimeMultipart();
			MimeBodyPart mbp1= new MimeBodyPart();
			
						
			String msg ="<body bgcolor='#DAF7A6'>Dear <i><font color='#FF7A33'>" + name + "</font></i>,<br/><br/>" +
					 "<img src='cid:image1'><br/><h2><font color='#C70039'>Welcome to TA Digital Family!</font></h2>" +
					 "<br/><font color='#900C3F'>We are excited to have you on-board.</font>" +
					 "<br/><br/><br/><font color='#581845'>Stay tuned and we have got some great things in store for you.</font>" +
					 "<br/><br/><br/><font color='#FFC300'>Happy Shopping!<br/>TA Digital<br/><br/></font><img src='cid:image'></body>";
			mbp1.setContent(msg,"text/html");
			mp.addBodyPart(mbp1);
			
			mbp1 = new MimeBodyPart();
			DataSource fds = new FileDataSource("D://Trainee Engineers March 2019/workspase/TrainingProject_Swapnil/WebContent/images/logo.png");
			mbp1.setDataHandler(new DataHandler(fds));
			mbp1.setHeader("Content-ID", "<image>");
			mp.addBodyPart(mbp1);
			
			mbp1 = new MimeBodyPart();
			fds = new FileDataSource("D://Trainee Engineers March 2019/workspase/TrainingProject_Swapnil/WebContent/images/welcome.png");
			mbp1.setDataHandler(new DataHandler(fds));
			mbp1.setHeader("Content-ID", "<image1>");
			mp.addBodyPart(mbp1);
				
			mimeMessage.setContent(mp)
;
				

			//SEND MAIL
			Transport.send(mimeMessage);
			
			status = "SENT";
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		
		return status;
	
	}

	
}