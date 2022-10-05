package br.com.cinemaflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinemaflix.controller.dto.CategoriaDto;
import br.com.cinemaflix.controller.dto.DetalhesDoCategoriaDto;
import br.com.cinemaflix.controller.dto.VideoDto;
import br.com.cinemaflix.controller.form.CategoriaForm;
import br.com.cinemaflix.modelo.Categoria;
import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.CategoriaRepository;
import br.com.cinemaflix.repository.VideoRepository;

@RestController  
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	public List<CategoriaDto> listarVideos(){
	
		List<Categoria> categorias = categoriaRepository.findAll();				
		
		return CategoriaDto.converter(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoCategoriaDto> detalharVideo (@PathVariable Long id) {   
		
		Optional<Categoria> categoria = categoriaRepository.findById(id);	
		
		if  (categoria.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoCategoriaDto(categoria.get()));
		}
		
		return ResponseEntity.notFound().build();

	}	
	
	@GetMapping("/{id}/video")
	public List<VideoDto> detalharVideoPorCategoria (@PathVariable Long id) {   
		
		List<Video> videos = videoRepository.agrupaVideoPorCategoria(id);	
		return VideoDto.converter(videos);
		
	}	
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		
		Categoria categoria = form.converter(categoriaRepository);		
		categoriaRepository.save(categoria);
		URI uri = uriBuilder.path("/video/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
		
	}	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable long id){
		
		Optional<Categoria> optional = categoriaRepository.findById(id);
		
		if  (optional.isPresent()) {
			categoriaRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();		

	}	
	
		
}
