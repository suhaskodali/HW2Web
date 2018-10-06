<%-- 
    Document   : errorPage
    Created on : Oct 6, 2018, 10:51:05 AM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <img src="https://cdn.theatlantic.com/assets/media/img/mt/2015/01/fw-1/lead_large.png" /><br/><br/><br/>
        <h3 style="background:blue;color:yellow">Good for debugging, but don't show what is below in a production environment!</h3>
        <%
                   out.println(exception.toString());
        %>
    </body>
</html>
