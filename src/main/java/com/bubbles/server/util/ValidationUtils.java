package com.bubbles.server.util;

import com.bubbles.server.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Common methods used for validation.
 *
 * @author Mingshan Lei
 * @since 2015/7/3
 */
public class ValidationUtils {

    private static final Logger logger = LoggerFactory.getLogger(ValidationUtils.class);

    /**
     * Convert usual validate constraint violation to spring validation errors.
     *
     * @param violationSet validated violation to be converted
     * @return spring validation errors that could be used in exception
     */
    public static Errors convertToErrors(Set<ConstraintViolation<User>> violationSet, Object target, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(target, objectName);
        for (ConstraintViolation violation : violationSet) {
            FieldError fieldError = new FieldError(objectName, violation.getPropertyPath().toString(), violation.getMessage());
            bindingResult.addError(fieldError);
        }
        return bindingResult;
    }
}
