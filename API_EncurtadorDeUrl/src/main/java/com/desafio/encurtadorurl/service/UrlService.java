package com.desafio.encurtadorurl.service;

import com.desafio.encurtadorurl.model.UrlModel;
import com.desafio.encurtadorurl.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
	@Autowired
	private UrlRepository urlRepository;

	public UrlModel criarUrl(String urlOriginal, String urlEncurtada) {
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlOriginal(urlOriginal);
		urlModel.setUrlEncurtada(urlEncurtada);
		urlModel.setDataCriacao(LocalDateTime.now());
		urlModel.setContagemAcessos(0);
		return urlRepository.save(urlModel);
	}

	public Optional<UrlModel> getUrlByUrlEncurtada(String urlEncurtada) {
		return urlRepository.findByUrlEncurtada(urlEncurtada);
	}

	public Optional<UrlModel> getUrlEncurtadaByUrlOriginal(String urlOriginal) {
		return urlRepository.findByUrlOriginal(urlOriginal);
	}
	
	public void incrementarContagemAcessos(UrlModel url) {
		url.setContagemAcessos(url.getContagemAcessos() + 1);
		urlRepository.save(url);
	}

	public List<UrlModel> getAllUrls() {
		return urlRepository.findAll();
	}
}
