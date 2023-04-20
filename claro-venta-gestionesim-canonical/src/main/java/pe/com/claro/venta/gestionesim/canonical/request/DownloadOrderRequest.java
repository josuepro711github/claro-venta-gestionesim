package pe.com.claro.venta.gestionesim.canonical.request;

public class DownloadOrderRequest {
	private HeaderDownloadOrderRequest header;
	private String cancelOnGoingOrders;
	private String eid;
	private String finalProfileStatusIndicator;
	private String iccid;
	private String imsi;
	private String msisdn;
	private String operatorId;
	private String profileType;
	
	public HeaderDownloadOrderRequest getHeader() {
		return header;
	}
	public void setHeader(HeaderDownloadOrderRequest header) {
		this.header = header;
	}
	public String getCancelOnGoingOrders() {
		return cancelOnGoingOrders;
	}
	public void setCancelOnGoingOrders(String cancelOnGoingOrders) {
		this.cancelOnGoingOrders = cancelOnGoingOrders;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getFinalProfileStatusIndicator() {
		return finalProfileStatusIndicator;
	}
	public void setFinalProfileStatusIndicator(String finalProfileStatusIndicator) {
		this.finalProfileStatusIndicator = finalProfileStatusIndicator;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getProfileType() {
		return profileType;
	}
	public void setProfileType(String profileType) {
		this.profileType = profileType;
	}
	
}
