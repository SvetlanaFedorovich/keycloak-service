package mvp.project.keycloakservice.model.response;

import lombok.Builder;


@Builder
public record KeycloakTokenResponse(
        String access_token,
        String refresh_token,
        String token_type,
        String session_state,
        Integer expires_in) {
}
