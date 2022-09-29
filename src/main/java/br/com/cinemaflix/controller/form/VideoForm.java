package br.com.cinemaflix.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.VideoRepository;

public class VideoForm {

	@NotEmpty @Length(min = 30)
	private String titulo;
	@NotEmpty @Length(min = 30)
	private String descricao;
	@NotEmpty @Length(min = 40)
	private String url;
	
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
	public Video converter(VideoRepository videoRepository) {	
		return new Video(this.titulo, this.descricao, this.url);
	}	
		
	
}
