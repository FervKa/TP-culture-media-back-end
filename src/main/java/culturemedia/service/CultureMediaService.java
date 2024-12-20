package culturemedia.service;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface CultureMediaService {
    List<Video> findAll() throws VideoNotFoundException;
    Video save(Video video);
    View save(View view);
    List<Video> find(Double fromDuration, Double toDuration) throws VideoNotFoundException;
    List<Video> find(String title) throws VideoNotFoundException;
}
