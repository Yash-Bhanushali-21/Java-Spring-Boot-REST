<!DOCTYPE html>
<% 
String logged_in_name = (String) request.getAttribute("user");
%>
<body>
<div class="container authenticated">
  <span>logged in as : <%=logged_in_name%></span>
</div>
</body>
</html>