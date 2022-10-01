package br.com.cinemaflix.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.repository.CategoriaRepository;

public class CategoriaForm {
	
	@NotEmpty @Length(min = 10)
	private String titulo;
	@NotEmpty @Length(min = 4)
	private String cor;
	
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
	public Categoria converter(CategoriaRepository categoriaRepository) {
		return new Categoria(this.titulo, this.cor);
	}
}
