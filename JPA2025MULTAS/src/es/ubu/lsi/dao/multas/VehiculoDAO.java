package es.ubu.lsi.dao.multas;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.multas.Vehiculo;

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
