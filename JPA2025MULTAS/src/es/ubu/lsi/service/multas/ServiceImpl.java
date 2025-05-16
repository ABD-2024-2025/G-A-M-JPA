package es.ubu.lsi.service.multas;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import es.ubu.lsi.dao.multas.ConductorDAO;
import es.ubu.lsi.dao.multas.IncidenciaDAO;
import es.ubu.lsi.dao.multas.TipoIncidenciaDAO;
import es.ubu.lsi.dao.multas.VehiculoDAO;
import es.ubu.lsi.model.multas.Conductor;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.model.multas.TipoIncidencia;
import es.ubu.lsi.model.multas.Vehiculo;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.PersistenceFactorySingleton;
import es.ubu.lsi.service.PersistenceService;

/**
 * La clase que nos permite gestionar las incidencias de los conductores.
 * Utilizamos el patrón de diseño DAO para gestionar la persistencia de los objetos.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara ABejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.0
 */
public class ServiceImpl extends PersistenceService implements Service {

    @Override
    public void insertarIncidencia(Date fecha, String nif, long tipo) throws PersistenceException {
        EntityManager em = PersistenceFactorySingleton.getEntityManager();
        try {
            em.getTransaction().begin();

            ConductorDAO conductorDAO = new ConductorDAO(em);
            TipoIncidenciaDAO tipoDAO = new TipoIncidenciaDAO(em);
            IncidenciaDAO incidenciaDAO = new IncidenciaDAO(em);

            Conductor conductor = conductorDAO.findById(nif);
            if (conductor == null) {
                throw new IncidentException(IncidentError.CONDUCTOR_NOT_FOUND);
            }

            TipoIncidencia tipoIncidencia = tipoDAO.findById((int) tipo);
            if (tipoIncidencia == null) {
                throw new IncidentException(IncidentError.NOT_EXIST_INCIDENT_TYPE);
            }

            int puntosRestar = tipoIncidencia.getPuntos();
            if (conductor.getPuntos() < puntosRestar) {
                throw new IncidentException(IncidentError.NOT_ENOUGH_POINTS.getCode(),
                        IncidentError.NOT_ENOUGH_POINTS.getMessage());
            }

            // Crear la incidencia
            Incidencia incidencia = new Incidencia();
            incidencia.setConductor(conductor);
            incidencia.setTipo(tipoIncidencia);
            incidencia.setFecha(fecha);
            incidenciaDAO.create(incidencia);

            // Actualizar puntos
            conductor.setPuntos(conductor.getPuntos() - puntosRestar);
            conductorDAO.update(conductor);

            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void indultar(String nif) throws PersistenceException {
        EntityManager em = PersistenceFactorySingleton.getEntityManager();
        try {
            em.getTransaction().begin();

            ConductorDAO conductorDAO = new ConductorDAO(em);
            IncidenciaDAO incidenciaDAO = new IncidenciaDAO(em);

            Conductor conductor = conductorDAO.findById(nif);
            if (conductor == null) {
                throw new IncidentException(IncidentError.CONDUCTOR_NOT_FOUND.getCode(),
                        IncidentError.CONDUCTOR_NOT_FOUND.getMessage());
            }

            // Eliminar incidencias
            List<Incidencia> incidencias = em.createQuery(
                "SELECT i FROM Incidencia i WHERE i.conductor.nif = :nif", Incidencia.class)
                .setParameter("nif", nif)
                .getResultList();
            for (Incidencia inc : incidencias) {
                incidenciaDAO.delete(inc);
            }

            // Restaurar puntos
            conductor.setPuntos(Service.MAXIMO_PUNTOS);
            conductorDAO.update(conductor);

            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new PersistenceException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Vehiculo> consultarVehiculos() throws PersistenceException {
        EntityManager em = PersistenceFactorySingleton.getEntityManager();
        try {
            VehiculoDAO vehiculoDAO = new VehiculoDAO(em);
            return vehiculoDAO.findAll();
        } finally {
            em.close();
        }
    }
}
