//: c15:servlets:EchoForm.java
// From 'Thinking in Java, 2nd ed.' by Bruce Eckel
// www.BruceEckel.com. See copyright notice in CopyRight.txt.
// Dumps the name-value pairs of any HTML form
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class EchoForm extends HttpServlet {
  public void doGet(HttpServletRequest req, 
    HttpServletResponse res) throws IOException {
    res.setContentType("text/html");
    PrintWriter out = res.getWriter();
    out.print("<h1>Your form contained:</h1>");
    Enumeration flds = req.getParameterNames();
    while(flds.hasMoreElements()) {
      String field = (String)flds.nextElement();
      String value = req.getParameter(field);
      out.print(field + " = " + value + "<br>");
    }
    out.close();    
  }
  public void doPost(HttpServletRequest req, 
    HttpServletResponse res) throws IOException {
    doGet(req, res);
  }  
} ///:~