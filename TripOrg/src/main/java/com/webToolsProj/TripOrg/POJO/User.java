package com.webToolsProj.TripOrg.POJO;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="userTable")
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person{
	
//	@Column(name="userName")
//	private String userName;
//	
//	@Column(name="password")
//	private String password;
	
//	@OneToOne(fetch=FetchType.EAGER,mappedBy="user",cascade=CascadeType.ALL)
//	private Email email;
	
	@Transient
	private float discount;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Set<Booking> booking;
//	public User(String userName, String password) {
//        this.userName = userName;
//        this.password = password;
//    }

    public User() {
    }
	
//	public String getUserName() {
//		return userName;
//	}
//	
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	
//	public String getPassword() {
//		return password;
//	}
//	
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	public float getDiscount() {
		return discount;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}

//	public Email getEmail() {
//		return email;
//	}
//
//	public void setEmail(Email email) {
//		this.email = email;
//	}
	
	
}
