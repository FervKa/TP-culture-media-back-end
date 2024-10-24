package culturemedia.service.impl;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.service.CultureMediaService;

import java.util.ArrayList;
import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaService {

    private final List<Video> videos;
    private final List<View> views;

    public CultureMediaServiceImpl() {
        views = new ArrayList<>();
        videos = new ArrayList<>();
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public Video save(Video video) {
        this.videos.add(video);
        return video;
    }

    @Override
    public View save(View view) {
        this.views.add(view);
        return view;
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> filteredVideos = null;
        for (Video video : videos) {
            if (video.title().toLowerCase().contains(title.toLowerCase())) {
                if (filteredVideos == null) {
                    filteredVideos = new ArrayList<Video>();
                }
                filteredVideos.add(video);
            }
        }
        return filteredVideos;
    }

    @Override
    public List<Video> find(Double fromDuration, Double toDuration) throws VideoNotFoundException {
        List<Video> filteredVideos = new ArrayList<Video>();
        for (Video video : videos) {
            if (video.duration() > fromDuration && video.duration() < toDuration) {
                filteredVideos.add(video);
            }
        }
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return filteredVideos;
    }
}
