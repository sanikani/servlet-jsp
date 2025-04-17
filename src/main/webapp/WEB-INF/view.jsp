<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="cfmt" uri="http://nhnacademy.com/cfmt" %>
<html>
<head>
  <title>학생 상세 정보</title>
  <style>
    table {
      width: 600px;
      border-collapse: collapse;
      border: 1px solid #ccc;
      margin-bottom: 20px;
    }
    th, td {
      padding: 8px 12px;
      border: 1px solid #ccc;
    }
    a {
      margin-right: 10px;
      color: purple;
      text-decoration: none;
    }
    a:hover {
      text-decoration: underline;
    }
    .action-buttons {
      margin-top: 10px;
    }
  </style>
</head>
<body>
<table>
  <tr>
    <th>아이디</th>
    <td>${student.id}</td>
  </tr>
  <tr>
    <th>이름</th>
    <td>${student.name}</td>
  </tr>
  <tr>
    <th>성별</th>
    <td>${student.gender}</td>
  </tr>
  <tr>
    <th>나이</th>
    <td>${student.age}</td>
  </tr>
  <tr>
    <th>등록일</th>
    <td>${cfmt:formatDate(student.createdAt, 'yyyy-MM-dd HH:mm:ss')}</td>
  </tr>
</table>

<div class="action-buttons">
  <a href="${pageContext.request.contextPath}/student/list">리스트</a>
  <a href="${pageContext.request.contextPath}/student/update?id=${student.id}">수정</a>
  <form action="${pageContext.request.contextPath}/student/delete" method="post" style="display:inline;">
    <input type="hidden" name="id" value="${student.id}">
    <input type="submit" value="삭제">
  </form>
</div>
</body>
</html>