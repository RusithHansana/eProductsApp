/**
 * Custom annotation to inject the currently authenticated user into Spring MVC controller methods.
 * This annotation is a convenience wrapper around Spring Security's @AuthenticationPrincipal.
 */
package tech.eproducts.user_management_service.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.lang.annotation.*;

/**
 * Indicates that the annotated element can be used on method parameters or types.
 */
@Target({ ElementType.PARAMETER, ElementType.TYPE })

/**
 * Specifies that this annotation should be retained at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)

/**
 * Indicates that this annotation should be documented by JavaDoc.
 */
@Documented

/**
 * Delegates to Spring Security's @AuthenticationPrincipal annotation.
 */
@AuthenticationPrincipal
public @interface CurrentUser {
}
