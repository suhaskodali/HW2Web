<%-- 
    Document   : results
    Created on : Oct 5, 2018, 12:34:23 PM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="errorPage.jsp"%>
<%@page import="java.util.*;"%>
<%@page import="servlets.MusicVoteBean;"%>
<jsp:useBean id="enteredVote" scope="page" class="servlets.MusicVoteBean" />
<jsp:setProperty name="enteredVote" property="*"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results JSP Page</title>
    </head>
    <body>
        <jsp:getProperty name="enteredVote" property="musicType"/>
        <jsp:getProperty name="enteredVote" property="numVotes"/>
        <jsp:getProperty name="enteredVote" property="newMusicType"/>

        
        <%
        session.setAttribute("votedMusicType", enteredVote);
        %>
        
        
        

        </br>
        Below is session counter
        <%
            Integer sessionCounter = (Integer) session.getAttribute("counter");
            if (sessionCounter == null) {
                sessionCounter = new Integer(0);
            }
            sessionCounter += 1;
            session.setAttribute("counter", sessionCounter);
            out.println(sessionCounter);
        %>
        
        
    </body>
</html>
