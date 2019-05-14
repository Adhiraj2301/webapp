package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class TinyHome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    \n");
      out.write("    <head><title>welcome</title>\n");
      out.write("\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        \n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n");
      out.write("  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n");
      out.write("  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n");
      out.write("  \n");
      out.write("        <script language=\"javascript\">\n");
      out.write("            reqObj=null;\n");
      out.write("            function varify(){\n");
      out.write("                document.getElementById(\"res\").innerHTML=\"<img src=\\\"image\\\\loader.gif\\\" width=\\\"400px\\\" height=\\\"150px\\\">\";\n");
      out.write("                if(window.XMLHttpRequest){\n");
      out.write("                    reqObj=new XMLHttpRequest();\n");
      out.write("                }else {\n");
      out.write("                    reqObj=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("                }\n");
      out.write("            \n");
      out.write("                reqObj.onreadystatechange=process;\n");
      out.write("                reqObj.open(\"POST\",\"./makeTinyUrl.jsp?url=\"+document.getElementById(\"LongUrl\").value,true);\n");
      out.write("                reqObj.send(null);\n");
      out.write("            }\n");
      out.write("            function process(){\n");
      out.write("                if(reqObj.readyState==4){\n");
      out.write("                    var x=reqObj.responseText;\n");
      out.write("                    var html='<a  id=\"redirect\" href=\"./RedirectOriginalPage.jsp?url='+x+'\">Test Tiny Url!</a>';\n");
      out.write("                    res.innerHTML =html;\n");
      out.write("                   \n");
      out.write("                    \n");
      out.write("                    document.getElementById(\"resText\").innerHTML=x;\n");
      out.write("            \n");
      out.write("\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <h2>Welcome</h2>\n");
      out.write("            <div class=\"panel-group\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading\">Tikona Tiny URL</div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <label for=\"SUrl\">Enter a long URL to make tiny</label>\n");
      out.write("                        <input type=\"text\" name=\"LongUrl\" id=\"LongUrl\" class=\"form-control\"  placeholder=\"Enter URL\" required><br>\n");
      out.write("                        <input type=\"submit\" class=\"btn btn-warning\" onClick=\"varify();\"><br>\n");
      out.write("                        <label for=\"TinyName\">Tiny URL</label><br>\n");
      out.write("                        \n");
      out.write("                        <button type=\"button\" class=\"btn btn-link\" id=\"res\"></button><br>\n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        Short URL: <div id=\"resText\" style=\"color: red\"></div>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>           \n");
      out.write("</body>\n");
      out.write("</html >");
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
