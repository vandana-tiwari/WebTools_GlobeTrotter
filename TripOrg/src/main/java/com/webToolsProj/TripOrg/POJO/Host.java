package com.webToolsProj.TripOrg.POJO;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="hostTable")
@PrimaryKeyJoinColumn(name="personID")
public class Host extends Person{
	
	private long govtID;
	private String idType;
	
	public Host(long govtID, String idType) {

        this.govtID = govtID;
        this.idType = idType;
	}
	
	public Host(){
		
	}
	
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public long getGovtID() {
		return govtID;
	}
	public void setGovtID(long govtID) {
		this.govtID = govtID;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}


}
