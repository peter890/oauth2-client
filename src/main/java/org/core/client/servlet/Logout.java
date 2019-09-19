/**
 *
 */
package org.core.client.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author piotrek
 */
public class Logout extends HttpServlet {
    /* (non-Javadoc)
    * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        try {
            final ServletOutputStream out = resp.getOutputStream();
            out.println(req.getSession().toString());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
