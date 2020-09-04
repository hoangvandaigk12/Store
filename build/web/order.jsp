<%-- 
    Document   : order
    Created on : Jul 14, 2020, 12:54:00 AM
    Author     : AD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <form action="checkout" method="POST">
            <c:if test="${sessionScope != null}">               
                <c:if test="${sessionScope.ACCOUNT != null}">
                    <c:set var="dto" value="${sessionScope.ACCOUNT}"/>
                    Customer Name: <input type="text" name="txtCusName" value="${dto.fullname}" /><br/>
                </c:if>

                <c:if test="${sessionScope.ACCOUNT == null}">
                    Customer Name: <input type="text" name="txtCusName" value="" /><br/>
                </c:if>
                    
                    Address: <input type="text" name="txtAddress" value="" /><br/>
                    Phone: <input type="text" name="txtPhone" value="" /><br/><br/>                  
            </c:if>
                    <input type="submit" value="Save" name="btAction" />
        </form>
    </body>
</html>
