package com.tcs.poc.app.filter;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilters implements Filter{
protected static final String[] ALLOWED_HEADERS = {"Origin", "Accept", "X-Requested-With", "Content-Type",
"Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization", "Referer", "X-XSRF-TOKEN"};

 @Override
public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
throws IOException, ServletException {
	 HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    response.addHeader("Access-Control-Allow-Origin", "*");

	    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
	        response.setStatus(HttpServletResponse.SC_OK);
	    } else {
	        chain.doFilter(req, res);
	    }}

 private String getClientHost(ServletRequest req) {
HttpServletRequest request = (HttpServletRequest) req;
String clientHost = request.getHeader("Origin");
if(clientHost == null) {
clientHost = request.getHeader("Referer");
}
return clientHost;
}

 @Override
public void destroy() {
/* this method is not in use.
*/
}

 @Override
public void init(FilterConfig arg0) throws ServletException {
/* this method is not in use.
*/
}
}