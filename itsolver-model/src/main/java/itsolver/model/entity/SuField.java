package itsolver.model.entity;

import itsolver.model.entity.sufield.SuFieldGraph;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="sufield")
@PrimaryKeyJoinColumn(name="project_id")
@OnDelete(action=OnDeleteAction.CASCADE)
public class SuField extends Project implements Serializable {
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 5309193244367556180L;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name = "sufield_model_id", nullable = false)
	private SuFieldModel sufieldModel = new SuFieldModel();
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	@JoinColumn(name = "sufield_graph_id", nullable = true)
	private SuFieldGraph sufieldGraph;
	
	@ManyToOne(targetEntity = InventiveStandard.class)
	@JoinColumn(name = "standard_id", nullable = true)
	private InventiveStandard inventiveStandard;
		
	public SuField() {
		super();
		super.projectSolution = new SolutionSuField();
	}
	
	public SolutionSuField getSolutionSuField(){
		return (SolutionSuField) this.projectSolution;
	}

	public SuFieldModel getSufieldModel() {
		return sufieldModel;
	}

	public void setSufieldModel(SuFieldModel sufieldModel) {
		this.sufieldModel = sufieldModel;
	}

	public InventiveStandard getInventiveStandard() {
		return inventiveStandard;
	}

	public void setInventiveStandard(InventiveStandard inventiveStandard) {
		this.inventiveStandard = inventiveStandard;
	}

	public SuFieldGraph getSufieldGraph() {
		return sufieldGraph;
	}

	public void setSufieldGraph(SuFieldGraph sufieldGraph) {
		this.sufieldGraph = sufieldGraph;
	}
	
}
