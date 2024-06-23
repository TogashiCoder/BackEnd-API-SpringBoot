package com.togashi.movierestapi.service;

import com.togashi.movierestapi.dto.MovieDTO;
import com.togashi.movierestapi.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.togashi.movierestapi.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream().map(movie -> {
            MovieDTO dto = new MovieDTO();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setGenre(movie.getGenre());
            dto.setDirector(movie.getDirector());
            dto.setRating(movie.getRating());
            return dto;
        }).collect(Collectors.toList());
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setDirector(movieDTO.getDirector());
        movie.setRating(movieDTO.getRating());

        movie = movieRepository.save(movie);

        movieDTO.setId(movie.getId());
        return movieDTO;
    }

    public Optional<MovieDTO> getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(film -> {
            MovieDTO dto = new MovieDTO();
            dto.setId(film.getId());
            dto.setTitle(film.getTitle());
            dto.setReleaseDate(film.getReleaseDate());
            dto.setGenre(film.getGenre());
            dto.setDirector(film.getDirector());
            dto.setRating(film.getRating());
            return dto;
        });
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public Optional<MovieDTO> updateMovie(int id, MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movieToUpdate = optionalMovie.get();
            movieToUpdate.setTitle(movieDTO.getTitle());
            movieToUpdate.setGenre(movieDTO.getGenre());
            movieToUpdate.setReleaseDate(movieDTO.getReleaseDate());
            movieToUpdate.setDirector(movieDTO.getDirector());
            movieToUpdate.setRating(movieDTO.getRating());

            movieRepository.save(movieToUpdate);

            movieDTO.setId(movieToUpdate.getId());
            return Optional.of(movieDTO);
        } else {
            return Optional.empty();
        }
    }
}
