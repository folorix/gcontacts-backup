/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.touret.gcontacts;

/**
 *
 * @author touret-a
 */
public class GContactException extends Exception {

    /**
     * Creates a new instance of <code>GContactException</code> without detail message.
     */
    public GContactException() {
    }

    /**
     * Constructs an instance of <code>GContactException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public GContactException(String msg) {
        super(msg);
    }

    public GContactException(Throwable cause) {
        super(cause);
    }

    public GContactException(String message, Throwable cause) {
        super(message, cause);
    }
}
