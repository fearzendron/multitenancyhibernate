<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Tenant: ${tenantID}</h1>

<br/>

<table>
    <c:forEach items="${list}" var="people">
        <tr>
            <td>${people.name}</td>
            <td>${people.lastName}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>