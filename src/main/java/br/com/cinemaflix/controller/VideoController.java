package br.com.cinemaflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cinemaflix.controller.dto.DetalhesDoVideoDto;
import br.com.cinemaflix.controller.dto.VideoDto;
import br.com.cinemaflix.controller.form.VideoForm;
import br.com.cinemaflix.modelo.Video;
import br.com.cinemaflix.repository.VideoRepository;

@RestController  
@RequestMapping("/video")
public class VideoController {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@GetMapping
	public List<VideoDto> listarVideos(){
	
		List<Video> videos = videoRepository.findAll();				
		
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
		
		if (!form.getUrl().startsWith("https://")) {
			return ResponseEntity.badRequest().build();
		}
		
		Video video = form.converter(videoRepository);		
		videoRepository.save(video);
		URI uri = uriBuilder.path("/video/{id}").buildAndExpand(video.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new VideoDto(video));
		
	}	
	
	

}
