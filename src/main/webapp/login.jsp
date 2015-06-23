<%-- 
    Document   : login
    Created on : 18/06/2015, 15:44:42
    Author     : julian.fernando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
    </head>
    <body>
        <form action="j_security_check" method="post">
            username : <input type="text" name="j_username"/><br>
            password : <input type="password" name = "j_password"/><br>
            <input type ="submit" name = "submit" value = "submit">
        </form>
    </body>
</html>
