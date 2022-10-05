package br.com.cinemaflix.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.CategoriaRepository;
import br.com.cinemaflix.repository.VideoRepository;

public class AtualizacaoVideoForm {

	@NotEmpty @Length(min = 30)
	private String titulo;
	@NotEmpty @Length(min = 30)
	private String descricao;
	@NotEmpty @Length(min = 40)
	private String url;
	
	private Long categoria;

	public Long getCategoria() {
		return this.categoria;
	}
	public void setCategoria(Long categoria) {
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
	
	public Video atualizar(long id, VideoRepository videoRepository,CategoriaRepository categoriaRepository) {
		if(this.categoria == null) {
			this.categoria = (long) 1;
		}
		
		Optional<Categoria> categoria = categoriaRepository.findById(this.categoria);	
		
		if  (!categoria.isPresent()) {
			return null;
		}			
		
		Video video = videoRepository.getReferenceById(id);
		video.setTitulo(this.titulo);
		video.setDescricao(this.descricao);
		video.setUrl(this.url);
		return video;
	}	
	
}
