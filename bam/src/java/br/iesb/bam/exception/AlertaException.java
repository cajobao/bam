/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iesb.bam.exception;

/**
 *
 * @author jacob.santana
 */
public class AlertaException extends GenericaException {

    public AlertaException(String message) {
        super(message);
    }

    public AlertaException(Throwable cause) {
        super(cause);
    }

    public AlertaException(String message, Throwable cause) {
        super(message, cause);
    }
}
