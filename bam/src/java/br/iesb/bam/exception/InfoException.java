package br.iesb.bam.exception;

/**
 *
 * @author jacob.santana
 */
public class InfoException extends GenericaException {

    public InfoException(String message) {
        super(message);
    }

    public InfoException(Throwable cause) {
        super(cause);
    }

    public InfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
