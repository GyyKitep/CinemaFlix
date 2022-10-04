package br.com.cinemaflix.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinemaflix.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTitulo(String titulo);


}
