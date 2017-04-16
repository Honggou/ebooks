<%-- Fetching the data from an HTML form. --%>
<%-- This JSP also generates the form. --%>
<%@ page import="java.util.*" %>
<html><body>
<H1>DisplayFormData</H1><H3>
<%
Enumeration f = request.getParameterNames();
if(!f.hasMoreElements()) { // No fields
%>
<form method="POST" action="DisplayFormData.jsp">
<%
  for(int i = 0; i < 10; i++) {
%>
    Field<%=i%>:
    <input type="text" size="20" 
      name="Field<%=i%>" value="Value<%=i%>"><br>
<% } %>
  <INPUT TYPE=submit name=submit Value="Submit">
</form>
<% } %>
<%
Enumeration flds = request.getParameterNames();
while(flds.hasMoreElements()) {
  String field = (String)flds.nextElement();
  String value = request.getParameter(field);
%>
  <li><%= field %> = <%= value %></li>
<%
} 
%></H3></body></html>