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
}
