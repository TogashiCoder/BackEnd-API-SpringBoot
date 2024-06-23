package com.togashi.movierestapi.service;

import com.togashi.movierestapi.dto.SerieDTO;
import com.togashi.movierestapi.model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.togashi.movierestapi.repository.SerieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> getAllSeries() {
        return serieRepository.findAll().stream().map(serie -> {
            SerieDTO dto = new SerieDTO();
            dto.setId(serie.getId());
            dto.setTitle(serie.getTitle());
            dto.setStartDate(serie.getStartDate());
            dto.setEndDate(serie.getEndDate());
            dto.setGenre(serie.getGenre());
            dto.setCreator(serie.getCreator());
            dto.setRating(serie.getRating());
            return dto;
        }).collect(Collectors.toList());
    }

    public SerieDTO addSerie(SerieDTO serieDTO) {
        Serie serie = new Serie();
        serie.setTitle(serieDTO.getTitle());
        serie.setStartDate(serieDTO.getStartDate());
        serie.setEndDate(serieDTO.getEndDate());
        serie.setGenre(serieDTO.getGenre());
        serie.setCreator(serieDTO.getCreator());
        serie.setRating(serieDTO.getRating());

        serie = serieRepository.save(serie);

        serieDTO.setId(serie.getId());
        return serieDTO;
    }

    public Optional<SerieDTO> getSerieById(int id) {
        Optional<Serie> serie = serieRepository.findById(id);
        return serie.map(s -> {
            SerieDTO dto = new SerieDTO();
            dto.setId(s.getId());
            dto.setTitle(s.getTitle());
            dto.setStartDate(s.getStartDate());
            dto.setEndDate(s.getEndDate());
            dto.setGenre(s.getGenre());
            dto.setCreator(s.getCreator());
            dto.setRating(s.getRating());
            return dto;
        });
    }

    public void deleteSerie(int id) {
        serieRepository.deleteById(id);
    }

    public Optional<SerieDTO> updateSerie(int id, SerieDTO serieDTO) {
        Optional<Serie> optionalSerie = serieRepository.findById(id);
        if (optionalSerie.isPresent()) {
            Serie serieToUpdate = optionalSerie.get();
            serieToUpdate.setTitle(serieDTO.getTitle());
            serieToUpdate.setStartDate(serieDTO.getStartDate());
            serieToUpdate.setEndDate(serieDTO.getEndDate());
            serieToUpdate.setGenre(serieDTO.getGenre());
            serieToUpdate.setCreator(serieDTO.getCreator());
            serieToUpdate.setRating(serieDTO.getRating());

            serieRepository.save(serieToUpdate);

            serieDTO.setId(serieToUpdate.getId());
            return Optional.of(serieDTO);
        } else {
            return Optional.empty();
        }
    }
}
