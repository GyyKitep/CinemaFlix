package br.com.cinemaflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.cinemaflix.modelo.Categoria;

public class CategoriaDto {
	
	private Long id;
	private String titulo;
	private String cor;
	
	public CategoriaDto(Categoria categoria) {
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
	public static List<CategoriaDto> converter(List<Categoria> categorias) {
		List<CategoriaDto> listCategoriasDtos = new ArrayList<CategoriaDto>();
		
		categorias.stream()
			.forEach(c -> {
				listCategoriasDtos.add(new CategoriaDto(c));
			});;

		
		return listCategoriasDtos;
	}
	
	

}
