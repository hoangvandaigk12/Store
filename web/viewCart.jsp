<%-- 
    Document   : viewCart
    Created on : Jun 19, 2020, 10:40:17 AM
    Author     : AD
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Map"%>
<%@page import="dai.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        
        <h1>Your Cart</h1>
        <c:if test="${sessionScope.CART == null}">
            <h2>No cart is existed</h2>
        </c:if>
        <c:if test="${sessionScope != null}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${cart != null}">
                <c:set var="items" value="${cart.values}"/>
                <c:if test="${items != null}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="view" method="POST">
                       
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>

                                    <td>
                                        ${item.title}
                                        <input type="hidden" name="txtTitle" value="${item.title}"/>
                                    </td>

                                    <td>
                                        ${item.quantity}
                                        <input type="hidden" name="txtQuantity" value="${item.quantity}" />
                                    </td>

                                    <td>
                                        <input type="checkbox" name="chkItem" value="${item.title}" />
                                    </td>
                                </tr>

                                
                            </c:forEach>
                                <tr>
                                    <td colspan="3" rowspan="2">
                                        <a href="load">Add more Item to Your Cart</a>
                                    </td>
                                    <td>
                                        <input type="submit" value="Remove selected Item" name="btAction" />
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="4">
                                        <input type="submit" value="Checkout" name="btAction" />
                                    </td>
                                </tr> 
                        </form>
                    </tbody>
                </table>
            </c:if>
        </c:if>
    </c:if>
    
</body>
</html>
