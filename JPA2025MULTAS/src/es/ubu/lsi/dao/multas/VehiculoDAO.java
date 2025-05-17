package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Vehiculo;

/**
 * VehiculoDAO. Sirve para gestionar la persistencia de los objetos de tipo Vehiculo.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * 
 * @version 1.5
 * @since 1.5
 */
public class VehiculoDAO extends JpaDAO<Vehiculo, Integer> {
    
    public VehiculoDAO(EntityManager em) {
        super(em);
    }
    
    @Override
    public List<Vehiculo> findAll() {
        TypedQuery<Vehiculo> query = entityManager.createQuery(
            "SELECT v FROM Vehiculo v", Vehiculo.class);
        return query.getResultList();
    }
}
