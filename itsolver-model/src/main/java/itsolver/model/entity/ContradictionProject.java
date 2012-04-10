package itsolver.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="contradiction_project")
@PrimaryKeyJoinColumn(name="project_id")
@OnDelete(action=OnDeleteAction.CASCADE)
public class ContradictionProject extends Project{
	private static final long serialVersionUID = -8988819566985822911L;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	@JoinColumn(name="contradiction_id")
	private ProjectContradiction contradiction;
	
	public ContradictionProject() {
		super();
		super.projectSolution = new SolutionContradiction();
	}
	
	public ProjectContradiction getContradiction() {
		return contradiction;
	}

	public void setContradiction(ProjectContradiction contradiction) {
		this.contradiction = contradiction;
	}
	
	public SolutionContradiction getSolutionContradiction(){
		return (SolutionContradiction) super.projectSolution;
	}
}
