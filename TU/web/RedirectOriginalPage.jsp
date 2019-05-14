<%-- 
    Document   : RedirectOriginalPage
    Created on : 10 Sep, 2018, 12:31:06 PM
    Author     : rahul.yadav
--%>


<%@ page import = "java.io.*,java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="db" class="com.tikona.tiny.service.URLShortener"></jsp:useBean>
<html>
   <head>
      <title>Page Redirection</title>
   </head>
   
   <body>
      
      <%
      
      
     
     String url=request.getParameter("url");
    //out.println(request.getRequestURL()+" "+url);
         
       String longUrl=db.convertToLong(url);
         // New location to be redirected
         String site = new String(longUrl);
        response.setStatus(response.SC_MOVED_TEMPORARILY);
         response.setHeader("Location", site); 
      %>
   </body>
</html>


<!--http://localhost:8088/TinyUrl?url=www.tik2.in/numvoqnqoqrqoms-->