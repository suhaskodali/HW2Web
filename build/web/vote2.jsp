<%-- 
    Document   : vote2
    Created on : Oct 6, 2018, 4:41:19 PM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vote Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="AV2servlet" method="get">
            Vote your favorite kind of music: <br/>
            <input type="radio" name="music" value="Pop" />Pop<br/>
            <input type="radio" name="music" value="Rock" />Rock<br/>
            <input type="submit" value ="Vote">
        </form>
        <br/>Or add new one!<br/><br/>
        
        <form action="ANV2servlet" method="get">
            New Music Type:<input type="text" name="newmusic" value=""><br/>
            <input type="submit" value="Add type and vote"/>
        </form>
        <%
            Integer sessionCounter = (Integer) session.getAttribute("counter");
            if (sessionCounter == null) {
                sessionCounter = new Integer(0);
            }
            session.setAttribute("counter", sessionCounter);

            Integer contextCounter = (Integer) application.getAttribute("counter");
            if (contextCounter == null) {
                contextCounter = new Integer(0);
            }
            contextCounter += 1;
            application.setAttribute("counter", contextCounter);
            

            session.setAttribute("contextCounter", contextCounter);
            
            
            

                
        %>
    </body>
</html>
