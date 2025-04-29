package com.exemple.studentportal.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex,
                                                 RedirectAttributes redirectAttributes) {
        logger.warn("Illegal argument exception: {}", ex.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage",
                "Invalid request: " + ex.getMessage());
        return "redirect:/students";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        logger.error("Unexpected error occurred", ex);

        // Don't expose raw exception messages to users in production
        String userMessage = "An unexpected error occurred. Please try again later.";
        String debugMessage = null;

        // Only show detailed error in development
        if ("development".equals(System.getProperty("spring.profiles.active"))) {
            debugMessage = ex.getMessage();
        }

        model.addAttribute("errorMessage", userMessage);
        model.addAttribute("debugMessage", debugMessage);
        return "error";
    }
}