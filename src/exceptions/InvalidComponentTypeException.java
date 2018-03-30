/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author leonardoho
 */
public class InvalidComponentTypeException extends Exception {

    public InvalidComponentTypeException() {
        super();
    }

    public InvalidComponentTypeException(String message) {
        super(message);
    }

    public InvalidComponentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidComponentTypeException(Throwable cause) {
        super(cause);
    }

}
