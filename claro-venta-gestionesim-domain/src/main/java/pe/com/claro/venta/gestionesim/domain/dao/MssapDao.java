package pe.com.claro.venta.gestionesim.domain.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.claro.common.bean.BodyResponse;
import pe.com.claro.common.resource.exception.DBException;
import pe.com.claro.common.util.PropertiesExternos;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.repository.MSSAPRepository;

@Stateless
public class MssapDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private MSSAPRepository mssapRepository;

	public ReservarCodigoResponse obtenerCodigo(String message, PropertiesExternos propertiesExternos,
			ReservarCodigoRequest request) throws DBException {
		return mssapRepository.obtenerCodigo(message, request, propertiesExternos);
	}

	public BodyResponse actualizarEstado(String message, PropertiesExternos propertiesExternos,
			ActualizarEstadoRequest request) throws DBException {
		return mssapRepository.actualizarEstado(message, request, propertiesExternos);
	}

}
