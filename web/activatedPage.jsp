<%-- 
    Document   : activatedPage
    Created on : Sep 29, 2020, 4:18:48 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Social Network</title>
    </head>
    <body>
        <h2>You Are Register Complete!!</h2>
        <h3>Only one step remain to finish setup.</h3>
        <p>Enter OTP code we sent to you with email ${param.txtEmail} to complete!!</p>
        <form action="ServletDispatcher" method="POST">
            <input type="hidden" name="txtEmail" value="${param.txtEmail}" />
            <input type="text" name="txtCodeOTP" value="" />
            <input type="submit" value="Submit" name="btAction"/>
        </form>
        <c:set var="sentOTP" value="${requestScope.SENT_OTP}"/>
        <c:set var="btAction" value="${param.btAction}"/>
        <c:if test="${empty sentOTP or btAction ne 'Search'}">
            <form action="ServletDispatcher">
                <p>Click Here to get OTP</p>
                <input type="hidden" name="txtEmail" value="${param.txtEmail}" />
                <input type="submit" value="Send OTP" name="btAction" />
            </form>
        </c:if>
    </body>
</html>
