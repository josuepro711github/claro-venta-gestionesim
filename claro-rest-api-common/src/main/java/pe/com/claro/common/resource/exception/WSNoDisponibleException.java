package pe.com.claro.common.resource.exception;

public class WSNoDisponibleException extends BaseException {

	private static final long serialVersionUID = 1L;
	private String nombreWS;
	private String nombreMetodo;

	/**
	 * Constructores
	 */
	public WSNoDisponibleException(String codError, String nombreWS, String msjError, String nombreMetodo,
			Exception objException) {

		super(codError, msjError, objException);
		this.nombreWS = nombreWS;
		this.nombreMetodo = nombreMetodo;
	}

	/**
	 * Constructores
	 */
	public WSNoDisponibleException(String codError, String nombreWS, String msjError, String nombreMetodo,
			Throwable objException) {

		super(codError, msjError);
		this.nombreWS = nombreWS;
		this.nombreMetodo = nombreMetodo;
	}

	/**
	 * Constructores
	 */
	public WSNoDisponibleException(String msjError, Exception objException) {
		super(msjError, objException);
	}

	/**
	 * Constructores
	 */
	public WSNoDisponibleException(Exception objException) {
		super(objException);
	}

	/**
	 * Constructores
	 */
	public WSNoDisponibleException(String msjError) {
		super(msjError);
	}

	public String getNombreWS() {
		return nombreWS;
	}

	public void setNombreWS(String nombreWS) {
		this.nombreWS = nombreWS;
	}

	public String getNombreMetodo() {
		return nombreMetodo;
	}

	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}

}
