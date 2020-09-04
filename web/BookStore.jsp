<%-- 
    Document   : BookStore
    Created on : Jul 12, 2020, 12:51:58 AM
    Author     : AD
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <form action="cart" method="POST">
            <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}"/>
            Choose book <select name="cboBook">
                <c:forEach var="product" items="${listProduct}">
                    <option>${product}</option>
                </c:forEach>
                
            </select><br/>
            <input type="submit" value="Add Book To Your Cart" name="btAction" />
            <input type="submit" value="View Your Cart" name="btAction" />
        </form>
    </body>
</html>
