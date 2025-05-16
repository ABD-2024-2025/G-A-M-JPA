package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.model.multas.TipoIncidencia;


/*
 * IncidenciaDAO. Sirve para gestionar la persistencia de los objetos de tipo Incidencia.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * 
 * @version 1.5
 * @since 1.5
 */
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

    public void create(Incidencia incidencia) {
        entityManager.persist(incidencia);
    }

    public void delete(Incidencia incidencia) {
        Incidencia merged = entityManager.merge(incidencia);
        entityManager.remove(merged);
    }
}
