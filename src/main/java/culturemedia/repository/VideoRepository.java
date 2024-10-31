package culturemedia.repository;

import java.util.List;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;

public interface VideoRepository {
    List<Video> findAll();
    Video save(Video video);
    List<Video> find(String title) throws VideoNotFoundException;
    List<Video> find(Double fromDuration, Double toDuration);
}
