<%-- 
    Document   : index
    Created on : 4 Oct, 2018, 2:00:05 PM
    Author     : rahul.yadav
--%>

<jsp:useBean id="db" class="com.tikona.tiny.Dao.DatabaseDao"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        String longUrl=db.retriveLongUrl("K6eqxx");
        out.print("hello "+longUrl);
        %>
        
        
    </body>
</html>
