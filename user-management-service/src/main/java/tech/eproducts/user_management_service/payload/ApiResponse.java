/**
 * Represents an API response containing success status and a message.
 */
package tech.eproducts.user_management_service.payload;

public class ApiResponse {
    /** Indicates whether the API operation was successful. */
    private boolean success;
    
    /** Contains a message describing the result of the API operation. */
    private String message;

    /**
     * Constructs a new ApiResponse with the given success status and message.
     *
     * @param success whether the API operation was successful
     * @param message a message describing the result of the API operation
     */
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Returns the success status of the API operation.
     *
     * @return true if the operation was successful, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the success status of the API operation.
     *
     * @param success the success status to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns the message describing the result of the API operation.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message describing the result of the API operation.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
