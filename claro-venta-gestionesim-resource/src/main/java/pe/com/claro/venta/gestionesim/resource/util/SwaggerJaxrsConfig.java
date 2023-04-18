package pe.com.claro.venta.gestionesim.resource.util;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;

import pe.com.claro.common.property.Constantes;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = Constantes.SERVLET_NAME, loadOnStartup = 1, value = Constantes.SERVLET_VALUE)
public class SwaggerJaxrsConfig extends HttpServlet{
	private static final Logger LOG = LoggerFactory
			.getLogger(SwaggerJaxrsConfig.class);

	private static final long serialVersionUID = 8544076270576741659L;

	@Override
	public void init(ServletConfig servletConfig) {
		try {
			super.init(servletConfig);
			SwaggerConfig swaggerConfig = new SwaggerConfig();
			ConfigFactory.setConfig(swaggerConfig);
			swaggerConfig.setBasePath(Constantes.BASE_PATH);
			swaggerConfig.setApiVersion(Constantes.API_VERSION);
			ScannerFactory.setScanner(new DefaultJaxrsScanner());
			ClassReaders.setReader(new DefaultJaxrsApiReader());
		} catch (ServletException e) {
			LOG.error(e.getMessage());
		}
	}
}