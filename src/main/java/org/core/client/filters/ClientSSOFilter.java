package org.core.client.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.core.Configuration;
import org.core.Configuration.Parameter;
import org.core.common.enums.CookiesName;
import org.core.common.exceptions.CookieNotFoundException;
import org.core.common.utils.CookieManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSSOFilter implements Filter {
	/**
	 * Logger.
	 */
	final static Logger logger = LoggerFactory.getLogger(ClientSSOFilter.class);

	public void destroy() {
		logger.debug("destroy");
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		logger.debug("doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String path = req.getRequestURI();
		boolean excludedRequest = false;
		logger.debug("doFilter|path: {}", path);
		if (path.contains("accessToken")) {
			excludedRequest = true;
		}

		if (!excludedRequest) {
			try {
				CookieManager.getCookie(req, CookiesName.AppId);

			} catch (CookieNotFoundException e1) {
				String clientId = Configuration.getConfiguration().getParameterValue(Parameter.ClientId);
				CookieManager.setCookie(resp, CookiesName.AppId, clientId);
			}

			try {
				CookieManager.getCookie(req, CookiesName.OAuthUserId);

			} catch (CookieNotFoundException e) {
				logger.error("doFilter", "Nie odnaleziono Cookie name: OAUTHUSERID");
				String clientId = Configuration.getConfiguration().getParameterValue(Parameter.ClientId);
				String redirectURI = Configuration.getConfiguration().getParameterValue(Parameter.AccessTokenUrl);
				try {
					OAuthClientRequest oauthRequest = OAuthClientRequest
							.authorizationProvider(OAuthProviderType.MYAUTH).setClientId(clientId)
							.setRedirectURI(redirectURI).setResponseType(ResponseType.CODE.toString()).setScope("user")
							.buildQueryMessage();
					filterChain.doFilter(request, response);
					resp.sendRedirect(oauthRequest.getLocationUri());
					return;
				} catch (OAuthSystemException e1) {
					logger.error("doFilter", e1);
				}
			}
		}

		// if (getCookie(cookies, cookieName) == null) {
		// resp.addCookie(new Cookie(cookieName, req.getSession().getId()));
		// resp.sendRedirect(req.getContextPath()+"/login.jsp");
		// }

		filterChain.doFilter(request, response);
		return;
	}

	public void init(final FilterConfig arg0) throws ServletException {
		logger.debug("init");

	}

}
