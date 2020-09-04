<%-- 
    Document   : update
    Created on : Jun 22, 2020, 9:53:15 AM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dai.dto.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <h1>Update Page</h1>
        <form method="POST" action ="update">
            <c:set var="errors" value="${requestScope.UPDATE_ERROR}"/>
            Username: <input type="text" name="txtUsername" value="${param.txtUsername}" readonly=""/><br/>
            Password: <input type="text" name="txtPassword" value="" required=""/><br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font>
                <br/>
            </c:if>
                Full name: <input type="text" name="txtFullname" value="${param.txtFullname}" readonly="" /><br/>
            Role: <input type="checkbox" name="chkRole" value="ON" 
                         <c:if test="${param.chkRole != null}">
                             checked ="checked"
                         </c:if>
                         /><br/>
            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
            <input type="submit" value="update" name="btAction" />
        </form>
    </body>
</html>
