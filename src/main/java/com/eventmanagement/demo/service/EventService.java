package com.project.movie_reservation_system.service;

import com.project.movie_reservation_system.dto.MovieRequestDto;
import com.project.movie_reservation_system.entity.Events;
import com.project.movie_reservation_system.enums.EventType;
import com.project.movie_reservation_system.exception.MovieNotFoundException;
import com.project.movie_reservation_system.repository.EventRepository;
//import com.project.movie_reservation_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.project.movie_reservation_system.constant.ExceptionMessages.MOVIE_NOT_FOUND;

@Service
public class EventService {

    private final EventRepository movieRepository;

    @Autowired
    public EventService(EventRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Page<Events> getAllMovies(int page, int pageSize) {
        return movieRepository.findAll(PageRequest.of(page,pageSize));
    }

    public Events getMovieById(long id){
       return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public Events createNewMovie(MovieRequestDto movieRequestDto) {

        Events events= Events.builder()
                .eventLanguage(movieRequestDto.getEventLanguage())
                .eventLength(movieRequestDto.getEventLength())
                .eventype(EventType.valueOf(String.valueOf(movieRequestDto.getEventype())))
                .eventName(movieRequestDto.getEventName())
                .build();
        events.setReleaseDate(movieRequestDto.getReleaseDate());

        return movieRepository.save(events);
    }

    public Events updateMovieById(long movieId, MovieRequestDto movieRequestDto) {
        return movieRepository.findById(movieId)
                .map(movieInDb -> {
                    movieInDb.setEventName(movieRequestDto.getEventName());
                    movieInDb.setEventype(EventType.valueOf(String.valueOf(movieRequestDto.getEventype())));
                    movieInDb.setEventLanguage(movieRequestDto.getEventLanguage());
                    movieInDb.setReleaseDate(movieRequestDto.getReleaseDate());
                    movieInDb.setEventLength(movieRequestDto.getEventLength());

                    return movieRepository.save(movieInDb);
                })
                .orElseThrow(() -> new MovieNotFoundException(MOVIE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    public List<Events> getEventsByType(EventType eventType){
        return movieRepository.findByEventype(eventType);
    }

    public void deleteMovieById(long movieId) {
        movieRepository.deleteById(movieId);
    }

}
