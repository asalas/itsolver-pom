package itsolver.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="profile")
public class Profile implements Serializable{
	private static final long serialVersionUID = 6356884023723225273L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="profile_id", nullable=false)
	@Id
	private Long Id;	
	/**
	 *The name of the user
	 */
	@Column(name = "name", length=255, nullable = false)
	private String name;
	
	/**
	 * Last name of user 
	 */
	@Column(name="last_name", length=255, nullable=true)
	private String lastName;
	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	@JoinColumn(name="dashboard_id")
	private Dashboard dashboard;
	
	@OneToMany(mappedBy = "profile", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<Project> projectList;	
	
	@OneToMany(mappedBy="sentByProfile", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<Invitation> sentByProfileInvitationList;
	
	@OneToMany(mappedBy="sentToProfile", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<Invitation> sentToProfileInvitationList;
	
	@OneToMany(mappedBy="sendFrom", fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	private List<InvitationByEmail> invitationByEmailList;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<Invitation> getSentByProfileInvitationList() {
		return sentByProfileInvitationList;
	}

	public void setSentByProfileInvitationList(List<Invitation> sentByInvitationList) {
		this.sentByProfileInvitationList = sentByInvitationList;
	}

	public List<Invitation> getSentToProfileInvitationList() {
		return sentToProfileInvitationList;
	}

	public void setSentToProfileInvitationList(List<Invitation> sentToInvitationList) {
		this.sentToProfileInvitationList = sentToInvitationList;
	}

	@Transient
	public String getFullName() {
		return name+" "+lastName;
	}

	public List<InvitationByEmail> getInvitationByEmailList() {
		return invitationByEmailList;
	}

	public void setInvitationByEmailList(
			List<InvitationByEmail> invitationByEmailList) {
		this.invitationByEmailList = invitationByEmailList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Dashboard getDashboard() {
		return dashboard;
	}

	public void setDashboard(Dashboard dashboard) {
		this.dashboard = dashboard;
	}

	@Transient
	public boolean equals(Object obj) {
		if(obj instanceof Profile) {		
			Profile profile = (Profile)obj;
			if(profile.getId() == this.getId()) {
				return true;
			}			
		}		
		return false;		
	}

}