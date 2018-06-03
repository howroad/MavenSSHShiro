package com.luhao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user_role", catalog = "db_test0603")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class TUserRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3530264150226505443L;
	private String urId;
	private TRole TRole;
	private TUser TUser;

	// Constructors

	/** default constructor */
	public TUserRole() {
	}

	/** full constructor */
	public TUserRole(TRole TRole, TUser TUser) {
		this.TRole = TRole;
		this.TUser = TUser;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "ur_id", unique = true, nullable = false, length = 32)

	public String getUrId() {
		return this.urId;
	}

	public void setUrId(String urId) {
		this.urId = urId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")

	public TRole getTRole() {
		return this.TRole;
	}

	public void setTRole(TRole TRole) {
		this.TRole = TRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")

	public TUser getTUser() {
		return this.TUser;
	}

	public void setTUser(TUser TUser) {
		this.TUser = TUser;
	}

}