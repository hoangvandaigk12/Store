<%-- 
    Document   : header
    Created on : Jul 12, 2020, 12:56:45 PM
    Author     : AD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${sessionScope.ACCOUNT != null}">
    <form action="logout">
        <font color="red">Welcome, ${sessionScope.ACCOUNT.username}</font>
        <input type="submit" value="Logout" name="btAction" /><br/>
    </form>
    
</c:if>
