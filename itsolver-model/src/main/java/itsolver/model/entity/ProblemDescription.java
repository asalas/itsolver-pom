package itsolver.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="problem_description")
public class ProblemDescription implements Serializable{
		
	private static final long serialVersionUID = -3652898042480857509L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="problem_desc_id", nullable=false)	
	private long id;
	
	@Column(name="brief_description")
	private String briefDescription;
	
	@Type(type="text")
	@Column(name="long_description")
	private String longDescription;
	
	@Column(name="problem_nature")
	private String problemNature;
	
	@Type(type="text")
	@Column(name="improvement_drawback")
	private String improvementDrawback;
	
	
	@Temporal(TemporalType.TIMESTAMP)	
	@Column(name="estimated_relase")
	private Date estimatedRelase;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBriefDescription() {
		return briefDescription;
	}


	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}


	public String getLongDescription() {
		return longDescription;
	}


	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}


	public String getProblemNature() {
		return problemNature;
	}


	public void setProblemNature(String problemNature) {
		this.problemNature = problemNature;
	}


	public Date getEstimatedRelase() {
		return estimatedRelase;
	}


	public void setEstimatedRelase(Date estimatedRelase) {
		this.estimatedRelase = estimatedRelase;
	}
	
	public String getImprovementDrawback() {
		return improvementDrawback;
	}
	
	public void setImprovementDrawback(String improvementDrawback) {
		this.improvementDrawback = improvementDrawback;
	}

}
