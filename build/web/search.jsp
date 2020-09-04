<%-- 
    Document   : search
    Created on : Jun 18, 2020, 11:37:15 PM
    Author     : AD
--%>

<%@page import="java.util.List"%>
<%@page import="dai.dto.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

    </head>
    <body>
        <form action="load" method="POST">
            <c:if test="${sessionScope.ACCOUNT.role != true}">
                <c:redirect url="load"></c:redirect>
            </c:if>
        </form>
        <jsp:include page="header.jsp" flush="true"/>
        <h1>Search page</h1>
        <form action="search" method="POST">
            Search Value: <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <br/>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">          
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="update">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>

                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" 
                                           value="${dto.username}"/>
                                </td>

                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>

                                <td>
                                    ${dto.fullname}
                                    <input type="hidden" name="txtFullname" value="${dto.fullname}" readonly="" />
                                </td>              

                                <td>
                                    <input type="checkbox" name="chkRole" value="ON" 
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           />
                                </td>

                                <td>
                                    <c:url var="urlRewriting" value="delete">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>

                                <td>
                                    <input type="submit" value="UpdateAccount" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                </tbody>
            </table>
        </c:if>
    </c:if>


</body>
</html>
