<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
  <title>Error Page</title>
</head>
<body>

<table>
  <tbody>
  <tr>
    <th>status_code</th>
    <td><c:out value="${statusCode}" /></td>
  </tr>
  <tr>
    <th>exception_type</th>
    <td><c:out value="${exceptionType}" /></td>
  </tr>
  <tr>
    <th>message</th>
    <td><c:out value="${message}" /></td>
  </tr>
  <tr>
    <th>exception</th>
    <td><c:out value="${exception}" /></td>
  </tr>
  <tr>
    <th>request_uri</th>
    <td><c:out value="${requestURI}" /></td>
  </tr>

  </tbody>

</table>
</body>
</html>