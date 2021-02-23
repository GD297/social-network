<%-- 
    Document   : homePage
    Created on : Sep 15, 2020, 9:20:39 AM
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
        <a href="postArticlePage.jsp">Post the Article</a><br/>
        <a href="ServletDispatcher?btAction=LoadCurrent">View all your Article</a><br/>
        <a href="ServletDispatcher?btAction=Notification">Notification!!</a><br/>
        <a href="ServletDispatcher?btAction=Logout">Logout</a><br/>
        <br/>
        <h2>Search Page</h2>
        <form action="ServletDispatcher">
            Search <input type="text" name="txtSearch" value="${param.txtSearch}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <c:set var="searchValue" value="${param.txtSearch}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="listArticle" value="${requestScope.LIST_ARTICLE}"/>
            <c:if test="${not empty listArticle}">
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
                                    <c:url var="ServletDispatcher" value="ServletDispatcher">
                                        <c:param name="txtUsername" value="${sessionScope.USERNAME}"/>
                                        <c:param name="txtPostID" value="${recordArticle.postID}"/>
                                        <c:param name="btAction" value="ViewDetails"/>
                                    </c:url>
                                    <a href="${ServletDispatcher}">Go to post</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:set var="totalPage" value="${requestScope.TOTAL_PAGE}"/>
                <c:if test="${not empty totalPage}">
                    <c:forEach begin="1" end="${totalPage}" step="1" var="counter">
                        <c:url var="ServletDispatcher" value="ServletDispatcher">
                            <c:param name="btAction" value="Search"/>
                            <c:param name="txtUsername" value="${sessionScope.USERNAME}"/>
                            <c:param name="txtSearch" value="${searchValue}"/>
                            <c:param name="page" value="${counter}"/>
                        </c:url>
                        <a href="${ServletDispatcher}">${counter}</a>
                    </c:forEach>
                </c:if>
            </c:if>

            <c:if test="${empty listArticle}">
                No record found match!!!
            </c:if>
        </c:if>

    </body>
</html>
