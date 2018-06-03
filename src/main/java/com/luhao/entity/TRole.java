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
 * TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_role", catalog = "db_test0603")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"}) 
public class TRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5057097885025946235L;
	private String roleId;
	private String roleName;
	private String roleDesc;
	private Set<TRolePower> TRolePowers = new HashSet<TRolePower>(0);
	private Set<TUserRole> TUserRoles = new HashSet<TUserRole>(0);

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** full constructor */
	public TRole(String roleName, String roleDesc, Set<TRolePower> TRolePowers, Set<TUserRole> TUserRoles) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.TRolePowers = TRolePowers;
		this.TUserRoles = TUserRoles;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")

	@Column(name = "role_id", unique = true, nullable = false, length = 32)

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_name", length = 32)

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "role_desc", length = 100)

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TRole")
	@JsonIgnore
	public Set<TRolePower> getTRolePowers() {
		return this.TRolePowers;
	}

	public void setTRolePowers(Set<TRolePower> TRolePowers) {
		this.TRolePowers = TRolePowers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TRole")
	@JsonIgnore
	public Set<TUserRole> getTUserRoles() {
		return this.TUserRoles;
	}

	public void setTUserRoles(Set<TUserRole> TUserRoles) {
		this.TUserRoles = TUserRoles;
	}

}