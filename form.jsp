<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%
    String usernameOrEmail = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("usernameOrEmail".equals(cookie.getName())) {
                usernameOrEmail = cookie.getValue();
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Username/Email Form</title>
</head>
<body>
    <h1>Enter your Username or Email</h1>
    <form action="cookies" method="POST">
        <label for="userInput">Username/Email:</label>
        <input type="text" id="userInput" name="userInput" value="<%= usernameOrEmail %>">
        <button type="submit">Submit</button>
    </form>
</body>
</html>