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
 * TRolePower entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role_power", catalog = "db_test0603")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class TRolePower implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2968034967756924157L;
	private String rpId;
	private TPower TPower;
	private TRole TRole;

	// Constructors

	/** default constructor */
	public TRolePower() {
	}

	/** full constructor */
	public TRolePower(TPower TPower, TRole TRole) {
		this.TPower = TPower;
		this.TRole = TRole;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "rp_id", unique = true, nullable = false, length = 32)

	public String getRpId() {
		return this.rpId;
	}

	public void setRpId(String rpId) {
		this.rpId = rpId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "power_id")

	public TPower getTPower() {
		return this.TPower;
	}

	public void setTPower(TPower TPower) {
		this.TPower = TPower;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")

	public TRole getTRole() {
		return this.TRole;
	}

	public void setTRole(TRole TRole) {
		this.TRole = TRole;
	}

}