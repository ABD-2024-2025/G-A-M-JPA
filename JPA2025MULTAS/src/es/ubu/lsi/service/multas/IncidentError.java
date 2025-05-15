package es.ubu.lsi.service.multas;

/**
 * Error code.
 * 
 * Listado de posibles errores que se pueden producir.
 * 
 * @author <a href="mailto:jmaudes@ubu.es">Jesús Maudes</a>
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:mmabad@ubu.es">Mario Martínez</a>
 * @author <a href="mailto:srarribas@ubu.es">Sandra Rodríguez</a>
 * @since 1.0
 *
 */
public enum IncidentError {
    CONDUCTOR_NOT_FOUND(-20000, "Conductor no encontrado"),
    TIPO_INCIDENCIA_NOT_FOUND(-20001, "Tipo de incidencia no encontrado"),
    NOT_ENOUGH_POINTS(-20002, "No hay puntos suficientes"),
    NOT_EXIST_INCIDENT_TYPE(-20003, "No existe el tipo de incidencia"),
    UNKNOWN(-20999, "Error desconocido");

    private final int code;
    private final String message;

    IncidentError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
}
