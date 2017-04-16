<%--Viewing the attributes in the pageContext--%>
<%-- Note that you can include any amount of code
inside the scriptlet tags --%>
<%@ page import="java.util.*" %>
<html><body>
<%
  session.setAttribute("My dog", "Ralph");
  for(int scope = 1; scope <= 4; scope++) {  
    out.println("<H3>Scope: " + 
                 scope + "</H3><BR>");
    Enumeration e =
      pageContext.getAttributeNamesInScope(scope);
    while(e.hasMoreElements()) {
      out.println("\t<li>" + 
        e.nextElement() + "</li>");
    }
  }
%>
<H4>End of list</H4>
</body></html>