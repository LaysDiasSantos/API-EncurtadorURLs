package com.desafio.encurtadorurl.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class UrlModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 1000)
	private String urlOriginal;

	@Column(nullable = false, unique = true)
	private String urlEncurtada;

	@Column(nullable = false)
	private LocalDateTime dataCriacao;

	@Column(nullable = false)
	private int contagemAcessos;

	public UrlModel() {
	}

	public UrlModel(String urlOriginal, String urlEncurtada) {
		this.urlOriginal = urlOriginal;
		this.urlEncurtada = urlEncurtada;
		this.dataCriacao = LocalDateTime.now();
		this.contagemAcessos = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getContagemAcessos() {
		return contagemAcessos;
	}

	public void setContagemAcessos(int contagemAcessos) {
		this.contagemAcessos = contagemAcessos;
	}

}
