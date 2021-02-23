<%-- 
    Document   : notificationPage
    Created on : Sep 28, 2020, 10:16:43 PM
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
        </font>
        <c:url var="SearchPage" value="ServletDispatcher">
            <c:param name="btAction" value="Search"/>
        </c:url><br/>
        <a href="${SearchPage}">Click here back to search page!!</a><br/>
        <a href="postArticlePage.jsp">Post the Article</a><br/>
        <a href="ServletDispatcher?btAction=LoadCurrent">View all your Article</a><br/>
        <a href="ServletDispatcher?btAction=Logout">Logout</a><br/>
        
        <h2>Notification</h2>
        <c:set var="listNoti" value="${requestScope.LIST_NOTIFICATION}"/>
        <c:if test="${not empty listNoti}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Email</th>
                        <th>Content</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="notifi" items="${listNoti}">
                        <c:if test="${notifi.email ne sessionScope.EMAIL}">
                            <tr>
                                <td
                                    >${notifi.email}
                                </td>
                                <td>
                                    just ${notifi.type} your post
                                </td>
                                <td>
                                    ${notifi.date}
                                </td>
                                <td>
                                    <c:url var="ViewDetails" value="ServletDispatcher">
                                        <c:param name="txtPostID" value="${notifi.postid}"/>
                                        <c:param name="btAction" value="ViewDetails"/>
                                    </c:url>
                                    <a href="${ViewDetails}">View Details</a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty listNoti}">
            There are currently no announcements!!
        </c:if>
    </body>
</html>
