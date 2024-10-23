package culturemedia.service;

import culturemedia.model.Video;
import culturemedia.model.View;

import java.util.List;

public interface CultureMediaService {
    List<Video> listAllVideos();
    Video addV(Video video);
    Video add(View view);
}
