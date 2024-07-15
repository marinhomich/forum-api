package dev.marinhomich.forum_api.dto;

import java.io.Serializable;
import dev.marinhomich.forum_api.controller.AuthenticationController;

/**
 * DTO for {@link AuthenticationController}
 */
public record JwtTokenDto(String token) implements Serializable {
}
