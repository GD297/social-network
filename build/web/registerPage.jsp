<%-- 
    Document   : registerPage
    Created on : Sep 17, 2020, 9:04:29 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h2>Register Page</h2>
        <form action="ServletDispatcher" method="POST"><br/>
            <c:set var="error" value="${requestScope.ERROR_HAPPEN}"/>
            Email <input type="text" name="txtEmail" value="${param.txtEmail}" /><br/>
            <c:if test="${not empty error.emailLengthErr}">
                <font color="red">
                ${error.emailLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty error.emailDuplicate}">
                <font color="red">
                ${error.emailDuplicate}
                </font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="${param.txtPassword}" /><br/>
            <c:if test="${not empty error.passwordLengthErr}">
                <font color="red">
                ${error.passwordLengthErr}
                </font><br/>
            </c:if>
            Name <input type="text" name="txtName" value="${param.txtName}" /><br/>
            <c:if test="${not empty error.nameLengthErr}">
                <font color="red">
                ${error.nameLengthErr}
                </font><br/>
            </c:if>
            <input type="hidden" name="txtRole" value="Member" /><br/>
            <input type="hidden" name="txtStatus" value="New" /><br/>
            <input type="submit" value="Register" name="btAction" /><br/>
            <input type="reset" value="Reset" />
        </form>
        <a href="loginPage.html">Click here to login!</a>
    </body>
</html>
