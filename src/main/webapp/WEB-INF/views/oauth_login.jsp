<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Login with:</h3>

<c:forEach items="${urls}" var="entry">
<a href="/${entry.value}">${entry.key}</a>
</c:forEach>