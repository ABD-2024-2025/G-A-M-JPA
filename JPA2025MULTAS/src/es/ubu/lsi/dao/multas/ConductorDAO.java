package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Conductor;

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
}
