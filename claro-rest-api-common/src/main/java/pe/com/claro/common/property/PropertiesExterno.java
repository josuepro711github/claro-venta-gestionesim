package pe.com.claro.common.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.NotFoundException;

import javax.ejb.Stateless;

import org.springframework.context.ApplicationContextException;

@Stateless
public class PropertiesExterno {

	private static Map<String, String> mapProperties;

	public String getValueProperty(String key) {
		if (mapProperties == null) {
			readPropertiesExterno(Constantes.PROPERTIESEXTERNOS);
		}

		return mapProperties.get(key);
	}

	private static void readPropertiesExterno(String fileInClasspath) {
		try {
			mapProperties = new HashMap<>();
			Properties propertiesExterno = new Properties();
			InputStream is = new FileInputStream(
					System.getProperty(Constantes.CLARO_PROPERTIES)
							+ File.separator + Constantes.NOMBRE_SERVICIO
							+ File.separator + fileInClasspath);
			propertiesExterno.load(is);
			mapProperties.putAll(propertiesExterno
					.entrySet()
					.stream()
					.collect(
							Collectors.toMap(e -> e.getKey().toString(), e -> e
									.getValue().toString())));

		} catch (FileNotFoundException e) {
			throw new NotFoundException(1, "No se puede leer el archivo "
					+ fileInClasspath);
		} catch (IOException e) {
			throw new ApplicationContextException(
					"No se puede leer el archivo ");
		}
	}

	public static void destroy() {
		mapProperties = null;
	}
}
