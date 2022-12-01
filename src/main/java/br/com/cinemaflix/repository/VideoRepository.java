package br.com.cinemaflix.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cinemaflix.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
	
	Page<Video> findByTitulo(String titulo, Pageable paginacao);
	
	@Query("SELECT v FROM Video v WHERE v.titulo LIKE :titulo")
	List<Video> carregarPorDoParteTitulo(@Param("titulo") String titulo);
	

	@Query("SELECT v FROM Video v WHERE v.categoria.id = :id")
	List<Video> agrupaVideoPorCategoria(Long id);	



}
