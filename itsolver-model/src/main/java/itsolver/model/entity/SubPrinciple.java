package itsolver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subprinciple")
public class SubPrinciple {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="subprinciple_id")
	private Long id;	
	
	@Column(name="subprinciple_description")
	private String subprincipleDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubprincipleDescription() {
		return subprincipleDescription;
	}

	public void setSubprincipleDescription(String subprincipleDescription) {
		this.subprincipleDescription = subprincipleDescription;
	}
}
