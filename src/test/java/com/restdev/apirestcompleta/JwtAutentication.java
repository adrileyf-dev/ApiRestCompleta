package com.restdev.apirestcompleta;

import com.restdev.apirestcompleta.jwt.JwtToken;
import com.restdev.apirestcompleta.web.dto.UsuarioLoginDto;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.function.Consumer;

public class JwtAutentication {
    public  static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String usename, String password){
        String token = client
                .post()
                .uri("/api/v1/auth")
                .bodyValue( new UsuarioLoginDto(usename,password))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody().getToken() ;
          return headers -> headers.add(HttpHeaders.AUTHORIZATION,"Bearer "+ token);


    }
}
