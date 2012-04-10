package itsolver.model.entity.cbr;

import itsolver.service.cbr.ListComparator;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name="cbr_constraint")
public class CBRConstraint implements CaseComponent, ListComparator {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cbr_constraint_id", nullable=false)	
	private Long id;
	
	@org.hibernate.annotations.CollectionOfElements(
			targetElement = java.lang.Long.class
	)
	@Column(name="cbr_constraint_list") 
	List<Long> constrainIdList = new ArrayList<Long>();
	
	
	public Attribute getIdAttribute() {
		return new Attribute("id", this.getClass());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Long> getConstrainIdList() {
		return constrainIdList;
	}

	public void setConstrainIdList(List<Long> constrainIdList) {
		this.constrainIdList = constrainIdList;
	}
	
	
	public List<Long> getListToComparate() {
		return this.constrainIdList;
	}
	
}
