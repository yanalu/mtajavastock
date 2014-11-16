package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Project2Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//exercise 2
				int num1=4;
				int num2=3;
				int num3=7;
				int result= (num1+num2)*num3;
				//String resultStr= new String ("<h1>Result of "+"("+num1+"+"+num2+")"+"*"+num3+" = "+result+ "</h1>");
				resp.setContentType("text/html");
		
	
		//exercise 3: 18/11/14
		double Radios=50; 
		double areaOfCircle=Math.PI *Math.pow(Radios, 2);
		String line1=new String ("calculation 1: area of circle with radios: " +Radios+ " is:"+ areaOfCircle+" square-cm");
		
		double B=30;
		double hypotenuse_length=50;	
		double opposite_length=Math.sin(Math.toRadians(B))*hypotenuse_length;
		String line2=new String ("Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: "+opposite_length+" cm");
		
		double result_of_pow=Math.pow(20,13);
		String line3=new String ("Power of 20 with exp of 13 is "+result_of_pow);
		
		String resultStr=line1 +"<br>" +line2 +"<br>"+line3;
		resp.getWriter().println(resultStr);
	}
}