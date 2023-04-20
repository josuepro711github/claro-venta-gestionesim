package pe.com.claro.common.util;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@LocalBean
@Stateless
public class RestTemplateEE {

	@Context
	RestTemplate restTemplate = _init();

	public RestTemplateEE() {
		super();
		this.restTemplate = new RestTemplate();
		for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
			if (converter instanceof StringHttpMessageConverter) {
				((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
				break;
			}
		}
	}

	private RestTemplate _init() {
		if (restTemplate == null) {
			this.restTemplate = new RestTemplate();
			for (HttpMessageConverter<?> converter : restTemplate.getMessageConverters()) {
				if (converter instanceof StringHttpMessageConverter) {
					((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
					break;
				}
			}
		}
		return restTemplate;
	}

	public RestTemplate getRestTemplate(SimpleClientHttpRequestFactory httpRequestFactory) {
		if (restTemplate == null) {
			_init();
		}
		restTemplate.setRequestFactory(httpRequestFactory);
		return restTemplate;
	}

	public RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			_init();
		}
		return restTemplate;
	}
	
}
