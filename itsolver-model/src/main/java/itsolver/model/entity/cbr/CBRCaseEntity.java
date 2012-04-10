package itsolver.model.entity.cbr;

import itsolver.model.entity.Project;
import itsolver.utils.CBRCaseType;
import itsolver.utils.CEncrypt;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name="cbr_case_entity")
public class CBRCaseEntity implements CaseComponent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="case_id", nullable=false)	
	private Long caseId;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="cbr_constraint_id")
	private CBRConstraint cbrConstraint;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="cbr_resource_id")	
	private CBRResource cbrResource;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="cbr_contradiction_id")
	private CBRContradiction cbrContradiction;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name="cbr_sufield_id")
	private CBRSuField cbrSuField;
		
	@OneToOne	
	private Project project;

	
	@Column(name="problem_nature")
	private String problemNature;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "cbr_case_type", nullable = false, updatable = false)
	private CBRCaseType cbrCaseType;
	
	@Transient
	private double similarityEvaluation;
	
	
	public Attribute getIdAttribute() {
		return new Attribute("caseId", this.getClass());
	}
	
	@Transient
	private String language;

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public CBRConstraint getCbrConstraint() {
		return cbrConstraint;
	}

	public void setCbrConstraint(CBRConstraint cbrConstraint) {
		this.cbrConstraint = cbrConstraint;
	}
	
	public String getProblemNature() {
		return problemNature;
	}

	public void setProblemNature(String problemNature) {
		this.problemNature = problemNature;
	}

	public CBRResource getCbrResource() {
		return cbrResource;
	}

	public void setCbrResource(CBRResource cbrResource) {
		this.cbrResource = cbrResource;
	}
	
	public CBRContradiction getCbrContradiction() {
		return cbrContradiction;
	}

	public void setCbrContradiction(CBRContradiction cbrContradiction) {
		this.cbrContradiction = cbrContradiction;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public CBRCaseType getCbrCaseType() {
		return cbrCaseType;
	}

	public void setCbrCaseType(CBRCaseType cbrCaseType) {
		this.cbrCaseType = cbrCaseType;
	}		

	public String getUrlToPdf() {
		//abc para no darle mucho sognificado cuando se ve en la URL hace referencia al id del proyecto
		String url = "/projecttoreport.do?abc="+ CEncrypt.encryptString(Long.toString(this.project.getId()))+"&lang="+this.getLanguage();
		return url;
	}
	public String getUrlToHtml() {
		//abc para no darle mucho sognificado cuando se ve en la URL hace referencia al id del proyecto
		return "/projecttoreport.do?html=1&abc="+ CEncrypt.encryptString(Long.toString(this.project.getId()))+"&lang="+this.getLanguage();
	}

	public CBRSuField getCbrSuField() {
		return cbrSuField;
	}

	public void setCbrSuField(CBRSuField cbrSuField) {
		this.cbrSuField = cbrSuField;
	}

	public Double getSimilarityEvaluation() {
		return Math.round(similarityEvaluation*100*100)/100.0d;		 
	}

	public void setSimilarityEvaluation(double similarityEvaluation) {
		this.similarityEvaluation = similarityEvaluation;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getLanguage() {
		return language;
	}

}