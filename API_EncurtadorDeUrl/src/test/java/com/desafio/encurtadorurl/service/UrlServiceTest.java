package com.desafio.encurtadorurl.service;

import com.desafio.encurtadorurl.model.UrlModel;
import com.desafio.encurtadorurl.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
	@Mock
	private UrlRepository urlRepository;

	@InjectMocks
	private UrlService urlService;

	@Test
	public void testCriarUrl() {
		String urlOriginal = "http://example.com";
		String urlEncurtada = "abcd1234";
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlOriginal(urlOriginal);
		urlModel.setUrlEncurtada(urlEncurtada);
		urlModel.setDataCriacao(LocalDateTime.now());
		urlModel.setContagemAcessos(0);

		when(urlRepository.save(any(UrlModel.class))).thenReturn(urlModel);

		UrlModel result = urlService.criarUrl(urlOriginal, urlEncurtada);

		assertEquals(urlOriginal, result.getUrlOriginal());
		assertEquals(urlEncurtada, result.getUrlEncurtada());
		assertEquals(0, result.getContagemAcessos());
		verify(urlRepository, times(1)).save(any(UrlModel.class));
	}

	@Test
	public void testGetUrlByUrlEncurtada() {
		String urlEncurtada = "abcd1234";
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlEncurtada(urlEncurtada);

		when(urlRepository.findByUrlEncurtada(urlEncurtada)).thenReturn(Optional.of(urlModel));

		Optional<UrlModel> result = urlService.getUrlByUrlEncurtada(urlEncurtada);

		assertTrue(result.isPresent());
		assertEquals(urlEncurtada, result.get().getUrlEncurtada());
		verify(urlRepository, times(1)).findByUrlEncurtada(urlEncurtada);
	}

	@Test
	public void testGetUrlEncurtadaByUrlOriginal() {
		String urlOriginal = "http://example.com";
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlOriginal(urlOriginal);

		when(urlRepository.findByUrlOriginal(urlOriginal)).thenReturn(Optional.of(urlModel));

		Optional<UrlModel> result = urlService.getUrlEncurtadaByUrlOriginal(urlOriginal);

		assertTrue(result.isPresent());
		assertEquals(urlOriginal, result.get().getUrlOriginal());
		verify(urlRepository, times(1)).findByUrlOriginal(urlOriginal);
	}

	@Test
	public void testIncrementarContagemAcessos() {
		UrlModel urlModel = new UrlModel();
		urlModel.setContagemAcessos(5);

		urlService.incrementarContagemAcessos(urlModel);

		assertEquals(6, urlModel.getContagemAcessos());
		verify(urlRepository, times(1)).save(urlModel);
	}

	@Test
	public void testGetAllUrls() {
		UrlModel urlModel1 = new UrlModel();
		UrlModel urlModel2 = new UrlModel();

		when(urlRepository.findAll()).thenReturn(Arrays.asList(urlModel1, urlModel2));

		List<UrlModel> result = urlService.getAllUrls();

		assertEquals(2, result.size());
		verify(urlRepository, times(1)).findAll();
	}
}
