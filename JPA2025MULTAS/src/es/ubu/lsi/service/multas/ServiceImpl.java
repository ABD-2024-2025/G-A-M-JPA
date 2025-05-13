package es.ubu.lsi.service.multas;

import java.util.Date;
import java.util.List;

import es.ubu.lsi.model.multas.Vehiculo;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.PersistenceService;

public class ServiceImpl extends PersistenceService implements Service {

	@Override
	public void insertarIncidencia(Date fecha, String nif, long tipo) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void indultar(String nif) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vehiculo> consultarVehiculos() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

}
