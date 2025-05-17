package es.ubu.lsi.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.multas.IncidentError;
import es.ubu.lsi.service.multas.IncidentException;
import es.ubu.lsi.service.multas.Service;
import es.ubu.lsi.service.multas.ServiceImpl;
import es.ubu.lsi.test.util.PoolDeConexiones; // Añadido para inicializar el pool

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

    private static final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static final Service service = new ServiceImpl();

    /**
     * Excepción personalizada para los tests de alumno.
     */
    public static class TestAlumnoException extends Exception {
        private static final long serialVersionUID = 1L;
		public TestAlumnoException(String msg) { super(msg); }
        public TestAlumnoException(String msg, Throwable cause) { super(msg, cause); }
    }

    /**
     * Inicializa el pool de conexiones antes de ejecutar los tests.
     */
    private static void init() {
        try {
            PoolDeConexiones.getInstance();
        } catch (Exception e) {
            System.err.println("Error inicializando el pool de conexiones: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== TESTS ALUMNO ===");
        init();
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
    }

    /**
     * Test: Intenta insertar una incidencia para un conductor que no existe.
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
     * Test: Indulta a un conductor (elimina incidencias y restaura puntos).
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
