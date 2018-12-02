package model;


public class SOS {
	String SOSID;
	String SOSDate;
	String SOSTime;
	String pType;
	String pQuan;
	String sid;
	String rid;
	String sender;
	String status;
	
	public String getSid() {
		return sid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSOSID() {
		return SOSID;
	}
	public void setSOSID(String sOSID) {
		SOSID = sOSID;
	}
	public String getSOSDate() {
		return SOSDate;
	}
	public void setSOSDate(String sOSDate) {
		SOSDate = sOSDate;
	}
	public String getSOSTime() {
		return SOSTime;
	}
	public void setSOSTime(String sOSTime) {
		SOSTime = sOSTime;
	}
	public String getpType() {
		return pType;
	}
	public void setpType(String pType) {
		this.pType = pType;
	}
	public String getpQuan() {
		return pQuan;
	}
	public void setpQuan(String pQuan) {
		this.pQuan = pQuan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
