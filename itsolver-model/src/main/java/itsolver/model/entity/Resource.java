package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity  
@Table(name = "resource")
public class Resource implements Serializable{

	private static final long serialVersionUID = 8774688839245486207L;
	
	@Id  
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name="resource_id", nullable=false)	
	 private Long id;
	
	@Column(name="resource_name")
	 private String resourceName;
	
	//@Enumerated(EnumType.STRING)
	//@Column(name="measurement_unit")
	@Transient
	private MeasurementUnit measurementUnit;
	
	public Resource() {		
	}
	
	public Resource(String name, MeasurementUnit unit) {
		this.resourceName = name;
		this.measurementUnit = unit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setMeasurementUnit(MeasurementUnit measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public MeasurementUnit getMeasurementUnit() {
		return measurementUnit;
	}

	

}
