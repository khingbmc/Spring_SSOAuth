<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/custom.css" rel="stylesheet">
</head>

<body>
<div id="login">
    <h3 class="text-center text-white pt-5">Log in with your account</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="${contextPath}/login" method="POST">
                        <h3 class="text-center text-info">Login</h3>
                        <div class="form-group">
                            <label for="username" class="text-info">Username:</label><br>
                            <input type="text" name="username" id="username" class="form-control" autofocus="true">
                        </div>
                        <div class="form-group">
                            <label for="password" class="text-info">Password:</label><br>
                            <input type="password" name="password" id="password" class="form-control">
                        </div>
                        <span>${error}</span>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <input type="submit" class="btn btn-info btn-md btn-primary" value="Submit">
                        </div><br>
                        <div id="register-link" class="text-right">
                            <a href="${contextPath}/registration" class="text-info">Create an account</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>

</html>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--  <head>--%>
<%--      <meta charset="utf-8">--%>
<%--      <title>Log in with your account</title>--%>

<%--      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">--%>
<%--      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">--%>
<%--  </head>--%>

<%--  <body>--%>

<%--    <div class="container">--%>
<%--      <form method="POST" action="${contextPath}/login" class="form-signin">--%>
<%--        <h2 class="form-heading">Log in</h2>--%>

<%--        <div class="form-group ${error != null ? 'has-error' : ''}">--%>
<%--            <span>${message}</span>--%>
<%--            <input name="username" type="text" class="form-control" placeholder="Username"--%>
<%--                   autofocus="true"/>--%>
<%--            <input name="password" type="password" class="form-control" placeholder="Password"/>--%>
<%--            <span>${error}</span>--%>
<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

<%--            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>--%>
<%--            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>--%>
<%--        </div>--%>
<%--      </form>--%>
<%--    </div>--%>

<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>--%>
<%--    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>--%>
<%--  </body>--%>
<%--</html>--%>
