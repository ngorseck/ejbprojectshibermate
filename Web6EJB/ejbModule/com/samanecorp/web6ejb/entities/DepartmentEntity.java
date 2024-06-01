package com.samanecorp.web6ejb.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "departments")
public class DepartmentEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy="department")
    private List<DeptEmployeeEntity> deptEmployees;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DeptEmployeeEntity> getDeptEmployees() {
		return deptEmployees;
	}

	public void setDeptEmployees(List<DeptEmployeeEntity> deptEmployees) {
		this.deptEmployees = deptEmployees;
	}
    
}
