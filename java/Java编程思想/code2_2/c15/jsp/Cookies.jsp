<%--This program has different behaviors under
 different browsers! --%>
<html><body>
<H1>Session id: <%= session.getId() %></H1>
<%
  Cookie[] cookies = request.getCookies();
  for(int i = 0; i < cookies.length; i++) { %>
Cookie name: <%= cookies[i].getName() %> <br>
value: <%= cookies[i].getValue() %><br>
Max age in seconds: 
  <%= cookies[i].getMaxAge() %><br>
<% cookies[i].setMaxAge(3); %>
Max age in seconds: 
  <%= cookies[i].getMaxAge() %><br>
<% response.addCookie(cookies[i]); %>
<% } %>
<%-- <% response.addCookie(
    new Cookie("Bob", "Car salesman")); %> --%>
</body></html>