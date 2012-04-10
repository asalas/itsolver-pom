package itsolver.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Clase abstracta con la informacion basica de un usuario del sistema.
 * 
 */
@Entity
@Table(name="user")
public  class User implements Serializable {

	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *Identificador del usuario
	 */	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id", nullable=false)	
	private Long id;
	
	
	
	/**
	 *Correo electronico del usuario. 
	 */
	@Column(name="email", length=30,nullable=true)
	private String email;
	
	/**
	 *El nombre del usuario para acceder al sistema 
	 */
	@Column(name="user_name", length=30,nullable=true)
	private String userName;
	
	/**
	 *Contrasenia del usuario. 
	 */
	@Column(name="password", nullable=true)
	private String password;
	

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch=FetchType.EAGER)
	@JoinColumn(name="profile_id")
	private Profile profile;
	
	
	/**
	 * Devuelve el Id del usuario
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Asigna el Id del usuario
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Devuelve el correo electronico del usuario.
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Asigna el email del usuario
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Devuelve el nombre con el que el usuario accede al sistema
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Asigna  el nombre con el que el usuario accede al sistema
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Devuelve el password con el que el usuario accede al sistema
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Asigna el password con el que el usuario accede al sistema
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Profile getProfile() {
		return profile;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User)obj;
			Long uId = user.getId();
			if(uId != null && this.getId() != null) {
				if(uId==this.getId()) {
					return true;
				}
			}
		}
		
		return false;
	}		
}
