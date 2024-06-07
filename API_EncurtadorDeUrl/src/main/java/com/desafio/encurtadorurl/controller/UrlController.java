package com.desafio.encurtadorurl.controller;

import com.desafio.encurtadorurl.model.UrlModel;
import com.desafio.encurtadorurl.service.UrlService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
@Tag(name = "API_EncurtadorDeUrl")
public class UrlController {

	@Autowired
	private UrlService urlService;

	@Operation(summary = "Cadastrar uma nova URL", method = "POST")
	@PostMapping
	public Map<String, String> criarUrl(@RequestParam String urlOriginal) {
		String urlEncurtada;
		UrlModel url;

		Optional<UrlModel> consultaUrl = urlService.getUrlEncurtadaByUrlOriginal(urlOriginal);

		if (!consultaUrl.isEmpty()) {
			// construir url encurtada pra devolver
			urlEncurtada = consultaUrl.get().getUrlEncurtada();
			url = consultaUrl.get();
		} else {
			urlEncurtada = UUID.randomUUID().toString().substring(0, 8);
			url = urlService.criarUrl(urlOriginal, urlEncurtada);
		}

		Map<String, String> response = new HashMap<>();
		if (url != null) {
			//String urlEncurtadaFormatada = "http://localhost:8080/api/" + urlEncurtada;
			response.put("urlOriginal", url.getUrlOriginal());
			response.put("urlEncurtada", urlEncurtada);

		} else {
			response.put("error", "Falha ao encurtar a URL!");
		}
		return response;
	}

	@Operation(summary = "Redirecionar para a URL original", method = "GET")
	@GetMapping("/{urlEncurtada}")
	public void redirecionarUrl(@PathVariable String urlEncurtada, HttpServletResponse response) throws IOException {
		Optional<UrlModel> urlOptional = urlService.getUrlByUrlEncurtada(urlEncurtada);
		if (urlOptional.isPresent()) {
			UrlModel url = urlOptional.get();
			urlService.incrementarContagemAcessos(url);
			response.sendRedirect(url.getUrlOriginal());
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL não encontrada");
		}
	}

	@Operation(summary = "Obter estatisticas de todas as URLs", method = "GET")
	@GetMapping("/statsTodaslUrls")
	public List<UrlModel> getStatsTodasUrls() {
		return urlService.getAllUrls();
	}

	@Operation(summary = "Obter estatisticas de uma URL especifica", method = "GET")
	@GetMapping("/stats/{urlEncurtada}")
	public Map<String, Object> getStatsUrl(@PathVariable String urlEncurtada) {
		Optional<UrlModel> urlOptional = urlService.getUrlByUrlEncurtada(urlEncurtada);
		if (urlOptional.isPresent()) {
			UrlModel url = urlOptional.get();

			int totalAcessos = url.getContagemAcessos();
			long totalDias = Duration.between(url.getDataCriacao(), LocalDateTime.now()).toDays();
			double mediaAcessos = totalDias > 0 ? (double) totalAcessos / totalDias : totalAcessos;

			Map<String, Object> stats = new HashMap<>();
			stats.put("urlOriginal", url.getUrlOriginal());
			stats.put("urlEncurtada", url.getUrlEncurtada());
			stats.put("totalGeralAcessos", totalAcessos);
			stats.put("acessosPorDia", mediaAcessos);

			return stats;
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("error", "URL não encontrada.");
			return response;
		}
	}

}
