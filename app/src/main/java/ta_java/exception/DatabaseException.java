package ta_java.exception;

  /**
 * Custom exception related to operations in database
 */
public class DatabaseException extends Exception {
  public DatabaseException(String msg) {
    super(msg);
  }
}