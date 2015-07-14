package org.core.client.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

/**
 * Servlet implementation class OAuthClientTest
 */
public class OAuthClientTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OAuthClientTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		perform(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		perform(request, response);
	}

	private void perform(final HttpServletRequest req,
			final HttpServletResponse response) throws ServletException {
		try {
			OAuthClientRequest request = OAuthClientRequest
					.authorizationProvider(OAuthProviderType.MYAUTH)
					.setClientId("1234567890")
					.setRedirectURI("http://oauthgate.com:8080/client/accessToken")
					.setResponseType(ResponseType.CODE.toString())
					.buildQueryMessage();

			// in web application you make redirection to uri:
			System.out.println("Visit: " + request.getLocationUri()
					+ "\nand grant permission");
			
		} catch (OAuthSystemException e) {
			throw new ServletException(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
