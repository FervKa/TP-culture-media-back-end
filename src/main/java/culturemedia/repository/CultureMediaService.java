package culturemedia.repository;

import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface CultureMediaService {
    List<Video> listAllVideos();
    Video add(Video video);
    Video add(View view);
}
