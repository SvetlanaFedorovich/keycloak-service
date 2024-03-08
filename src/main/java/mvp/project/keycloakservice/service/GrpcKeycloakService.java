package mvp.project.keycloakservice.service;

import feign.FeignException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import mvp.project.authservice.KeycloakServiceGrpc;
import mvp.project.authservice.TokenRequest;
import mvp.project.authservice.TokenResponse;
import mvp.project.keycloakservice.exception.TokenInvalidateException;
import mvp.project.keycloakservice.model.response.KeycloakTokenResponse;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.http.HttpStatus;

import static mvp.project.keycloakservice.builder.TokenResponseBuilder.tokenResponseBuild;
import static mvp.project.keycloakservice.builder.UserCredentialsDtoBuilder.userCredentialsDtoBuild;
import static mvp.project.keycloakservice.constant.ErrorClientResponseMessage.INVALID_USER_CREDENTIALS_OR_CLIENT;
import static mvp.project.keycloakservice.constant.ErrorClientResponseMessage.REALM_DOES_NOT_EXIST;
import static mvp.project.keycloakservice.constant.ErrorClientResponseMessage.SERVER_CONNECTION_ERROR;
import static mvp.project.keycloakservice.constant.ErrorClientResponseMessage.UNSUPPORTED_GRANT_TYPE;

@GrpcService
@RequiredArgsConstructor
public class GrpcKeycloakService extends KeycloakServiceGrpc.KeycloakServiceImplBase {

    private final KeycloakService keycloakService;

    private Status getStatusResponseFromFeignClient (FeignException e) {
        if (e.status() == HttpStatus.NOT_FOUND.value()) {
            return Status.NOT_FOUND.withDescription(REALM_DOES_NOT_EXIST);
        }
        if (e.status() == HttpStatus.UNAUTHORIZED.value()) {
            return Status.UNAUTHENTICATED.withDescription(INVALID_USER_CREDENTIALS_OR_CLIENT);
        }
        if (e.status() == HttpStatus.BAD_REQUEST.value()) {
            return Status.UNKNOWN.withDescription(UNSUPPORTED_GRANT_TYPE);
        }
        return Status.INTERNAL.withDescription(SERVER_CONNECTION_ERROR);
    }

    @Override
    public void getToken(TokenRequest request, StreamObserver<TokenResponse> responseObserver) {
        try {
            KeycloakTokenResponse token = keycloakService
                    .getToken(userCredentialsDtoBuild(request)).orElseThrow(TokenInvalidateException::new);
            responseObserver.onNext(tokenResponseBuild(token));
            responseObserver.onCompleted();
        } catch (FeignException e) {
            responseObserver.onError(getStatusResponseFromFeignClient(e).asRuntimeException());
        } catch (TokenInvalidateException e){
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException());
        }
    }
}
