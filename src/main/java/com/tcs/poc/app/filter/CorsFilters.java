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
HttpServletResponse response = (HttpServletResponse) res;
String clientHost = getClientHost(req);
if(clientHost == null) {
response.setHeader("Access-Control-Allow-Origin", "*");
} else {
response.setHeader("Access-Control-Allow-Origin", clientHost);
}
response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
response.setHeader("Access-Control-Max-Age", "3600");
response.setHeader("Access-Control-Allow-Credentials", "true");
String allowedHeaders = Arrays.toString(ALLOWED_HEADERS);
allowedHeaders = allowedHeaders.substring(1, allowedHeaders.length() - 1);
response.setHeader("Access-Control-Allow-Headers", allowedHeaders);

 chain.doFilter(req, res);
}

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