package itsolver.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="algorithm_question")
@XmlRootElement
public class AlgorithmQuestion implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = -6277128556371532187L;
	
	@Id
	@Column(name="question_number", nullable=true)
	private String questionNumber;
	
	@Column(name="question")
	private String question;		
	
	@Column(name="yes_question_number")
	private String yesQuestionNumber;
	
	@Column(name="no_question_number")
	private String noQuestionNumber;
	
	@ManyToMany
	@JoinTable(
			name="yes_standards_list",
			joinColumns = @JoinColumn(name="questionNumber"),
			inverseJoinColumns = @JoinColumn(name="inventiveStandardNumber")
	)
	private List<InventiveStandard> ifYesStandardsList = new ArrayList<InventiveStandard>();
	
	@ManyToMany
	@JoinTable(
			name="no_standards_list",
			joinColumns = @JoinColumn(name="questionNumber"),
			inverseJoinColumns = @JoinColumn(name="inventiveStandardNumber")
	)
	private List<InventiveStandard> ifNoStandardsList = new ArrayList<InventiveStandard>();

	@XmlAttribute
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}	

	@XmlAttribute(name="question_number")
	public String getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	@XmlAttribute(name="yes_question_number")
	public String getYesQuestionNumber() {
		return yesQuestionNumber;
	}

	public void setYesQuestionNumber(String positiveQuestionNumber) {
		this.yesQuestionNumber = positiveQuestionNumber;
	}

	@XmlAttribute(name="no_question_number")
	public String getNoQuestionNumber() {
		return noQuestionNumber;
	}

	public void setNoQuestionNumber(String negativeQuestionNumber) {
		this.noQuestionNumber = negativeQuestionNumber;
	}

	@XmlElement(name="yes_standards_list")
	public List<InventiveStandard> getIfYesStandardsList() {
		return ifYesStandardsList;
	}

	public void setIfYesStandardsList(List<InventiveStandard> ifYesStandardsList) {
		this.ifYesStandardsList = ifYesStandardsList;
	}

	@XmlElement(name="no_standards_list")
	public List<InventiveStandard> getIfNoStandardsList() {
		return ifNoStandardsList;
	}

	public void setIfNoStandardsList(List<InventiveStandard> ifNoStandardsList) {
		this.ifNoStandardsList = ifNoStandardsList;
	}
	
	
}