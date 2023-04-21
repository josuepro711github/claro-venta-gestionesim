package pe.com.claro.venta.gestionesim.integration;

import pe.com.claro.common.bean.ELKLogLegadoBean;
import pe.com.claro.common.resource.exception.WSException;
import pe.com.claro.venta.gestionesim.canonical.request.DownloadOrderRequest;
import pe.com.claro.venta.gestionesim.canonical.request.HeaderRequestBean;
import pe.com.claro.venta.gestionesim.canonical.response.DownloadOrderResponse;

public interface HubEsim {

	public DownloadOrderResponse descargarPedido(String trazabilidad, HeaderRequestBean headerRequest,
			DownloadOrderRequest request, ELKLogLegadoBean elkLegadoBean) throws WSException;
}
