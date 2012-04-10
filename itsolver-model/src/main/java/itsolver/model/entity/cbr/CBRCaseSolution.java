package itsolver.model.entity.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class CBRCaseSolution implements CaseComponent{

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name="case_id", nullable=false)	
	private Long solutionId;
	
	private String solution;
	
	
	public Attribute getIdAttribute() {
		return new Attribute("solutionId", this.getClass());
	}

	public Long getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
}
