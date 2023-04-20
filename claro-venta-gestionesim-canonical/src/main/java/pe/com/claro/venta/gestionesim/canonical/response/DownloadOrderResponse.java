package pe.com.claro.venta.gestionesim.canonical.response;

public class DownloadOrderResponse extends HeaderResponse{
	
	private String es2pSmdpAddress;
	private String iccid;
	private HeaderResponse header;
	
	public String getEs2pSmdpAddress() {
		return es2pSmdpAddress;
	}
	public void setEs2pSmdpAddress(String es2pSmdpAddress) {
		this.es2pSmdpAddress = es2pSmdpAddress;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public HeaderResponse getHeader() {
		return header;
	}
	public void setHeader(HeaderResponse header) {
		this.header = header;
	}
}
