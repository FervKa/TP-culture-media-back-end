package culturemedia.controller;

import java.util.*;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CultureMediaController {

	private final CultureMediaService cultureMediaService;


	public CultureMediaController(CultureMediaService cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}

	@GetMapping("/videos")
	public List<Video> findAllVideos() throws VideoNotFoundException {
		try{
			List<Video> videos = cultureMediaService.findAll();
			return videos;
		}catch (VideoNotFoundException videoNotFoundException){
			throw new VideoNotFoundException();
		}
	}

	@PostMapping("/videos")
	public Video add(Video video) {
		cultureMediaService.save(video);
		return video;
	}


}
