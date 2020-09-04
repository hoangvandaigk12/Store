
<%-- 
    Document   : createNewAccount
    Created on : Jun 21, 2020, 3:43:06 PM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <h1>Create New Account</h1>
        <form action="createNewAccount">

            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 to 20 chars)<br/>
            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>

            Password* <input type="password" name="txtPassword" value="" />(6 to 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font><br/>
            </c:if>

            Confirm* <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if>

            Full name <input type="text" name="txtFullname" value="${param.txtFullname}" />(2 to 10 chars)<br/>
            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">
                ${errors.fullnameLengthErr}
                </font><br/>
            </c:if>

            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />           
        </form><br/>
    </body>
</html>
