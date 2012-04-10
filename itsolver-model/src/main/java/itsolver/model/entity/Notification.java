package itsolver.model.entity;

import itsolver.utils.NotificationType;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="notification")
public class Notification {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="notification_id", nullable=false)	
	private long Id;
	
	public Notification() {
		
	}
	public Notification(NotificationType _notificationType) {
		this.notificationType = _notificationType;
	}
	
	/*@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.LAZY)
	@JoinColumn(name="project_id")
	private Project project;*/
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="description")	
	private String description;
	
	/**
	 * Full name of user that generates the notification
	 */
	@Column(name="fullname")
	private String fullName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type", nullable = false, updatable = false)
	private NotificationType notificationType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_on", nullable = false)	
	private Date createdOn	= new Date();

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		/*String value ="N/A";
		switch(notificationType){
		 case PROJECT_CREATED:
			 	value = "Project creation";
			break;
		 case INVITATION_TO_COLABORATE:
			 	value = "Sent invitation to colaborate";
			break;
		 case INVITATION_RECEIVED:
			 	value = "Invitation to colaborate received";
			break;
		}
		return value;*/
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
	public Date getCreatedOn() {		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			createdOn = formatter.parse(createdOn.toString());
		}catch(Exception e) {}
		
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
