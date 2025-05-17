package es.ubu.lsi.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.multas.IncidentError;
import es.ubu.lsi.service.multas.IncidentException;
import es.ubu.lsi.service.multas.Service;
import es.ubu.lsi.service.multas.ServiceImpl;

/**
 * Nuestros test personalizados para la práctica.
 * 
 * Cada función es un test independiente. Se usan excepciones personalizadas para los tests.
 * 
 * @author <a href="https://joseleelportfolio.vercel.app">José Gallardo</a>
 * @author <a href="mailto:sap1013@alu.ubu.es">Sara Abejón</a>
 * @author <a href="mailto:mmg1065@alu.ubu.es">María Molina</a>
 * 
 * @version 1.6
 * @since 1.5
 */
public class TestClientAlumno {

	/**
	 * Formato de fecha utilizado en los tests.
	 */
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    /**
	 * Servicio de incidencias.
	 */
    private static final Service service = new ServiceImpl();

    /**
     * Excepción personalizada para los tests.
     */
    public static class TestAlumnoException extends Exception {
        private static final long serialVersionUID = 1L;
		public TestAlumnoException(String msg) { super(msg); }
        public TestAlumnoException(String msg, Throwable cause) { super(msg, cause); }
    }
    
    /**
     * Ejecuta los tests.
     * 
     * @param args argumentos por la línea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("=== TESTS ALUMNO ===");
        try {
            testInsertarIncidenciaConductorNoExiste();
        } catch (Exception e) {
            System.out.println("Fallo testInsertarIncidenciaConductorNoExiste: " + e.getMessage());
        }

        try {
            testIndultarConductor();
        } catch (Exception e) {
            System.out.println("Fallo testIndultarConductor: " + e.getMessage());
        }
        System.out.println("=== TESTS ALUMNO ===");
    }

    /**
     * Test: Inserta una incidencia con un conductor que no existe.
     * 
     * @throws Exception si ocurre un error al insertar la incidencia.
     */
    public static void testInsertarIncidenciaConductorNoExiste() throws Exception {
        System.out.println("[testInsertarIncidenciaConductorNoExiste] Insertando incidencia con conductor inexistente...");
        Date fecha = dateformat.parse("21/05/2019 10:00");
        String nif = "99999999Z"; // NIF que no existe
        long tipo = 2;
        try {
            service.insertarIncidencia(fecha, nif, tipo);
            throw new TestAlumnoException("ERROR: No lanzó excepción para conductor inexistente");
        } catch (IncidentException e) {
            if (e.getError() == IncidentError.CONDUCTOR_NOT_FOUND) {
                System.out.println("\tOK: Detecta conductor inexistente.");
            } else {
                throw new TestAlumnoException("ERROR: Excepción inesperada: " + e.getError(), e);
            }
        }
    }

    /**
	 * Test: Indulta a un conductor.
	 * 
	 * @throws Exception si ocurre un error al indultar al conductor.
	 */
    public static void testIndultarConductor() throws Exception {
        System.out.println("[testIndultarConductor] Indultando conductor...");
        String nif = "10000000A";
        try {
            service.indultar(nif);
            System.out.println("\tOK: Indulto realizado correctamente.");
        } catch (PersistenceException e) {
            throw new TestAlumnoException("No se pudo indultar al conductor", e);
        }
    }
}
