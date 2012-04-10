package itsolver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invitation_mail")
public class InvitationByEmail {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 8493861933536718504L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invitation_mail_id")
	private Long id;
	
	@Column(name="hilo_id", unique=true)
	private String hiloId;
	
	@ManyToOne(targetEntity = Profile.class)
	@JoinColumn(name = "profile_host_id", nullable = false)
	private Profile sendFrom;
	
	@ManyToOne(targetEntity = Project.class)
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@Column(name="email")
	private String email;
	
	@Column(name="message")
	private String message;
	
	@Column(name="is_accepted")
	private Boolean isAccepted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHiloId() {
		return hiloId;
	}

	public void setHiloId(String hiloId) {
		this.hiloId = hiloId;
	}

	public Profile getSendFrom() {
		return sendFrom;
	}

	public void setSendFrom(Profile sendFrom) {
		this.sendFrom = sendFrom;
	}

	public Boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAcepted) {
		this.isAccepted = isAcepted;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
	
}