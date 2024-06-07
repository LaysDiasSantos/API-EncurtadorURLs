package com.desafio.encurtadorurl.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class UrlModelTest {
	@Test
	public void testConstrutorPadraoModel() {
		UrlModel urlModel = new UrlModel();

		assertNull(urlModel.getId());
		assertNull(urlModel.getUrlOriginal());
		assertNull(urlModel.getUrlEncurtada());
		assertNull(urlModel.getDataCriacao());
		assertEquals(0, urlModel.getContagemAcessos());
	}

	@Test  
	public void testarConstrutorParametrizadoModeloURL() {
        String urlOriginal = "http://example.com";
        String urlEncurtada = "abcd1234";
        LocalDateTime beforeCreation = LocalDateTime.now();

        UrlModel urlModel = new UrlModel(urlOriginal, urlEncurtada);

        assertNull(urlModel.getId());
        assertEquals(urlOriginal, urlModel.getUrlOriginal());
        assertEquals(urlEncurtada, urlModel.getUrlEncurtada());
        
        LocalDateTime dataCriacao = urlModel.getDataCriacao();
        assertTrue(dataCriacao.isAfter(beforeCreation) || dataCriacao.isEqual(beforeCreation));
        
        assertEquals(0, urlModel.getContagemAcessos());
    }

	@Test
	public void testSettersAndGetters() {
		UrlModel urlModel = new UrlModel();
		LocalDateTime now = LocalDateTime.now();

		urlModel.setId(1L);
		urlModel.setUrlOriginal("http://example.com");
		urlModel.setUrlEncurtada("abcd1234");
		urlModel.setDataCriacao(now);
		urlModel.setContagemAcessos(5);

		assertEquals(1L, urlModel.getId());
		assertEquals("http://example.com", urlModel.getUrlOriginal());
		assertEquals("abcd1234", urlModel.getUrlEncurtada());
		assertEquals(now, urlModel.getDataCriacao());
		assertEquals(5, urlModel.getContagemAcessos());
	}
}
