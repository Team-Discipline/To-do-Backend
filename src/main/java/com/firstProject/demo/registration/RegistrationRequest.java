//OK
package com.firstProject.demo.registration;

public record RegistrationRequest(
        String id,
        String password,
        String role
) {
}