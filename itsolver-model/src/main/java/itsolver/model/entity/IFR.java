package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * El IFR (Ideal Final Result) sera tomado de manera textual, 
 * en esta estapa no sera utilizado para realizar las comparativas 
 * de los casos en el modulo de CBR
 * 
 * @author asalas
 *
 */

@Entity
@Table(name="ifr")
public class IFR implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2480595699311634525L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ifr_id", nullable=false)
	private Long id;
	
	@Type(type="text")
	@Column(name="eliminate_deficiencies")
	private String eliminateDeficiencies;
	
	@Type(type="text")
	@Column(name="preserve_advantages")
	private String preserveAdvantages;
	
	@Type(type="text")
	@Column(name="not_increase_complexity")
	private String notIncreaseComplexity;
	
	@Type(type="text")
	@Column(name="not_create_disadvantages")
	private String notCreateDisadvantages;

	public String getEliminateDeficiencies() {
		return eliminateDeficiencies;
	}

	public void setEliminateDeficiencies(String eliminateDeficiencies) {
		this.eliminateDeficiencies = eliminateDeficiencies;
	}

	public String getPreserveAdvantages() {
		return preserveAdvantages;
	}

	public void setPreserveAdvantages(String preserveAdvantages) {
		this.preserveAdvantages = preserveAdvantages;
	}

	public String getNotIncreaseComplexity() {
		return notIncreaseComplexity;
	}

	public void setNotIncreaseComplexity(String notIncreaseComplexity) {
		this.notIncreaseComplexity = notIncreaseComplexity;
	}

	public String getNotCreateDisadvantages() {
		return notCreateDisadvantages;
	}

	public void setObstacles(String notCreateDisadvantages) {
		this.notCreateDisadvantages = notCreateDisadvantages;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	 

}