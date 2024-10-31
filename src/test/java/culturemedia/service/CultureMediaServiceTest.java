package culturemedia.service;

import culturemedia.exceptions.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.impl.CultureMediaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CultureMediaServiceTest {


    @Mock
    private VideoRepository videoRepository;

    @Mock
    private ViewsRepository viewsRepository;
    
    private CultureMediaService cultureMediaService;

    @BeforeEach
    void init() throws VideoNotFoundException {
        MockitoAnnotations.initMocks(this);
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
        /*List<Video> videoList = List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1));

        for (Video video : videoList){
            System.out.println(video);
            cultureMediaService.save(video);
        }
        List<Video> filtered = cultureMediaService.find("Clic");
        System.out.println(filtered);*/
    }


    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        mock_find_all_videos(List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)));
        List<Video> videos = cultureMediaService.findAll();
        /*saveVideos();*/
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        String title = "Clic";
        mock_find_videos(List.of(new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1)), title);
        List<Video> videos = cultureMediaService.find(title);
        assertEquals(2, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        VideoNotFoundException vnfex = assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
        assertEquals("No video found!", vnfex.getMessage());
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() throws VideoNotFoundException {
        mock_find_by_duration(4.5, 5.5, List.of(new Video("06", "Clic 6", "----", 5.1)));
        List<Video> videos = cultureMediaService.find(4.5, 5.5);
        assertEquals(1, videos.size());
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_an_VideoNotFoundException() throws VideoNotFoundException {
        mock_find_by_duration(10.2, 24.2, new ArrayList<>());
        VideoNotFoundException vnfex = assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find(10.2, 24.2));
        assertEquals("No video found!", vnfex.getMessage());
    }

    @Test
    void when_save_video_should_be_returned_successfully() throws VideoNotFoundException {
        Video new_video = new Video("07", "Clic 5", "----", 5.7);
        mock_save_video(new_video, new_video);
        Video video = cultureMediaService.save(new_video);
        assertEquals(new_video, video);
    }

    @Test
    void when_save_view_should_be_returned_successfully()  {
        View new_view = new View("FervKa");
        mock_save_view(new_view, new_view);
        View video = viewsRepository.save(new_view);
        assertEquals(new_view, video);
    }

    private void mock_find_all_videos(List<Video> needed_videos) {
        when(videoRepository.findAll()).thenReturn(needed_videos);
    }
    private void mock_find_videos(List<Video> needed_videos, String title) throws VideoNotFoundException {
        when(videoRepository.find(title)).thenReturn(needed_videos);
    }
    private void mock_find_by_duration(Double fromDuration, Double toDuration, List<Video> to_be_returned) throws VideoNotFoundException {
        when(videoRepository.find(fromDuration, toDuration)).thenReturn(to_be_returned);
    }
    private void mock_save_video(Video video, Video saved_video){
        when(videoRepository.save(video)).thenReturn(saved_video);
    }
    private void mock_save_view(View view, View saved_view){
        when(viewsRepository.save(view)).thenReturn(saved_view);
    }
}