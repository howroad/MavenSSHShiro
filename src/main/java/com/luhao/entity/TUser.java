package com.luhao.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "db_test0603")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class TUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3048654265612435634L;
	private String userId;
	private String userName;
	private String userPassword;
	private String userRname;
	private String userPic;
	private String userGender;
	private String userBirth;
	private String userTel;
	private String userAddress;
	private String userEmail;
	private String userRegistTime;
	private Integer userStates;
	private Integer userOutState;
	private Integer userManagerState;
	private Integer userSuperState;
	private Set<TUserRole> TUserRoles = new HashSet<TUserRole>(0);

	// Constructors

	/** default constructor */
	public TUser() {
	}

	
	public TUser(String userId) {
		super();
		this.userId = userId;
	}


	public TUser(String userId, String userName, String userPassword, String userRname, String userPic,
			String userGender, String userBirth, String userTel, String userAddress, String userEmail,
			String userRegistTime, Integer userStates, Integer userOutState, Integer userManagerState,
			Integer userSuperState, Set<TUserRole> tUserRoles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRname = userRname;
		this.userPic = userPic;
		this.userGender = userGender;
		this.userBirth = userBirth;
		this.userTel = userTel;
		this.userAddress = userAddress;
		this.userEmail = userEmail;
		this.userRegistTime = userRegistTime;
		this.userStates = userStates;
		this.userOutState = userOutState;
		this.userManagerState = userManagerState;
		this.userSuperState = userSuperState;
		TUserRoles = tUserRoles;
	}


	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "user_id", unique = true, nullable = false, length = 32)

	public String getUserId() {
		return this.userId;
	}

	


	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", length = 32)

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "user_password", length = 32)

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Column(name = "user_rname", length = 32)

	public String getUserRname() {
		return this.userRname;
	}

	public void setUserRname(String userRname) {
		this.userRname = userRname;
	}

	@Column(name = "user_pic")

	public String getUserPic() {
		return this.userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	@Column(name = "user_gender", length = 10)

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	@Column(columnDefinition = "DATE", name = "user_birth")

	public String getUserBirth() {
		return this.userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	@Column(name = "user_tel", length = 15)

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	@Column(name = "user_address", length = 100)

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Column(name = "user_email", length = 50)

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(columnDefinition = "DATETIME", name = "user_regist_time")

	public String getUserRegistTime() {
		return this.userRegistTime;
	}

	public void setUserRegistTime(String userRegistTime) {
		this.userRegistTime = userRegistTime;
	}

	@Column(name = "user_states")

	public Integer getUserStates() {
		return this.userStates;
	}

	public void setUserStates(Integer userStates) {
		this.userStates = userStates;
	}

	@Column(name = "user_out_state")

	public Integer getUserOutState() {
		return this.userOutState;
	}

	public void setUserOutState(Integer userOutState) {
		this.userOutState = userOutState;
	}

	@Column(name = "user_manager_state")

	public Integer getUserManagerState() {
		return userManagerState;
	}

	public void setUserManagerState(Integer userManagerState) {
		this.userManagerState = userManagerState;
	}
	@Column(name = "user_super_state")
	public Integer getUserSuperState() {
		return userSuperState;
	}


	public void setUserSuperState(Integer userSuperState) {
		this.userSuperState = userSuperState;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TUser")
	@JsonIgnore
	public Set<TUserRole> getTUserRoles() {
		return this.TUserRoles;
	}

	public void setTUserRoles(Set<TUserRole> TUserRoles) {
		this.TUserRoles = TUserRoles;
	}


	@Override
	public String toString() {
		return "TUser [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userRname="
				+ userRname + ", userPic=" + userPic + ", userGender=" + userGender + ", userBirth=" + userBirth
				+ ", userTel=" + userTel + ", userAddress=" + userAddress + ", userEmail=" + userEmail
				+ ", userRegistTime=" + userRegistTime + ", userStates=" + userStates + ", userOutState=" + userOutState
				+ ", userManagerState=" + userManagerState + ", userSuperState=" + userSuperState + ", TUserRoles="
				+ TUserRoles + "]";
	}


}