package org.core.client.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Index
 */
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
//		ClientConfig cc = new ClientConfig()
//		.register(JacksonFeature.class)
//		.register(JsonContentTypeResponseFilter.class)
//		.property(javax.json.stream.JsonGenerator.PRETTY_PRINTING, true);
//		
//		Client client = ClientBuilder.newClient(cc);
//		WebTarget target =  client.target("http://oauthgate.com:8080/server/oauth/rest");
//		target.register(LoggingFilter.class);
//		UserDataResponse userData = target.path("test").path("19").request(MediaType.APPLICATION_JSON).get(UserDataResponse.class);
//		
//		PrintWriter pw = response.getWriter();
//		pw.println(userData);
//		pw.flush();
//		pw.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
