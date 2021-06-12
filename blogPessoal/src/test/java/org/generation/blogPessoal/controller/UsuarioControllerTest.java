package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	private Usuario usuario;
	private Usuario usuarioAlterar;
	

	@BeforeAll
	public void start() {
		usuario = new Usuario("Alex Santos", "Aleale", "1212");
		usuarioAlterar = new Usuario("Alexandre Sousa", "Chao", "654321");
	}

	@Disabled
	@Test
	void deveSalvarUsuarioRetornaStatus201() {

		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuario);

		ResponseEntity<Usuario> resposta = testRestTemplate
				.exchange("/usuario", HttpMethod.POST, request,Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}


	@Test
	void deveRetornarListadeUsuarioRetornaStatus200() {
		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("Aleale", "1212")
				.exchange("/usuario", HttpMethod.GET, null, String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	@Disabled
	@Test
	void deveAtualizarSenhaUsuarioRetornaStatus201() {

		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioAlterar);

		ResponseEntity<Usuario> resposta = testRestTemplate.withBasicAuth("RafaelBoaz", "134652")
				.exchange("/usuario", HttpMethod.PUT, request, Usuario.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}

}