package edu.eci.arsw.exams.moneylaunderingapi;



/**
 *
 * @author jimmy
 */
public class SuspectAccountException extends Exception{

    public SuspectAccountException(String message) {
        super(message);
    }

    public SuspectAccountException(String message, Throwable cause) {
        super(message, cause);
    }
    
}