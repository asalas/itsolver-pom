package itsolver.model.entity;

import itsolver.utils.ProjectStatus;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="project")
@Inheritance(strategy = InheritanceType.JOINED)
public class Project implements Serializable{
	private static final long serialVersionUID = -620366031025591807L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_id", nullable=false)	
	private long Id;
	
	
	@ManyToOne(targetEntity = Profile.class )
	@JoinColumn(name = "profile_id", nullable = false)
	private Profile profile;
	
	@Column(name="project_name")
	private String projectName;
			
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable = false)	
	private Date createdOn	= new Date();
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE })
	@JoinColumn(name = "problem_desc_id", nullable = false)
	private ProblemDescription problemDescription = new ProblemDescription();
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<Invitation> invitationList;
	
	@OneToMany(mappedBy="project", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<InvitationByEmail> invitationByEmailList;

	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<ProjectResource> projectResourceList;
	 
	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 @org.hibernate.annotations.Cascade(
	    value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	 )
	 private List<Restriction> restrictionList;
	 
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE })
	@JoinColumn(name = "ifr_id", nullable = false)
	private IFR ifr = new IFR();
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE })
	@JoinColumn(name = "project_solution_id", nullable = false)
	protected ProjectSolution projectSolution;
	
	@OneToOne(cascade = { CascadeType.ALL })
	private SystemDescription systemComponents = new  SystemDescription();
	
	@OneToOne(cascade = { CascadeType.ALL })
	private SystemDescription superSystemComponents = new  SystemDescription();
	 
	@Enumerated(EnumType.STRING)
	@Column(name = "project_status", nullable = false)
	private ProjectStatus projectStatus = ProjectStatus.WORKING;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		if (this instanceof ContradictionProject){
			return "Contradictions";
		}else if (this instanceof SuField) {
			return "Su-Field";
		}
		return "NA";
	}
	
	

	public Date getCreatedOn() {		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			createdOn = formatter.parse(createdOn.toString());
		}catch(Exception e) {}
		
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setProblemDescription(ProblemDescription problemDescription) {
		this.problemDescription = problemDescription;
	}

	public ProblemDescription getProblemDescription() {
		return problemDescription;
	}

	public List<Invitation> getInvitationList() {
		return invitationList;
	}

	public void setInvitationList(List<Invitation> invitationList) {
		this.invitationList = invitationList;
	}

	public List<ProjectResource> getProjectResourceList() {
		if ( this.projectResourceList == null ){
			return new ArrayList<ProjectResource>();
		}
		return projectResourceList;
	}

	public void setProjectResourceList(List<ProjectResource> projectResourceList) {
		this.projectResourceList = projectResourceList;
	}

	public List<InvitationByEmail> getInvitationByEmailList() {
		return invitationByEmailList;
	}

	public void setInvitationByEmailList(
			List<InvitationByEmail> invitationByEmailList) {
		this.invitationByEmailList = invitationByEmailList;
	}

	public List<Restriction> getRestrictionList() {
		if (restrictionList == null){
			return new ArrayList<Restriction>();
		}
		return restrictionList;
	}

	public void setRestrictionList(List<Restriction> restrictionList) {
		this.restrictionList = restrictionList;
	}

	public IFR getIfr() {
		return ifr;
	}

	public void setIfr(IFR ifr) {
		this.ifr = ifr;
	}

	@Transient
	public boolean equals(Object obj) {
		if(obj instanceof Project) {
			Project project = (Project) obj;
			if(project.getId() == this.getId()) {
				return true;
			}
		}
		return false;
	}

	public ProjectStatus getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(ProjectStatus projectStatus) {
		this.projectStatus = projectStatus;
	}

	public ProjectSolution getProjectSolution() {
		return projectSolution;
	}

	public void setProjectSolution(ProjectSolution projectSolution) {
		this.projectSolution = projectSolution;
	}

	public SystemDescription getSystemComponents() {
		return systemComponents;
	}

	public void setSystemComponents(SystemDescription systemComponents) {
		this.systemComponents = systemComponents;
	}

	public SystemDescription getSuperSystemComponents() {
		return superSystemComponents;
	}

	public void setSuperSystemComponents(SystemDescription superSystemComponents) {
		this.superSystemComponents = superSystemComponents;
	}	
}