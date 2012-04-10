package itsolver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="picture_solution")
public class PictureSolution {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="picture_solution_id", nullable=false)
	private Long id;
	
	@Lob
	@Column(name="picture")
	private byte[] picture;
	
	@ManyToOne(targetEntity=ProjectSolution.class)
	@JoinColumn(name="project_solution_id", nullable=true)
	private ProjectSolution projectSolution;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public ProjectSolution getProjectSolution() {
		return projectSolution;
	}

	public void setProjectSolution(ProjectSolution projectSolution) {
		this.projectSolution = projectSolution;
	}
	
}
