<%-- 
    Document   : postArticlePage
    Created on : Sep 28, 2020, 1:23:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Social Network</title>
    </head>
    <body>
        <font color="red">
        <c:if test="${not empty sessionScope.USERNAME}">
            Welcome ${sessionScope.USERNAME}
        </c:if>
        <c:if test="${empty sessionScope.USERNAME}">
            <c:redirect url="loginPage.html"/>
        </c:if>
        </font><br/>
        <c:url var="SearchPage" value="ServletDispatcher">
            <c:param name="btAction" value="Search"/>
        </c:url>
        <a href="${SearchPage}">Click here back to search page!!</a><br/>
        <a href="ServletDispatcher?btAction=LoadCurrent">View all your Article</a><br/>
        <a href="ServletDispatcher?btAction=Notification">Notification!!</a><br/>
        <a href="ServletDispatcher?btAction=Logout">Logout</a><br/><br/>
        <h2>Post Article on Social Network</h2>
        <form action="ServletDispatcher" method="POST" enctype="multipart/form-data" accept="image/*" >
            <c:set var="errors" value="${requestScope.ERROR_HAPPEN}"/>
            Title <input type="text" name="txtTitle" value="" /><br/>
            <c:if test="${not empty errors.titleErrLength}">
                <font color="red">
                ${errors.titleErrLength}
                </font><br/>
            </c:if>
            Description <input type="text" name="txtDescription" value="" /><br/>
            <c:if test="${not empty errors.desErrLength}">
                <font color="red">
                ${errors.desErrLength}
                </font><br/>
            </c:if>
            Select image: <input type="file" name="Image" accept="image/*"/><br/>
            <input type="hidden" name="txtStatus" value="Active" />
            <input type="submit" value="Post" name="btAction" /><br/>
        </form>
    </body>
</html>
