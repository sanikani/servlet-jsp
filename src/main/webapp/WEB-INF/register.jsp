<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>학생 등록</title>
</head>
<body>
<h1>학생 등록</h1>
<form method="post" action="/student/register">
  <table border="1" cellspacing="0" cellpadding="5">
    <tr>
      <td>ID</td>
      <td><input type="text" name="id" required></td>
    </tr>
    <tr>
      <td>이름</td>
      <td><input type="text" name="name" required></td>
    </tr>
    <tr>
      <td>성별</td>
      <td>
        <input type="radio" name="gender" value="M" required>남
        <input type="radio" name="gender" value="F">여
      </td>
    </tr>
    <tr>
      <td>나이</td>
      <td><input type="number" name="age" required></td>
    </tr>
  </table>
  <br>
  <button type="submit">등록</button>
</form>
</body>
</html>
