package itsolver.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="solution_sufield")
@PrimaryKeyJoinColumn(name="project_solution_id")
@OnDelete(action=OnDeleteAction.CASCADE)
public class SolutionSuField extends ProjectSolution{
	
	@OneToOne(cascade = { CascadeType.ALL })
	private InventiveStandard inventiveStandard;
	
	public InventiveStandard getInventiveStandard() {
		return inventiveStandard;
	}

	public void setInventiveStandard(InventiveStandard inventiveStandard) {
		this.inventiveStandard = inventiveStandard;
	}

}