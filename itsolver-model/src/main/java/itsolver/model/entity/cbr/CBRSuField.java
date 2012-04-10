package itsolver.model.entity.cbr;

import itsolver.model.entity.InventiveStandard;
import itsolver.model.entity.sufield.SuFieldGraph;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jcolibri.cbrcore.Attribute;

@Entity
@Table(name="cbr_sufield")
public class CBRSuField {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cbr_sufield_id")
	private Long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="standard_number")
	private InventiveStandard inventiveStandard;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sufield_graph_id")
	private SuFieldGraph suFieldGraph;
	
	public Attribute getIdAttribute() {
		return new Attribute("id", this.getClass());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InventiveStandard getInventiveStandard() {
		return inventiveStandard;
	}

	public void setInventiveStandard(InventiveStandard inventiveStandard) {
		this.inventiveStandard = inventiveStandard;
	}

	public SuFieldGraph getSuFieldGraph() {
		return suFieldGraph;
	}

	public void setSuFieldGraph(SuFieldGraph suFieldGraph) {
		this.suFieldGraph = suFieldGraph;
	}
	
}
