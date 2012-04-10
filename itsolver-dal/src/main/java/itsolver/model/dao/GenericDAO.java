package itsolver.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interfaz para implementar el patro de disenio Data Access Object 
 * @author rene
 *
 * @param <T>
 * @param <ID>
 */
public interface GenericDAO <T, ID extends Serializable> {
	/**
     * Encuentra una entidad T por su id de tipo ID y la regresa si es que existe.
     * En caso contrario regresa null
     * @param id id de la Entidad de tipo ID
     * @return Entidad con la que conincide el id. Si no hay coincidencia regresa null.
     */
    T findById(ID id);
    
    /**
     * Regresa todas las entidades de tipo T en la base de datos.
     * @return Todas las entidades en base de datos de tipo T
     */
    List<T> findAll();
        
    
    /**
     * Encuentra todas las entidades de tipo T similares al ejemplo.
     * @param exampleInstance Entidad ejemplo usuada para la comparacion de base de datos.
     * @param excludeProperty Lista de variables que se excluyen de la comparacion.
     * @return Lista de entidades que coinciden con el ejemplo
     */
    List<T> findByExample(T exampleInstance, String... excludeProperty);
    
    /**
     * Almacena una entidad
     * @param entity Entidad
     */
    void persist(T entity);
    
    void merge(T detachedEntity);
    
    
    void refresh(T oldEntity);    
    
 
    void remove(T entity);

}
