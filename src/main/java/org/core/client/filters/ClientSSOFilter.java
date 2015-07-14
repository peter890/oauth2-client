package org.core.client.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.core.common.enums.CookiesName;

public class ClientSSOFilter implements Filter {
	final static Logger log = LogManager.getLogger(ClientSSOFilter.class);

	public void destroy() {
		log.debug("destroy");
	}

	public void doFilter(final ServletRequest request, final ServletResponse response,
			final FilterChain filterChain) throws IOException, ServletException {
		log.debug("doFilter");
		//String authGateUrl = Configuration.getConfiguration().getParameterValue("authGateUrl");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(getCookie(req, CookiesName.SSID) == null) {
		
		}
//		if (getCookie(cookies, cookieName) == null) {
//			resp.addCookie(new Cookie(cookieName, req.getSession().getId()));
//			resp.sendRedirect(req.getContextPath()+"/login.jsp");
//		}
		
		filterChain.doFilter(request, response);
		return;
	}

	private Cookie getCookie(final Cookie[] cookies, final String cookieName) {
		if (cookies != null && cookies.length > 0 && cookieName != null
				&& cookieName != "") {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	
	private Cookie getCookie(final HttpServletRequest request, final CookiesName name) {
		String cookieName = name.getValue();
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0 && cookieName != null
				&& cookieName != "") {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}

	public void init(final FilterConfig arg0) throws ServletException {
		log.debug("init");
		

	}

}
