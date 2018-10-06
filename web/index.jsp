<%-- 
    Document   : index
    Created on : Oct 4, 2018, 6:11:31 PM
    Author     : Suhas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index page</title>
        <script type="text/javascript">        
            var xmlhttp;
            function LookUp() {
                if (window.XMLHttpRequest){
                    // code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }else{
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }

                var url = "indexServlet";
                
                xmlhttp.open("GET", url, true);
                xmlhttp.onreadystatechange = callback;
                xmlhttp.send();

                
            }
            
            function callback(){
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status == 200) {
                        document.getElementById("results").innerHTML=xmlhttp.responseText;
                    }
                }
            }
            
        </script>
    </head>
    <body>
        <a href="/P2Kodali/Start2Page2Servlet">See voting results</a>
        </br>
        
        <input type="button" value="My votes" onClick="LookUp();"/>
        <div id="results">
            


        
        
    </body>
</html>
