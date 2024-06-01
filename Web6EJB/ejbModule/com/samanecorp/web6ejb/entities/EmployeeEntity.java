package com.samanecorp.web6ejb.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    
    private String lastName;

    @OneToMany(mappedBy="employee")
    private List<DeptEmployeeEntity> deptEmployees;
    
    @OneToOne(mappedBy="employee")
    private UserEntity userEntity;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<DeptEmployeeEntity> getDeptEmployees() {
		return deptEmployees;
	}

	public void setDeptEmployees(List<DeptEmployeeEntity> deptEmployees) {
		this.deptEmployees = deptEmployees;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
}
