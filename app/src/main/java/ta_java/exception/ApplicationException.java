package ta_java.exception;


  /**
 * Custom exception related to application operations
 */
public class ApplicationException extends Exception {
  public ApplicationException(String msg) {
    super(msg);
  }
}