<%-- 
    Document   : Test
    Created on : 20 Sep, 2018, 2:47:59 PM
    Author     : rahul.yadav
--%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="db" class="com.tikona.tiny.service.URLShortener"></jsp:useBean>
<%
    PrintWriter pw = response.getWriter();
    response.setContentType("text/xml");

    String source = request.getParameter("source");
    String createdBy = request.getParameter("createdBy");
    String url = request.getParameter("url");
    String ttl = request.getParameter("TTL");

    String msg = "";
    String status = "SUCCESS";

    pw.print("<ROOT>");
    pw.print("<RESULT>");
    if (url == null || url.equals("")) {

        msg += "Invalid Url ~";
        status = "FAIL";
    }
    if (request.getParameter("TTL") == null || request.getParameter("TTL").equals("")) {

        msg += "Invalid TTL ~";
        status = "FAIL";
    }

    if (createdBy == null || createdBy.equals("")) {
        msg += "Invalid CreatedBy ~";
        status = "FAIL";
    }
    if (source == null || source.equals("")) {

        msg += "Invalid Source ~";
        status = "FAIL";
    }

    if (status.equalsIgnoreCase("success")) {
        String shortyUrl = db.convertToShort(url, Integer.parseInt(ttl), createdBy, source);
        if (shortyUrl != null && shortyUrl.length()>= 6) {
            pw.write("<STATUS>SUCCESS</STATUS>");
            pw.write("<SHORTLY_URL>" + shortyUrl + "</SHORTLY_URL>");
        } else {

            pw.write("<STATUS>FAIL</STATUS>");
            pw.write("<MSG>TRY AFTER SOME TIME</MSG>");
        }

        // out.print(shortyUrl);


    } else {

        pw.write("<STATUS>" + status + "</STATUS>");
        pw.write("<MSG>" + msg + "</MSG>");
    }
    pw.print("</RESULT>");
    pw.print("</ROOT>");

    //url="https://www.amazon.in/ap/signin?_encoding=UTF8&openid.assoc_handle=inflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fcard%3Fie%3DUTF8%26ref_%3Dcust_rec_intestitial_signin";

%>
