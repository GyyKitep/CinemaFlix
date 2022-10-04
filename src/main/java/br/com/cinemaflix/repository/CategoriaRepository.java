package br.com.cinemaflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinemaflix.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByTitulo(String titulo);

}
