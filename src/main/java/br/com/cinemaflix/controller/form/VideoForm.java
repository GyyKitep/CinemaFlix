package br.com.cinemaflix.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.CategoriaRepository;

public class VideoForm {

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
	
	public Video converter(CategoriaRepository categoriaRepository) {	
		if(this.categoria == null) {
			this.categoria = (long) 1;
		}		
		
		Optional<Categoria> categoria = categoriaRepository.findById(this.categoria);	
		
		if  (!categoria.isPresent()) {
			return null;
		}					
		
		return new Video(this.titulo, this.descricao, this.url, categoria.get());
	}	
		
	
}
