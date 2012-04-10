package itsolver.model.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="invitation")
public class Invitation implements Serializable{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2011800451629536493L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invitation_id", nullable=false)
	private Long id;
	
	@Column(name="invitation_message")
	private String message;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="invitation_date", nullable=false)
	private Date invitationDate;
	
	@ManyToOne(targetEntity=Project.class)
	@JoinColumn(name="project_id")
	private Project project;
	
	
	// En la proiedad JoinColumn el name es para la tabla local, no la referencia
	@ManyToOne(targetEntity = Profile.class )
	@JoinColumn(name = "profile_host", nullable = false)
	private Profile sentByProfile;
	
	@ManyToOne(targetEntity = Profile.class )
	@JoinColumn(name = "profile_guest", nullable = false)
	private Profile sentToProfile;
	
	@Column(name="is_accepted")
	private Boolean isAccepted;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getInvitationDate() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			invitationDate = formatter.parse(invitationDate.toString());
		}catch(Exception e) {}
		return invitationDate;
	}

	public void setInvitationDate(Date invitationDate) {
		this.invitationDate = invitationDate;
	}

	public Boolean isAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public Profile getSentByProfile() {
		return sentByProfile;
	}

	public void setSentByProfile(Profile sentByProfile) {
		this.sentByProfile = sentByProfile;
	}

	public Profile getSentToProfile() {
		return sentToProfile;
	}

	public void setSentToProfile(Profile sentToProfile) {
		this.sentToProfile = sentToProfile;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Transient
	public String getStatus() {
		String status="";
		if(this.isAccepted != null) {
			if(this.isAccepted) {
				status ="Accepted";
			}else {
				status = "Rejected";
			}
		}else {
			status = "Pending";
		}		
		return status;
	}

}
