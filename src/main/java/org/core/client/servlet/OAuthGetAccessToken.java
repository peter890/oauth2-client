package org.core.client.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class OAuthGetAccessToken
 */
public class OAuthGetAccessToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(OAuthGetAccessToken.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuthGetAccessToken() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doGet|START");
		String code = req.getParameter("code");
		OAuthClientRequest request;
		try {
			request = OAuthClientRequest
					.tokenProvider(OAuthProviderType.MYAUTH)
					.setGrantType(GrantType.AUTHORIZATION_CODE)
					.setClientId("131804060198305")
					.setClientSecret("3acb294b071c9aec86d60ae3daf32a93")
					.setRedirectURI("http://oauthgate.com:8080/client").setCode(code)
					.buildBodyMessage();
			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			
			//GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(request, GitHubTokenResponse.class);
			OAuthJSONAccessTokenResponse resp = oAuthClient.accessToken(request);

			System.out.println("Access Token: "
					+ resp.getAccessToken() + ", Expires in: "
					+ resp.getExpiresIn());
			
			//response.setStatus(resp.getOAuthToken().);
			PrintWriter pw = response.getWriter();
			pw.print(resp.getBody());
			pw.flush();
			pw.close();
		} catch (OAuthSystemException e) {
			logger.debug("doGet", e);
		} catch (OAuthProblemException e) {
			logger.debug("doGet", e);
		}	
		logger.debug("doGet|STOP");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		logger.debug("doPost|START");
		logger.error("Nie powinno tutaj wejœæ!");
	}

}
