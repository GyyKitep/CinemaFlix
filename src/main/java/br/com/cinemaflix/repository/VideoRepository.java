package br.com.cinemaflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinemaflix.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{


}
