package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.TipoIncidencia;

/**
 * TipoIncidenciaDAO. Sirve para gestionar la persistencia de los objetos de tipo TipoIncidencia.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * 
 * @version 1.5
 * @since 1.5
 */
public class TipoIncidenciaDAO extends JpaDAO<TipoIncidencia, Integer> {
    
    public TipoIncidenciaDAO(EntityManager em) {
        super(em);
    }
    
    @Override
    public List<TipoIncidencia> findAll() {
        TypedQuery<TipoIncidencia> query = entityManager.createQuery(
            "SELECT t FROM TipoIncidencia t", TipoIncidencia.class);
        return query.getResultList();
    }
}
