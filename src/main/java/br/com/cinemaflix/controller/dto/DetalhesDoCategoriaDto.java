package br.com.cinemaflix.controller.dto;

import br.com.cinemaflix.modelo.Categoria;

public class DetalhesDoCategoriaDto {
	
	private Long id;
	private String titulo;
	private String cor;
	
	public DetalhesDoCategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.titulo = categoria.getTitulo();
		this.cor = categoria.getCor();
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
