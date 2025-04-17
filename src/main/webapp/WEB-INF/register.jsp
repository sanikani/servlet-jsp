<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>학생 등록</title>
</head>
<body>
<h1>학생 등록</h1>
<form method="post" action="${action}">
  <table border="1" cellspacing="0" cellpadding="5">
    <tr>
      <td>ID</td>
      <td>
        <c:choose>
          <c:when test="${empty student}">
            <input type="text" name="id" value="${student.id}" required />
          </c:when>
          <c:otherwise>
            <input type="text" name="id" value="${student.id}" readonly />
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>이름</td>
      <td><input type="text" name="name" value="${student.name}" required></td>
    </tr>
    <tr>
      <td>성별</td>
      <td>
        <c:choose>
          <c:when test="${empty student}">
            <input type="radio" name="gender" value="M" required>남
            <input type="radio" name="gender" value="F">여
          </c:when>
          <c:otherwise>
            ${student.gender == 'M' ? '남' : '여'}
            <input type="hidden" name="gender" value="${student.gender}" />
          </c:otherwise>
        </c:choose>
      </td>
    </tr>
    <tr>
      <td>나이</td>
      <td><input type="number" name="age" value="${student.age}" required></td>
    </tr>
  </table>
  <p>
    <button type="submit">
      <c:choose>
        <c:when test="${empty student}">
          등록
        </c:when>
        <c:otherwise>
          수정
        </c:otherwise>
      </c:choose>
    </button>
  </p>
</form>
</body>
</html>
