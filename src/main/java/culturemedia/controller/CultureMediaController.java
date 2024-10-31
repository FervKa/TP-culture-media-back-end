package culturemedia.controller;

import java.util.*;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;

public class CultureMediaController {

	private final CultureMediaService cultureMediaService;


	public CultureMediaController(CultureMediaService cultureMediaService) {
		this.cultureMediaService = cultureMediaService;
	}

	public List<Video> findAllVideos() throws VideoNotFoundException {
		try{
			List<Video> videos = cultureMediaService.findAll();
			return videos;
		}catch (VideoNotFoundException videoNotFoundException){
			throw new VideoNotFoundException();
		}
	}

	public Video add(Video video) {
		cultureMediaService.save(video);
		return video;
	}


}
