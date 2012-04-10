package itsolver.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

@Entity
@Table(name="project_solution")
@Inheritance(strategy = InheritanceType.JOINED)
public class ProjectSolution {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="project_solution_id", nullable=false)	
	private Long id;
	
	@OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
	private List<SelectedResource> utilizedResourceList;
	
	/**
	 * Propiedad para describir como se utilizaron los recursos en la resolucion del problema
	 */
	@Type(type="text")
	@Column(name="resource_implementation")
	private String resourceImplementation;
	 
	@OneToMany(cascade={CascadeType.MERGE}, fetch=FetchType.LAZY)
	 @Cascade(
	    value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	 )
	private List<Restriction> satisfiedConstrainList;
	 
	/**Solution details**/
	@Type(type="text")
	@Column(name="comments_in_solution")
	private String commentsInSolution;
	
	@Type(type="text")
	@Column(name="problem_with_solution")
	private String problemWithSolution;
	
	@Type(type="text")
	@Column(name="information_sources")
	private String informationSources;
	
	@Type(type="text")
	@Column(name="alternative_solution")
	private String alternativeSolution;
	
	@Type(type="text")
	@Column(name="expert_contact_detail")
	private String expertContactDetail;
	
	@Column(name="satisfaction_percent")
	private int satisfactionPercent;
	
	@Type(type="text")
	@Column(name="how_was_it_adapted")
	private String howWasItAdapted;
	
	@OneToMany(mappedBy="projectSolution", fetch=FetchType.EAGER)
	private List<PictureSolution> picturesSolutionList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceImplementation() {
		return resourceImplementation;
	}

	public void setResourceImplementation(String resourceImplementation) {
		this.resourceImplementation = resourceImplementation;
	}

	public String getProblemWithSolution() {
		return problemWithSolution;
	}

	public void setProblemWithSolution(String problemWithSolution) {
		this.problemWithSolution = problemWithSolution;
	}

	public String getInformationSources() {
		return informationSources;
	}

	public void setInformationSources(String informationSources) {
		this.informationSources = informationSources;
	}

	public String getAlternativeSolution() {
		return alternativeSolution;
	}

	public void setAlternativeSolution(String alternativeSolution) {
		this.alternativeSolution = alternativeSolution;
	}

	public String getExpertContactDetail() {
		return expertContactDetail;
	}

	public void setExpertContactDetail(String expertContactDetail) {
		this.expertContactDetail = expertContactDetail;
	}

	public int getSatisfactionPercent() {
		return satisfactionPercent;
	}

	public void setSatisfactionPercent(int satisfactionPercent) {
		this.satisfactionPercent = satisfactionPercent;
	}

	public String getCommentsInSolution() {
		return commentsInSolution;
	}

	public void setCommentsInSolution(String commentsInSolution) {
		this.commentsInSolution = commentsInSolution;
	}

	public List<SelectedResource> getUtilizedResourceList() {
		if ( utilizedResourceList == null ){
			return new ArrayList<SelectedResource>();
		}
		return utilizedResourceList;
	}

	public void setUtilizedResourceList(List<SelectedResource> utilizedResourceList) {
		this.utilizedResourceList = utilizedResourceList;
	}

	public List<Restriction> getSatisfiedConstrainList() {
		if ( satisfiedConstrainList == null ){
			return new ArrayList<Restriction>();
		}
		return satisfiedConstrainList;
	}

	public void setSatisfiedConstrainList(List<Restriction> satisfiedConstrainList) {
		this.satisfiedConstrainList = satisfiedConstrainList;
	}

	public String getHowWasItAdapted() {
		return howWasItAdapted;
	}

	public void setHowWasItAdapted(String howWasItAdapted) {
		this.howWasItAdapted = howWasItAdapted;
	}

	public List<PictureSolution> getPicturesSolutionList() {
		return picturesSolutionList;
	}

	public void setPicturesSolutionList(List<PictureSolution> picturesSolutionList) {
		this.picturesSolutionList = picturesSolutionList;
	}
	
}