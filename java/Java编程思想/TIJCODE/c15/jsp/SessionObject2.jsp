<%--The session object carries through--%>
<html><body>
<H1>Session id: <%= session.getId() %></H1>
<H1>Session value for "My dog" 
<%= session.getValue("My dog") %></H1>
<% session.invalidate(); %>
</body></html>