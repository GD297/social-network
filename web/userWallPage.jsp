<%-- 
    Document   : userWallPage
    Created on : Sep 28, 2020, 4:57:03 PM
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
        <a href="postArticlePage.jsp">Post the Article</a><br/>
        <a href="ServletDispatcher?btAction=Notification">Notification!!</a><br/>
        <a href="ServletDispatcher?btAction=Logout">Logout</a><br/>
        <h1>Your home page</h1>
        <c:set var="listArticle" value="${requestScope.LIST_ARTICLE}"/>
        <c:if test="${not empty listArticle}">
            <h3>All you post here!!!</h3>
            <table border="1">
                <thead>
                    <tr>
                        <th>Post ID</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recordArticle" items="${listArticle}" varStatus="counter">
                        <tr>
                            <td>
                                <c:url var="ServletDispatcher" value="ServletDispatcher">
                                    <c:param name="txtUsername" value="${sessionScope.USERNAME}"/>
                                    <c:param name="txtPostID" value="${recordArticle.postID}"/>
                                    <c:param name="btAction" value="ViewDetails"/>
                                </c:url>
                                <a href="${ServletDispatcher}">${recordArticle.postID}</a>
                            </td>
                            <td>
                                <img src="data:image/png;base64,${recordArticle.image}" width="128" height="128">
                              
                            </td>
                            <td>
                                ${recordArticle.title}
                            </td>
                            <td>
                                ${recordArticle.description}
                            </td>
                            <td>
                                ${recordArticle.date}
                            </td>
                            <td>
                                <c:url var="ViewDetails" value="ServletDispatcher">
                                    <c:param name="txtUsername" value="${sessionScope.USERNAME}"/>
                                    <c:param name="txtPostID" value="${recordArticle.postID}"/>
                                    <c:param name="btAction" value="ViewDetails"/>
                                </c:url>
                                <a href="${ViewDetails}">Go to post</a>
                            </td>
                            <td>
                                <c:url var="DeleteArticle" value="ServletDispatcher">
                                    <c:param name="txtPostID" value="${recordArticle.postID}"/>
                                    <c:param name="btAction" value="DeleteArticle"/>
                                </c:url>
                                <a href="${DeleteArticle}" onclick="return confirm('Are you sure you want to delete this Article?');" >Delete Post</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}"/>

        <c:if test="${empty listArticle}">
            There are no Article here!!!
        </c:if>

    </body>
</html>
