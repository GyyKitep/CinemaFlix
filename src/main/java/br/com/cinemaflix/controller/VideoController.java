package br.com.cinemaflix.controller;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinemaflix.controller.dto.DetalhesDoVideoDto;
import br.com.cinemaflix.controller.dto.VideoDto;
import br.com.cinemaflix.controller.form.AtualizacaoVideoForm;
import br.com.cinemaflix.controller.form.VideoForm;
import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.CategoriaRepository;
import br.com.cinemaflix.repository.VideoRepository;

@RestController  
@RequestMapping("/video")
public class VideoController {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<VideoDto> listarVideos(@RequestParam(required = false, name = "search") String search){
		
		List<Video> videos = new ArrayList<>();
		if(search == null) {
			videos = videoRepository.findAll();				
		}else {
			String titulo = "%" + search + "%";
			videos = videoRepository.carregarPorDoParteTitulo(titulo);
		}
		return VideoDto.converter(videos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesDoVideoDto> detalharVideo (@PathVariable Long id) {   
		
		Optional<Video> video = videoRepository.findById(id);	
		
		if  (video.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoVideoDto(video.get()));
		}
		
		return ResponseEntity.notFound().build();

	}	
	
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> cadastrar(@RequestBody @Valid VideoForm form, UriComponentsBuilder uriBuilder) {
		Video video = form.converter(categoriaRepository);
		
		if (!video.getUrl().startsWith("https://")) {
			return ResponseEntity.badRequest().build();
		}			
		
		videoRepository.save(video);
		URI uri = uriBuilder.path("/video/{id}").buildAndExpand(video.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VideoDto(video));
		
	}	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable long id){
		
		Optional<Video> optional = videoRepository.findById(id);
		
		if  (optional.isPresent()) {
			videoRepository.deleteById(id);
			
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();		

	}	
	
	@PutMapping("/{id}") 
	public ResponseEntity<VideoDto> atualizar(@PathVariable long id, @RequestBody @Valid AtualizacaoVideoForm form){
		
		Optional<Video> optional = videoRepository.findById(id);
		
		if  (optional.isPresent()) {
			Video video = form.atualizar(id, videoRepository, categoriaRepository); 
			
			return ResponseEntity.ok(new VideoDto(video));
		}
		
		return ResponseEntity.notFound().build();
				
		

	}	
	
	

}
