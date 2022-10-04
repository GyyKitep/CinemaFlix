package br.com.cinemaflix.controller.dto;

import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.modelo.Video;

public class DetalhesDoVideoDto {


	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Categoria categoria;
	
	public DetalhesDoVideoDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoria = video.getCategoria();
	}
	

	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
}
