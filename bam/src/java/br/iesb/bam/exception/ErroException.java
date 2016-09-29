package br.iesb.bam.exception;

/**
 *
 * @author jacob.santana
 */
public class ErroException extends GenericaException {

    public ErroException(String message) {
        super(message);
    }

    public ErroException(Throwable cause) {
        super(cause);
    }

    public ErroException(String message, Throwable cause) {
        super(message, cause);
    }
}
