package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.DireccionPostal;

public class DireccionPostalDAO extends JpaDAO<DireccionPostal, Integer> {

    public DireccionPostalDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<DireccionPostal> findAll() {
        TypedQuery<DireccionPostal> query = entityManager.createQuery(
            "SELECT d FROM DireccionPostal d", DireccionPostal.class);
        return query.getResultList();
    }
}
