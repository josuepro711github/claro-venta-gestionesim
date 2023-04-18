package pe.com.claro.venta.gestionesim.resource.util;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import pe.com.claro.common.property.Constantes;

@WebFilter(filterName = Constantes.FILTER_NAME, urlPatterns = { Constantes.URL_PATTERNS })
public class HTML5CorsFilter implements javax.servlet.Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader(Constantes.ALLOW_ORIGIN, Constantes.ALLOW_ORIGIN_VALUE);
		res.addHeader(Constantes.ALLOW_METHODS, Constantes.ALLOW_METHODS_VALUE);
		res.addHeader(Constantes.ALLOW_HEADERS, Constantes.ALLOW_HEADERS_VALUE);
		chain.doFilter(request, response);
	}
		@Override
		public void init(FilterConfig filterConfig) throws ServletException {
			this.toString();
		}

		@Override
		public void destroy() {
			this.toString();
		}
	}