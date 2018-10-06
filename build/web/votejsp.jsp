<%-- 
    Document   : votejsp
    Created on : Oct 6, 2018, 10:47:43 AM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vote JSP page</title>
    </head>
    <body>
        <form action="results.jsp">
            Vote your favorite kind of music: <br/>
            <input type="radio" name="musicType" value="Pop" />Pop<br/>
            <input type="radio" name="musicType" value="Rock" />Rock<br/>
            <input type="submit" value ="Vote">
        
        
            <br/>Or add new one!<br/><br/>
        
            New Music Type:<input type="text" name="newMusicType" value=""><br/>
            <input type="submit" value="Add type and vote"/>
        </form>
    </body>
</html>
