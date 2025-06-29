package es.ubu.lsi.test;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.model.multas.Vehiculo;
import es.ubu.lsi.model.multas.Conductor;
import es.ubu.lsi.model.multas.Incidencia;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.multas.IncidentError;
import es.ubu.lsi.service.multas.IncidentException;
import es.ubu.lsi.service.multas.Service;
import es.ubu.lsi.service.multas.ServiceImpl;
import es.ubu.lsi.test.util.ExecuteScript;
import es.ubu.lsi.test.util.PoolDeConexiones;

/**
 * Test client.
 * 
 * @author <a href="mailto:jmaudes@ubu.es">Jesús Maudes</a>
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:mmabad@ubu.es">Mario Martínez</a>
 * @author <a href="mailto:srarribas@ubu.es">Sandra Rodríguez</a>
 * @author <a href="mailto:pgdiaz@ubu.es">Pablo García</a>
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.5
 * @since 1.0
 */
public class TestClient {

	/** Logger. */
	private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

	/** Connection pool. */
	private static PoolDeConexiones pool;

	/** Path. */
	private static final String SCRIPT_PATH = "sql/";

	/** Simple date format. */
	private static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	/** Entity Manager. */

	/**
	 * Main.
	 * 
	 * @param args arguments.
	 */
	public static void main(String[] args) {
		try {
			System.out.println("Iniciando...");
			init();
			System.out.println("Probando el servicio...");
			testService();
			System.out.println("FIN.............");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Error grave en la aplicación {}", ex.getMessage());
		}
	}

	/**
	 * Init pool.
	 */
	static public void init() {
		try {
			pool = PoolDeConexiones.getInstance();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Create tables.
	 */
	static public void createTables() {
		ExecuteScript.run(SCRIPT_PATH + "script.sql");
	}

	/**
	 * Test service using JDBC and JPA.
	 */
	static void testService() throws Exception {
		createTables();
		Service implService = null;
		try {
			// JPA Service
			implService = new ServiceImpl();
			System.out.println("Framework y servicio iniciado...");
			
			//Entity Manager PRUEBA
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Multas");
			EntityManager em = emf.createEntityManager();
			//VehiculoDAO vehiculoDao = new VehiculoDAO(em);

			// insertar incidencia para el conductor 10000000A con 3 puntos de penalización
			insertarIncidenciaCorrecta(implService);
			
			// intenta insertar con tipo de incidencia que no existe
			insertarIncidenciaConTipoIncidenciaErroneo(implService);
					
			// comprueba que la consulta de pistas carga todos los datos
			consultarVehiculosUsandoGrafo(em);

		} catch (Exception e) { // for testing code...
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			pool = null;
		}
	} // testClient

	/**
	
	/**
	 * Inserta una incidencia correcta.
	 * 
	 * @param implService implementación del servicio
	 * @throws Exception error en test
	 */
	private static void insertarIncidenciaCorrecta(Service implService) throws Exception {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			System.out.println("Insertar incidencia correcta");
			implService.insertarIncidencia(dateformat.parse("15/05/2019 16:00"), "10000000A", 3); // 3 es moderada con 3
																									// puntos
			// insertamos incidencia descontando 3 puntos al conductor 10000000A que tenía 9
			// inicialmente

			con = pool.getConnection();
			
			// Comprobar si la incidencia se ha añadido
			st = con.createStatement();
			rs = st.executeQuery("SELECT fecha||'-'||nif||'-'||idtipo FROM INCIDENCIA ORDER BY fecha, nif, idtipo");

			StringBuilder resultado = new StringBuilder();
			while (rs.next()) {
				resultado.append(rs.getString(1));
				resultado.append("\n");
			}
			logger.debug(resultado.toString());
			String cadenaEsperada =
			// @formatter:off
			"11/04/19 12:00:00,000000-10000000A-2\n" +
			"12/04/19 11:00:00,000000-10000000B-2\n" +
			"12/04/19 12:00:00,000000-10000000C-2\n" +
			"12/04/19 12:00:00,000000-20000000C-2\n" +
			"12/04/19 13:00:00,000000-10000000C-3\n" +
			"12/04/19 13:00:00,000000-20000000C-3\n" +
			"13/04/19 14:00:00,000000-30000000A-3\n" +			
			"13/04/19 15:00:00,000000-30000000B-2\n" +
			"13/04/19 16:00:00,000000-30000000C-1\n" +
			"15/05/19 16:00:00,000000-10000000A-3\n"; // nueva fila
			// @formatter:on
			
			if (cadenaEsperada.equals(resultado.toString())) {
				System.out.println("\tOK incidencia bien insertada");
			} else {
				System.out.println("\tERROR incidencia mal insertada");
			}
			rs.close();
			rs = st.executeQuery("SELECT puntos FROM conductor WHERE NIF='10000000A'");
			StringBuilder resultadoEsperadoPuntos = new StringBuilder();
			while (rs.next()) {
				resultadoEsperadoPuntos.append(rs.getString(1));
			}
			String puntosEsperados = "3"; // le deberíamos descontar 3 puntos quendado 6-3 = 3 puntos.
			if (puntosEsperados.equals(resultadoEsperadoPuntos.toString())) {
				System.out.println("\tOK actualiza bien los puntos del conductor");
			} else {
				System.out.println("\tERROR no descuenta bien los puntos de la incidencia del conductor");
			}
			con.commit();
		} catch (Exception ex) {
			logger.error("ERROR grave en test. " + ex.getLocalizedMessage());
			con.rollback();
			throw ex;
		} finally {
			cerrarRecursos(con, st, rs);
		}
	}

	/**
	 * Intenta insertar una incidencia cuyo tipo no existe.
	 * 
	 * @param implService servicio
	 */
	private static void insertarIncidenciaConTipoIncidenciaErroneo(Service implService) {
		try {
			System.out.println("Insertar incidencia con tipo erróneo");
			// fecha y usuario correcto
			implService.insertarIncidencia(dateformat.parse("15/05/2019 17:00"), "10000000A", 5); // 5 NO EXISTE
			System.out.println("\tERROR NO detecta que NO existe el tipo de incidencia y finaliza la transacción");

		} catch (IncidentException ex) {
			if (ex.getError() == IncidentError.NOT_EXIST_INCIDENT_TYPE) {
				System.out.println("\tOK detecta correctamente que NO existe ese tipo de incidencia");
			} else {
				System.out.println("\tERROR detecta un error diferente al esperado:  " + ex.getError().toString());
			}
		} catch (PersistenceException ex) {
			logger.error("ERROR en transacción de inserción de incidencia con JPA: " + ex.getLocalizedMessage());
			throw new RuntimeException("Error en inserción de incidencia en vehiculos", ex);
		} catch(Exception ex) {
			logger.error("ERROR grave de programación en transacción de inserción de incicencia con JPA: " + ex.getLocalizedMessage());
			throw new RuntimeException("Error grave en inserción de incidencia en vehiculos", ex);
		}
	}

	/**
	 * Prueba consulta de vehiculos, cargando datos completos desde un grafo de
	 * entidades.
	 * 
	 * @param implService implementación del servicio
	 * 
	 * @param em           EntityManager inyectado o pasado como parámetro
	 */
	private static void consultarVehiculosUsandoGrafo(EntityManager em) {
		try {
			System.out.println("Información completa con grafos de entidades...");
			EntityGraph<?> graph = em.createEntityGraph(Vehiculo.class);
			graph.addSubgraph("conductores");

			List<Vehiculo> vehiculos = em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class)
			    .setHint("javax.persistence.fetchgraph", graph)
			    .getResultList();
	
			for (Vehiculo vehiculo : vehiculos) {
				System.out.println(vehiculo.toString());
				List<Conductor> conductores = vehiculo.getConductores();
				if (conductores != null) {
				    for (Conductor conductor : conductores) {
						System.out.println("\t" + conductor.toString());
						Set<Incidencia> incidencias = ((Conductor) conductor).getIncidencias();
						if (incidencias != null) {
							for (Incidencia incidencia : incidencias) {
								System.out.println("\t\t" + incidencia.toString());
							}
						}
					}
				}
			}
			System.out.println("OK Sin excepciones en la consulta completa y acceso posterior");
		} catch (Exception ex) { 
			logger.error("ERROR en transacción de consultas de vehiculos con JPA: " + ex.getLocalizedMessage());
			throw new RuntimeException("Error en consulta de vehiculos", ex);
		}
	}

	/**
	 * Cierra recursos de la transacción.
	 * 
	 * @param con conexión
	 * @param st  sentencia
	 * @param rs  conjunto de datos
	 * @throws SQLException si se produce cualquier error SQL
	 */
	private static void cerrarRecursos(Connection con, Statement st, ResultSet rs) throws SQLException {
		if (rs != null && !rs.isClosed())
			rs.close();
		if (st != null && !st.isClosed())
			st.close();
		if (con != null && !con.isClosed())
			con.close();
	}

}
