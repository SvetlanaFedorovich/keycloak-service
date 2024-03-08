package mvp.project.keycloakservice.service;

import feign.FeignException;
import mvp.project.keycloakservice.KeycloakServiceApplication;
import mvp.project.keycloakservice.KeycloakTestContainer;
import mvp.project.keycloakservice.model.dto.UserCredentialsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static mvp.project.keycloakservice.data.TestData.userDataTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = KeycloakServiceApplication.class)
@ExtendWith(KeycloakTestContainer.class)
class KeycloakServiceTest {

    @Autowired
    private KeycloakService keycloakService;

    @BeforeEach
    public void setUp() {
        userDataTest = new UserCredentialsDto();
        userDataTest.setUsername("user");
        userDataTest.setPassword("password");
        userDataTest.setClient_id("crm-base-service");
        userDataTest.setGrant_type("password");
    }

    @Test
    void whenGetTokenInvokedWithValidUserData_thenTokenIsReturned() {
        assertFalse(keycloakService.getToken(userDataTest).isEmpty());
        userDataTest.setUsername("invalidUsername");
        FeignException exception = assertThrows(FeignException.class, ()->
                keycloakService.getToken(userDataTest));
        assertThat(exception.status()).isEqualTo(401);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void whenGetTokenInvokedWithInvalidUsernameOrPasswordOrClientId_thenReturnedIsStatusCode(String input) {
        userDataTest.setUsername(input);
        userDataTest.setPassword(input);
        userDataTest.setClient_id(input);
        FeignException exception = assertThrows(FeignException.class, ()->
                keycloakService.getToken(userDataTest));
        assertThat(exception.status()).isEqualTo(401);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void whenGetTokenInvokedWithInvalidGrantType_thenReturnedIsStatusCode(String input) {
        userDataTest.setGrant_type(input);
        FeignException exception = assertThrows(FeignException.class, ()->
                keycloakService.getToken(userDataTest));
        assertThat(exception.status()).isEqualTo(400);
    }
}