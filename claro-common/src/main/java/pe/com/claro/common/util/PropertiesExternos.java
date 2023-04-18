package pe.com.claro.common.util;

import java.io.Serializable;

public class PropertiesExternos implements Serializable {
	private PropertiesExternos() {
	}

	private static final long serialVersionUID = 1L;

	public static final String MSSAPJNDI = "bd.mssap.jndi";
	public static final String MSSAPBD = "bd.mssap";
	public static final String MSSAPOWNER = "bd.mssap.owner";
	
	// Timeouts
	public static final String MSSAPTIMEOUTCONEXIONOBTENERCODIGO = "bd.mssap.timeout.conexion.obtener.codigo";
	public static final String MSSAPTIMEOUTEJECUCIONOBTENERCODIGO = "bd.mssap.timeout.ejecucion.obtener.codigo";
	public static final String MSSAPTIMEOUTCONEXIONACTUALIZARESTADO = "bd.mssap.timeout.conexion.actualizar.estado";
	public static final String MSSAPTIMEOUTEJECUCIONACTUALIZARESTADO = "bd.mssap.timeout.ejecucion.actualizar.estado";

	//StoreProcedure
	public static final String MSSAPPACKAGEGESTIONESIM = "bd.mssap.package.gestiona.esim";
	public static final String MSSAPSPOBTENERCODIGO = "bd.mssap.sp.obtener.codigo";
	public static final String MSSAPSPACTUALIZARESTADO = "bd.mssap.sp.actualizar.estado";
	// IDF
	public static final String IDF0CODIGO = "idf0.codigo";
	public static final String IDF0MSG = "idf0.mensaje";
	public static final String IDF1CODIGO = "idf1.codigo";
	public static final String IDF1MSG = "idf1.mensaje";
	public static final String IDF2CODIGO = "idf2.codigo";
	public static final String IDF2MSG = "idf2.mensaje";
	public static final String IDF3CODIGO = "idf3.codigo";
	public static final String IDF3MSG = "idf3.mensaje";

	public static final String IDT1CODIGO = "idt1.codigo";
	public static final String IDT1MSG = "idt1.mensaje";
	public static final String IDT2CODIGO = "idt2.codigo";
	public static final String IDT2MSG = "idt2.mensaje";
	public static final String IDT3CODIGO = "idt3.codigo";
	public static final String IDT3MSG = "idt3.mensaje";
	public static final String IDT4CODIGO = "idt4.codigo";
	public static final String IDT4MSG = "idt4.mensaje";
}
