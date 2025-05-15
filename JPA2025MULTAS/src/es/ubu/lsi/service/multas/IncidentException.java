package es.ubu.lsi.service.multas;

import es.ubu.lsi.service.PersistenceException;

/**
 * Reservation exception.
 * 
 * @author <a href="mailto:jmaudes@ubu.es">Jesús Maudes</a>
 * @author <a href="mailto:rmartico@ubu.es">Raúl Marticorena</a>
 * @author <a href="mailto:mmabad@ubu.es">Mario Martínez</a>
 * @author <a href="mailto:srarribas@ubu.es">Sandra Rodríguez</a>
 * @author <a href="mailto:pgdiaz@ubu.es">Pablo García</a> 
 * @since 1.0
 *
 */
public class IncidentException extends PersistenceException {

    /** Default. */
    private static final long serialVersionUID = 1L;

    /** Error code. */
    private final int code;

    /** Incident error. */
    private final IncidentError error;

    /**
     * Constructor.
     * 
     * @param error
     *            incident error
     */
    public IncidentException(IncidentError error) {
        super(error.getMessage());
        this.code = error.getCode();
        this.error = error;
    }

    /**
     * Constructor.
     * 
     * @param code
     *            error code
     * @param message
     *            error message
     */
    public IncidentException(int code, String message) {
        super(message);
        this.code = code;
        this.error = null;
    }

    /**
     * Gets error code.
     * 
     * @return error code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets incident error.
     * 
     * @return incident error
     */
    public IncidentError getError() {
        return error;
    }
}
