package com.desafio.encurtadorurl.testsController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.desafio.encurtadorurl.controller.UrlController;
import com.desafio.encurtadorurl.model.UrlModel;
import com.desafio.encurtadorurl.service.UrlService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {
	@Mock
	private UrlService urlService;

	@InjectMocks
	private UrlController urlController;

	@Test
	public void testCriarUrlQuandoUrlJaExiste() {
		String urlOriginal = "http://example.com";
		String urlEncurtada = "abcd1234";
		UrlModel urlModel = new UrlModel(urlOriginal, urlEncurtada);

		when(urlService.getUrlEncurtadaByUrlOriginal(urlOriginal)).thenReturn(Optional.of(urlModel));

		Map<String, String> expectedResponse = new HashMap<>();
		expectedResponse.put("urlOriginal", urlOriginal);
		expectedResponse.put("urlEncurtada", "http://localhost:8080/api/" + urlEncurtada);

		Map<String, String> actualResponse = urlController.criarUrl(urlOriginal);

		assertEquals(expectedResponse, actualResponse);
		verify(urlService, times(1)).getUrlEncurtadaByUrlOriginal(urlOriginal);
		verify(urlService, never()).criarUrl(anyString(), anyString());
	}

	@Test
	public void testCriarUrlComErro() {
		String urlOriginal = "http://example.com";

		when(urlService.getUrlEncurtadaByUrlOriginal(urlOriginal)).thenReturn(Optional.empty());
		when(urlService.criarUrl(anyString(), anyString())).thenReturn(null);

		Map<String, String> expectedResponse = new HashMap<>();
		expectedResponse.put("error", "Falha ao encurtar a URL!");

		Map<String, String> actualResponse = urlController.criarUrl(urlOriginal);

		assertEquals(expectedResponse, actualResponse);
		verify(urlService, times(1)).getUrlEncurtadaByUrlOriginal(urlOriginal);
		verify(urlService, times(1)).criarUrl(anyString(), anyString());
	}
}
