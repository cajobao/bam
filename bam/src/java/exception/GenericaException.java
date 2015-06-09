/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author jacob.santana
 */
public class GenericaException extends Exception {

    public GenericaException(String message) {
        super(message);
    }

    public GenericaException(Throwable cause) {
        super(cause);
    }

    public GenericaException(String message, Throwable cause) {
        super(message, cause);
    }
}
