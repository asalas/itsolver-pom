package itsolver.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="contradiction_principle")
public class ContradictionPrinciple {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="contradiction_principle_id")
	private Long id;
	
	@OneToOne(cascade = { CascadeType.ALL })
	private SolutionPrinciple solutionPrinciple;
	
	/**
	 * Orden de importancia del principio dentro de la contradiccion
	 */
	@Column(name="importance_order")
	private int importanceOrder;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SolutionPrinciple getSolutionPrinciple() {
		return solutionPrinciple;
	}

	public void setSolutionPrinciple(SolutionPrinciple solutionPrinciple) {
		this.solutionPrinciple = solutionPrinciple;
	}

	public int getImportanceOrder() {
		return importanceOrder;
	}

	public void setImportanceOrder(int importanceOrder) {
		this.importanceOrder = importanceOrder;
	}
}
