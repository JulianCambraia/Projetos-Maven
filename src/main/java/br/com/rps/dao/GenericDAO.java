/*
 * Classe DAO Genérica para operações de CRUD.
 * Só irá realizar transações.
 * Nada de regras de negócio nesta classe Genérica
 */
package br.com.rps.dao;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author julian.fernando
 */

public class GenericDAO<T> {
    
    private static final String UNIT_NAME ="CrudPU";
    
    @PersistenceContext(unitName = UNIT_NAME)
    private EntityManager entityManager;
    
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void save(T entity) {
        entityManager.persist(entity);
    }
    
    protected void delete(Object id, Class<T> classe) {
        // entidade genérica a ser removida
        T entityToBeRemoved = entityManager.getReference(classe, id);
        
        entityManager.remove(entityToBeRemoved);
    }
    
    public T update(T entity) {
        return entityManager.merge(entity);
    }
    
    public T find(long entityID) {
        return entityManager.find(entityClass, entityID);
    }
    
    public List<T> findAll() {
        CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return entityManager.createQuery(cq).getResultList();
    }
    
    protected T findOneResult(String namedQuery, Map<String,Object> parameters) {
        T result = null;
        try {
            Query query = entityManager.createNamedQuery(namedQuery);
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryparameters(query,parameters);
            }
            result = (T) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("erro enquanto executava a query" + e.getMessage());
            e.printStackTrace();
        }
        
        return result;
    }

    private void populateQueryparameters(Query query, Map<String, Object> parameters) {
        for (Map.Entry<String, Object> entrySet : parameters.entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            query.setParameter(key, value);
        }
    }
}
