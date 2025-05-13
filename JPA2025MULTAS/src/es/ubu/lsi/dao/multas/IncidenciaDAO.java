package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.model.multas.TipoIncidencia;

public class IncidenciaDAO extends JpaDAO<Incidencia, TipoIncidencia> {
    
    public IncidenciaDAO(EntityManager em) {
        super(em);
    }
    
    @Override
    public List<Incidencia> findAll() {
        TypedQuery<Incidencia> query = entityManager.createQuery(
            "SELECT i FROM Incidencia i", Incidencia.class);
        return query.getResultList();
    }
}
