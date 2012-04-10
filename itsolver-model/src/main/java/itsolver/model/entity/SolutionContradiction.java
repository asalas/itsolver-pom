package itsolver.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="solution_contradiction")
@PrimaryKeyJoinColumn(name="project_solution_id")
@OnDelete(action=OnDeleteAction.CASCADE)
public class SolutionContradiction extends ProjectSolution{
	
	@OneToOne(cascade = { CascadeType.ALL })
	private SolutionPrinciple solutionPrinciple;

	public SolutionPrinciple getSolutionPrinciple() {
		return solutionPrinciple;
	}

	public void setSolutionPrinciple(SolutionPrinciple solutionPrinciple) {
		this.solutionPrinciple = solutionPrinciple;
	}

}