<%--Setting and getting session object values--%>
<html><body>
<H1>Session id: <%= session.getId() %></H1>
<H3><li>This session was created at 
<%= session.getCreationTime() %></li></H1>
<H3><li>MaxInactiveInterval= 
  <%= session.getMaxInactiveInterval() %></li></H3>
<% session.setMaxInactiveInterval(5); %>
<H3><li>Reset MaxInactiveInterval= 
  <%= session.getMaxInactiveInterval() %></li></H3>
<H2>If this session object "My dog" is still around <H2>  
<H3><li>Session value for "My dog" =  
<%=
    session.getAttribute("My dog")
%></li></H3>
<%-- Now add the session object "My dog" --%>
<% 
    session.setAttribute("My dog", 
                    new String("Ralph"));
%>
<H1>My dog's name is 
<%= session.getAttribute("My dog") %></H1>
<%-- See if "My dog" wanders to another form --%>
<FORM TYPE=POST ACTION=SessionObject2.jsp>
<INPUT TYPE=submit name=submit Value="Invalidate">
</FORM>
<FORM TYPE=POST ACTION=SessionObject3.jsp>
<INPUT TYPE=submit name=submit Value="Keep Around">
</FORM>
</body></html>