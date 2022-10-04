package br.com.cinemaflix.controller.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.modelo.Video;

public class VideoDto {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	private Categoria categoria;
	
	public VideoDto(Video video) {
		this.id = video.getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.categoria = video.getCategoria();
	}
	
	public Long getId() {
		return id;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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

	public void setId(Long id) {
		this.id = id;
	}

	public static List<VideoDto> converter(List<Video> videos) {
		
		List<VideoDto> listVideosDtos = new ArrayList<VideoDto>();
		
		videos.stream()
			.forEach(v -> {
				listVideosDtos.add(new VideoDto(v));
			});;

		
		return listVideosDtos;
	
	}
}
