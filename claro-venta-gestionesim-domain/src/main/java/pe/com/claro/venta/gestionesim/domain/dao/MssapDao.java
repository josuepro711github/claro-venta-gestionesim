package pe.com.claro.venta.gestionesim.domain.dao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.claro.common.resource.exception.DBException;
import pe.com.claro.venta.gestionesim.canonical.request.ActualizarEstadoRequest;
import pe.com.claro.venta.gestionesim.canonical.request.ReservarCodigoRequest;
import pe.com.claro.venta.gestionesim.canonical.response.ActualizarEstadoResponse;
import pe.com.claro.venta.gestionesim.canonical.response.ReservarCodigoResponse;
import pe.com.claro.venta.gestionesim.domain.repository.MSSAPRepository;

@Stateless
public class MssapDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private MSSAPRepository mssapRepository;

	public ReservarCodigoResponse obtenerCodigo(String message, ReservarCodigoRequest request) throws DBException {
		return mssapRepository.obtenerCodigo(message, request);
	}

	public ActualizarEstadoResponse actualizarEstado(String message, ActualizarEstadoRequest request) throws DBException {
		return mssapRepository.actualizarEstado(message, request);
	}

}
