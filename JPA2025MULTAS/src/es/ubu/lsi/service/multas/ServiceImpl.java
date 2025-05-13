package es.ubu.lsi.service.multas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import es.ubu.lsi.model.multas.Vehiculo;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.PersistenceService;
import es.ubu.lsi.test.util.PoolDeConexiones;

public class ServiceImpl extends PersistenceService implements Service {

	
    /**
	 * Inserta una indicencia para un conductor dado, verificando la disponibilidad de puntos en el carnét y
	 * descontando la cantidad correspondiente.
	 * 
	 * @param nif NIF del cliente que alquila el coche.
	 * @param tipo  Tipo de incidencia a realizar.
	 * @param fecha   Fecha de registro de la incidencia.
	 * @throws SQLException Si ocurre un error en la base de datos.
	 */
	@Override
	public void insertarIncidencia(Date fecha, String nif, long tipo) throws PersistenceException {
		PoolDeConexiones pool = PoolDeConexiones.getInstance();
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
		
        try {
            con = pool.getConnection();
            
            // Verificar si el conductor existe
            st = con.prepareStatement("SELECT COUNT(*) FROM Conductor WHERE NIF = ?");
            st.setString(1, nif);
            rs = st.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
            	//TODO Poner excepcion correspondiente avisando que no existe el conductoe
            }
            rs.close();
            st.close();
            
            // Calcular puntos
            st = con.prepareStatement("SELECT puntos FROM Conductor WHERE NIF = ?");
            st.setString(1, nif);
            int puntosConductor = st.executeUpdate();
            
            st = con.prepareStatement("SELECT idTipo FROM Incidencia WHERE tipoIncidencia = ?");
            st.setLong(1, tipo);
            int idTipo = st.executeUpdate();
            
            st = con.prepareStatement("SELECT valor FROM TipoIncidencia WHERE id = ?");
            st.setInt(1, idTipo);
            int puntosParaQuitar = st.executeUpdate();
            
            int puntosRestantes = puntosConductor - puntosParaQuitar;
            if ( puntosRestantes <= 0 ) {
            	puntosRestantes = 0;
            	//TODO Poner excepcion correspondiente avisando que no hay puntos
            }
            
            // Insertar incidencia
            st = con.prepareStatement(
                "INSERT INTO Incidencia (fecha, NIF, anotacion, tipoIncidencia) " +
                "VALUES (?, ?, ?, ?)");
            st.setDate(1, sqlFecha);
            st.setString(2, nif);
            
            st.setLong(4, tipo);
            int nFilas = st.executeUpdate();
            if (nFilas == 0) {
            	//TODO Poner excepcion correspondiente avisando que no se ha podido insertar incidencia
            }
            st.close();
            
            // Actualizar puntos conductor
            st = con.prepareStatement(
                "INSERT INTO Conductor (NIF, nombre, apellido, direccion, puntos, idauto, incidencias) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)");
            st.setString(2, nif);
            st.setInt(5, puntosRestantes);
            // TODO Añadir la incidencia nueva que hemos creado a la lista del conductor
            st.executeUpdate();
            int nFilas1 = st.executeUpdate();
            if (nFilas1 == 0) {
            	//TODO Poner excepcion correspondiente avisando que no se han podido actualizar los puntos del conductor
            }
            st.close();
            con.commit();
        } catch (SQLException e) {

        } finally {

        }
       
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
