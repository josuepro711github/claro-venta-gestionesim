package pe.com.claro.venta.gestionesim.resource.util;

import java.util.Set;

import javax.ejb.Singleton;
import javax.ws.rs.core.Application;
import pe.com.claro.common.property.Constantes;
import pe.com.claro.common.resource.exception.ProviderExceptionMapper;
import pe.com.claro.venta.gestionesim.resource.GestioneSimResource;

@Singleton
@javax.ws.rs.ApplicationPath(Constantes.API)
public class ApplicationConfig extends Application{
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();

		resources
				.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
		resources
				.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
		resources
				.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
		resources
				.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(GestioneSimResource.class);
		resources.add(ProviderExceptionMapper.class);
	}
	}
