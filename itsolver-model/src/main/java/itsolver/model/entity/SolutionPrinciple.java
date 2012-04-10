package itsolver.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="solution_principle")
public class SolutionPrinciple {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="solution_principle_id")
	private Long id;	
	
	@Column(name="principle_name")
	private String principleName;
	
	@OneToMany( cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 private List<SubPrinciple> subPrincipleList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrincipleName() {
		return principleName;
	}

	public void setPrincipleName(String principleName) {
		this.principleName = principleName;
	}

	public List<SubPrinciple> getSubPrincipleList() {
		return subPrincipleList;
	}

	public void setSubPrincipeList(List<SubPrinciple> subPrincipleList) {
		this.subPrincipleList = subPrincipleList;
	}
}
