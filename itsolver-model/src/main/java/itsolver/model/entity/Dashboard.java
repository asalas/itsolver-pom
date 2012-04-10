package itsolver.model.entity;

import java.util.ArrayList;
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
@Table(name="dashboard")
public class Dashboard {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dashboard_id", nullable=false)	
	private long Id;
	
	/*@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="profile_id")
	private Profile profile;*/
	
	@OneToMany(fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,  CascadeType.REMOVE } )
	@org.hibernate.annotations.Cascade(
	    value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN
	 )
	//@OneToMany(cascade={CascadeType.MERGE},fetch=FetchType.LAZY)
	private List<Notification> notificationList = new ArrayList<Notification>();

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public List<Notification> getNotificationList() {		
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}
}
