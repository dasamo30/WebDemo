/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdemo.filter;

//import common.Logger;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diego
 */
public class SecurityFilter implements Filter {

    private static final boolean debug = true;
    //private Logger logger = Logger.getLogger(this.getClass());

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    private long currTime = 0;
    private long expiryTime = 0;

    public SecurityFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("SecurityFilter:DoAfterProcessing");
        }
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        HttpSession session = req.getSession();

        currTime = System.currentTimeMillis() / 1000;
        
        
        String uri = ((HttpServletRequest)request).getRequestURI();

        if(uri.matches(".*(css|jpg|png|gif|js|svg|eot|ttf|woff|woff2)")){
            chain.doFilter(request, response);
            return;
         }

        //System.out.println("SecurityFilter");
        
        if (servletPath.equals("/login") || servletPath.equals("/index.html") || servletPath.equals("/validatelogin")) {
            ////System.out.println("if:validatelogin");
            if (session.getAttribute("err") != null) {
                String temp[] = session.getAttribute("err").toString().split(";");
                if (temp[0].equals("error_logeo")) {
                    session.setAttribute("err", temp[1]);
                } else {
                    if (expiryTime == 0) {
                        session.setAttribute("err", "");
                    } else if (expiryTime == -1) {
                        expiryTime = 0;
                    } else if (expiryTime == -2) {
                        expiryTime = 0;
                    }
                }
            }
        } else {
            //System.out.println("else");
           if (expiryTime == 0) {
                
                if (session.getAttribute("usuario") != null) {
                    //System.out.println("usuario != null");
                    expiryTime = currTime + session.getMaxInactiveInterval();
                } else {
                    //System.out.println("Usted no ha iniciado sesion");
                    session.setAttribute("err", "Usted no ha iniciado sesion");
                    // Request is not authorized.
                    resp.sendRedirect("/WebDemo/login");
                    expiryTime = -2;
                    return;
                }
            } else {
                if (expiryTime < currTime) {
                    session.setAttribute("err", "La sesion a expirado.");
                    // Request is not authorized.
                    resp.sendRedirect("/WebDemo/login");
                    expiryTime = -1;
                    return;
                } else {
                    if (session.getAttribute("usuario") != null) {
                        expiryTime = currTime + session.getMaxInactiveInterval();
                        //System.out.println("existe");
                        /*
                        if((Integer)session.getAttribute("pestado")==0){
                            request.getRequestDispatcher("/WebDemo/panel/home").forward(request, response);
                            return;
                        }*/
                    } else {
                        session.setAttribute("err", "Usted no ha iniciado sesion");
                        // Request is not authorized.
                        resp.sendRedirect("/WebDemo/login");
                        //resp.setHeader(servletPath, servletPath);
                        expiryTime = -2;
                        return;
                    }
                }
            }
        }
        chain.doFilter(req, resp);
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;

        if (filterConfig != null) {
            //System.out.println("=======> " + filterConfig.getInitParameter("parametro1"));
            if (debug) {
                log("SecurityFilter:Initializing filter");
            }
        }

    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("SecurityFilter()");
        }
        StringBuffer sb = new StringBuffer("SecurityFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
