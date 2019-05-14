package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;

public final class ConvertToShort_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      com.tikona.tiny.service.URLShortener db = null;
      synchronized (_jspx_page_context) {
        db = (com.tikona.tiny.service.URLShortener) _jspx_page_context.getAttribute("db", PageContext.PAGE_SCOPE);
        if (db == null){
          db = new com.tikona.tiny.service.URLShortener();
          _jspx_page_context.setAttribute("db", db, PageContext.PAGE_SCOPE);
        }
      }
      out.write('\n');

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


      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
