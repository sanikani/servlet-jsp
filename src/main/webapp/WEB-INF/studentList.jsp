<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
  <title>student - list</title>
  <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<table>
  <thead>
  <tr>
    <th>아이디</th>
    <th>이름</th>
    <th>성별</th>
    <th>나이</th>
    <th>cmd</th>
  </tr>
  </thead>
  <tbody>
  <!-- 학생 리스트 반복 출력 -->
  <c:forEach var="student" items="${studentList}">
    <tr>
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.gender}</td>
      <td>${student.age}</td>
      <td>
        <a href="${pageContext.request.contextPath}/student/update?id=${student.id}">수정</a>
        <a href="${pageContext.request.contextPath}/student/delete?id=${student.id}">삭제</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>