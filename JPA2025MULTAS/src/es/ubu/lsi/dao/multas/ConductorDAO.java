package es.ubu.lsi.dao.multas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Conductor;

/**
 * ConductorDAO. Sirve para gestionar la persistencia de los objetos de tipo Conductor.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * 
 * @version 1.5
 * @since 1.5
 */
public class ConductorDAO extends JpaDAO<Conductor, String> {
    
    public ConductorDAO(EntityManager em) {
        super(em);
    }
    
    @Override
    public List<Conductor> findAll() {
        TypedQuery<Conductor> query = entityManager.createQuery(
            "SELECT c FROM Conductor c", Conductor.class);
        return query.getResultList();
    }

    public void update(Conductor conductor) {
        entityManager.merge(conductor);
    }
}
