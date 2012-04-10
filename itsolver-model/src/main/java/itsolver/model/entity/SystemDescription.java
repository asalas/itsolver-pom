package itsolver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_description")
public class SystemDescription {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="system_description_id", nullable=false)	
	private Long id;
	
	@Column(name="power_source")
	private String powerSource;
	
	@Column(name="engine")
	private String engine;
	
	@Column(name="transmition")
	private String transmition;
	
	@Column(name="working_unit")
	private String workingUnit;
	
	@Column(name="control_unit")
	private String controlUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getTransmition() {
		return transmition;
	}

	public void setTransmition(String transmition) {
		this.transmition = transmition;
	}

	public String getWorkingUnit() {
		return workingUnit;
	}

	public void setWorkingUnit(String workingUnit) {
		this.workingUnit = workingUnit;
	}

	public String getControlUnit() {
		return controlUnit;
	}

	public void setControlUnit(String controlUnit) {
		this.controlUnit = controlUnit;
	}
}
