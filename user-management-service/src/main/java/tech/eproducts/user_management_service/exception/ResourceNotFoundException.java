package tech.eproducts.user_management_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested resource is not found.
 * This exception is mapped to HTTP 404 NOT FOUND status.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  private String resourceName;
  private String fieldName;
  private Object fieldValue;

  /**
   * Constructs a new ResourceNotFoundException with the specified detail message.
   *
   * @param message the detail message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }

  /**
   * Constructs a new ResourceNotFoundException with a formatted detail message.
   *
   * @param resourceName the name of the resource that was not found
   * @param fieldName the name of the field used in the search
   * @param fieldValue the value of the field used in the search
   */
  public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
    super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    this.resourceName = resourceName;
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  /**
   * Returns the name of the resource that was not found.
   *
   * @return the resource name
   */
  public String getResourceName() {
    return resourceName;
  }

  /**
   * Returns the name of the field used in the search.
   *
   * @return the field name
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * Returns the value of the field used in the search.
   *
   * @return the field value
   */
  public Object getFieldValue() {
    return fieldValue;
  }
}
