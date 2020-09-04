<%-- 
    Document   : oder_error
    Created on : Jul 14, 2020, 1:44:09 AM
    Author     : AD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Error</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <h1>Order Error</h1>
        <form action="checkout" method="POST">
            <c:set var="errors" value="${requestScope.ORDER_ERROR}"/>
            Customer Name: <input type="text" name="txtCusName" value="${param.txtCusName}" /><br/>
            <c:if test="${not empty errors.fullnameLenghtErr}">
                <font color="red">${errors.fullnameLenghtErr}</font><br/>
            </c:if>

            Address: <input type="text" name="txtAddress" value="${param.txtAddress}" /><br/>
            <c:if test="${not empty errors.addressLenghtErr}">
                <font color="red">${errors.addressLenghtErr}</font><br/>
            </c:if>

            Phone: <input type="text" name="txtPhone" value="${param.txtPhone}" /><br/>
            <c:if test="${not empty errors.phoneLenghtErr}">
                <font color="red">${errors.phoneLenghtErr}</font><br/>
            </c:if>
            <c:if test="${not empty errors.phoneFormatErr}">
                <font color="red">${errors.phoneFormatErr}</font><br/><br/>
            </c:if>

            <input type="submit" value="Save" name="btAction" />
        </form>
    </body>
</html>
