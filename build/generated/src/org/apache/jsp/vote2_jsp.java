package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class vote2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Vote Page</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Integer sessionCounter = (Integer) session.getAttribute("counter");
            if (sessionCounter == null) {
                sessionCounter = new Integer(0);
            }
            session.setAttribute("counter", sessionCounter);

            Integer contextCounter = (Integer) application.getAttribute("counter");
            if (contextCounter == null) {
                contextCounter = new Integer(0);
                
            }


        
      out.write("\n");
      out.write("        <form action=\"AV2servlet\" method=\"get\">\n");
      out.write("            Vote your favorite kind of music: <br/>\n");
      out.write("            <input type=\"radio\" name=\"music\" value=\"Pop\" />Pop<br/>\n");
      out.write("            <input type=\"radio\" name=\"music\" value=\"Rock\" />Rock<br/>\n");
      out.write("            <input type=\"submit\" value =\"Vote\">\n");
      out.write("        </form>\n");
      out.write("        <br/>Or add new one!<br/><br/>\n");
      out.write("        \n");
      out.write("        <form action=\"ANV2servlet\" method=\"get\">\n");
      out.write("            New Music Type:<input type=\"text\" name=\"newmusic\" value=\"\"><br/>\n");
      out.write("            <input type=\"submit\" value=\"Add type and vote\"/>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
