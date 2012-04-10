package itsolver.model.dao;
import itsolver.model.entity.InvitationByEmail;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Clase que implementa el patron de disenio Data Access Object
 * @author 
 *
 * @param <T>
 * @param <ID>
 */
@Repository
public abstract class GenericDAOImpl<T,ID extends Serializable> implements GenericDAO<T, ID> {
	
	protected static Logger logger = Logger.getLogger(GenericDAOImpl.class);
    static{logger.setLevel(Level.DEBUG);}
    
    
    
	private Class<T> entityBeanType;

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
    EntityManager entityManager;
    EntityTransaction entityTransaction;
        
    public final void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;        
    }
    
    @SuppressWarnings("unchecked")
	public GenericDAOImpl(){    	
    	this.entityBeanType = (Class<T>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public EntityManager getEntityManager() {
        if (entityManager == null)
            throw new IllegalStateException("EntityManager has not been set on DAO before usage");
        return entityManager;
    }
    
    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    public T findById(ID id) {    	   	
    	T entityResult = getEntityManager().find(entityBeanType, id); 
        return entityResult;
    }
    

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return getEntityManager().createQuery("from " + getEntityBeanType().getName() ).getResultList();
    }    


    @SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance, String... excludeProperty) {
        // Using Hibernate, more difficult with EntityManager and EJB-QL
        org.hibernate.Criteria crit = ((org.hibernate.ejb.HibernateEntityManager)getEntityManager())
                            .getSession()
                            .createCriteria(getEntityBeanType());
        org.hibernate.criterion.Example example =
                org.hibernate.criterion.Example.create(exampleInstance);
        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }
        crit.add(example);
        return crit.list();
    }

    
    @Transactional
    public void persist(T entity) {
        getEntityManager().persist(entity);  
    }
    
    @Transactional
    public void merge(T entity) {
        getEntityManager().merge(entity);
    }
    
    @Transactional
    public void remove(T entity) {    
    	getEntityManager().remove(entity);
    }
    
    public void refresh(T oldEntity) {
    	getEntityManager().refresh(oldEntity);
    }

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		GenericDAOImpl.logger = logger;
	}

	public InvitationByEmail findByHiloId(String hiloId) {
		// TODO Auto-generated method stub
		return null;
	}

}
