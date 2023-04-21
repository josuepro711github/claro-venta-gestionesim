package pe.com.claro.common.util;

import javax.ws.rs.core.Configuration;

public class PropertiesExternos {
	public String mssapjndi;
	public String mssapbd;
	public String mssapowner;

	// Timeouts
	public String mssaptimeoutconexionobtenercodigo;
	public String mssaptimeoutejecucionobtenercodigo;
	public String mssaptimeoutconexionactualizarestado;
	public String mssaptimeoutejecucionactualizarestado;

	public String urlhubesim;
	public String metodohubesim;
	public Integer downloadOrderTimeoutConexion;
	public Integer downloadOrderTimeoutEjecucion;

	public String status;

	// StoreProcedure
	public String mssappackagegestionesim;
	public String mssapspobtenercodigo;
	public String mssapspactualizarestado;
	// IDF
	public String idf0codigo;
	public String idf0msg;
	public String idf1codigo;
	public String idf1msg;
	public String idf2codigo;
	public String idf2msg;
	public String idf3codigo;
	public String idf3msg;
	// IDT
	public String idt1codigo;
	public String idt1msg;
	public String idt2codigo;
	public String idt2msg;
	public String idt3codigo;
	public String idt3msg;
	public String idt4codigo;
	public String idt4msg;

	// constructor
	public PropertiesExternos(Configuration configuration) {
		this.downloadOrderTimeoutConexion = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.jndi")) == null ? 0
						: Integer.parseInt(ClaroUtil
								.convertProperties(configuration.getProperty("ws.download.order.timeout.conexion")));
		this.downloadOrderTimeoutEjecucion = ClaroUtil
				.convertProperties(configuration.getProperty("ws.download.order.timeout.ejecucion")) == null ? 0
						: Integer.parseInt(ClaroUtil
								.convertProperties(configuration.getProperty("ws.download.order.timeout.ejecucion")));
		this.mssapjndi = ClaroUtil.convertProperties(configuration.getProperty("bd.mssap.jndi"));
		this.mssapbd = ClaroUtil.convertProperties(configuration.getProperty("bd.mssap"));
		this.mssapowner = ClaroUtil.convertProperties(configuration.getProperty("bd.mssap.owner"));
		this.mssaptimeoutconexionobtenercodigo = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.timeout.conexion.obtener.codigo"));
		this.mssaptimeoutejecucionobtenercodigo = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.timeout.ejecucion.obtener.codigo"));
		this.mssaptimeoutconexionactualizarestado = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.timeout.conexion.actualizar.estado"));
		this.mssaptimeoutejecucionactualizarestado = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.timeout.ejecucion.actualizar.estado"));
		this.urlhubesim = ClaroUtil.convertProperties(configuration.getProperty("ws.hubEsim.wsdl"));
		this.metodohubesim = ClaroUtil.convertProperties(configuration.getProperty("ws.hubEsim.metodo"));
		this.status = ClaroUtil.convertProperties(configuration.getProperty("estado.libre"));

		this.mssappackagegestionesim = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.package.gestiona.esim"));
		this.mssapspobtenercodigo = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.sp.obtener.codigo"));
		this.mssapspactualizarestado = ClaroUtil
				.convertProperties(configuration.getProperty("bd.mssap.sp.actualizar.estado"));
		// IDF
		this.idf0codigo = ClaroUtil.convertProperties(configuration.getProperty("idf0.codigo"));
		this.idf0msg = ClaroUtil.convertProperties(configuration.getProperty("idf0.mensaje"));
		this.idf1codigo = ClaroUtil.convertProperties(configuration.getProperty("idf1.codigo"));
		this.idf1msg = ClaroUtil.convertProperties(configuration.getProperty("idf1.mensaje"));
		this.idf2codigo = ClaroUtil.convertProperties(configuration.getProperty("idf2.codigo"));
		this.idf2msg = ClaroUtil.convertProperties(configuration.getProperty("idf2.mensaje"));
		this.idf3codigo = ClaroUtil.convertProperties(configuration.getProperty("idf3.codigo"));
		this.idf3msg = ClaroUtil.convertProperties(configuration.getProperty("idf3.mensaje"));
		// IDT
		this.idt1codigo = ClaroUtil.convertProperties(configuration.getProperty("idt1.codigo"));
		this.idt1msg = ClaroUtil.convertProperties(configuration.getProperty("idt1.mensaje"));
		this.idt2codigo = ClaroUtil.convertProperties(configuration.getProperty("idt2.codigo"));
		this.idt2msg = ClaroUtil.convertProperties(configuration.getProperty("idt2.mensaje"));
		this.idt3codigo = ClaroUtil.convertProperties(configuration.getProperty("idt3.codigo"));
		this.idt3msg = ClaroUtil.convertProperties(configuration.getProperty("idt3.mensaje"));
		this.idt4codigo = ClaroUtil.convertProperties(configuration.getProperty("idt4.codigo"));
		this.idt4msg = ClaroUtil.convertProperties(configuration.getProperty("idt4.mensaje"));
	}
}