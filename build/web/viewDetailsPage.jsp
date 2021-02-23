<%-- 
    Document   : viewDetailsPage
    Created on : Sep 26, 2020, 4:10:57 PM
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
        <a href="ServletDispatcher?btAction=LoadCurrent">View all your Article</a><br/>
        <a href="ServletDispatcher?btAction=Notification">Notification!!</a><br/>
        <a href="ServletDispatcher?btAction=Logout">Logout</a><br/>
        <c:set var="Article" value="${requestScope.ARTICLE_DETAILS}"/>
        <c:set var="likeNumber" value="${requestScope.LIKE_NUMBER}"/>
        <c:set var="dislikeNumber" value="${requestScope.DISLIKE_NUMBERE}"/>
        <c:set var="listComment" value="${requestScope.LIST_COMMENTS}"/>
        <c:if test="${not empty Article}">
            <c:set var="Active" value="Active"/>
            <c:set var="Delete" value="Delete"/>
            <p>${Article.status} ${Active}</p>

            <h2>
                ${Article.title}
            </h2>
            <p>
                ${Article.date} by ${Article.email}
            </p>
            <p>
                ${Article.description}
            </p>
            <img src="data:image/png;base64,${Article.image}"/>
            <p>
                ${likeNumber} <a href="ServletDispatcher?btAction=MakeEmotion&typeEmotion=1&txtPostID=${param.txtPostID}">Like</a> 
                ${dislikeNumber} <a href="ServletDispatcher?btAction=MakeEmotion&typeEmotion=0&txtPostID=${param.txtPostID}">Dislike</a> 
            </p>
            <br/>
            <p>Comments</p>
            <c:if test="${not empty listComment}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Content</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="comment" items="${listComment}">

                            <tr>
                                <td>
                                    ${comment.email}
                                </td>
                                <td>
                                    ${comment.content}
                                </td>
                                <td>
                                    ${comment.date}
                                </td>
                                <c:if test="${comment.email eq sessionScope.EMAIL}">
                                    <td>
                                        <c:url var="DeleteComment" value="ServletDispatcher">
                                            <c:param name="btAction" value="DeleteComment"/>
                                            <c:param name="txtEmail" value="${comment.email}"/>
                                            <c:param name="txtContent" value="${comment.content}"/>
                                            <c:param name="txtPostID" value="${Article.postID}"/>
                                            <c:param name="Date" value="${comment.date}"/>
                                        </c:url>
                                        <a href="${DeleteComment}">Delete</a>
                                    </td>
                                </c:if>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <form action="ServletDispatcher">
                <textarea name="txtComment" rows="10" cols="50" ></textarea><br/>
                <input type="hidden" name="txtFullName" value="${sessionScope.USERNAME}" />
                <input type="hidden" name="txtEmail" value="${sessionScope.EMAIL}" />
                <input type="hidden" name="txtPostID" value="${param.txtPostID}" />
                <input type="submit" value="Comments" name="btAction" />
            </form>

        </c:if>
        <c:if test="${empty Article}">
            <h2>Sorry this article has been remove</h2>
        </c:if>
    </body>
</html>
