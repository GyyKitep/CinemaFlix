package br.com.cinemaflix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cinemaflix.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ 
	
	Optional<Usuario> findByNome(String nome);

}
