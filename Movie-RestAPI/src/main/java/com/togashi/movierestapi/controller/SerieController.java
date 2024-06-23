package com.togashi.movierestapi.controller;

import com.togashi.movierestapi.dto.SerieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.togashi.movierestapi.service.SerieService;

import java.util.List;
import java.util.Optional;


    @RestController
    @RequestMapping("/series")
    public class SerieController {

        @Autowired
        private SerieService serieService;

        @GetMapping
        public List<SerieDTO> getAllSeries() {
            return serieService.getAllSeries();
        }

        @GetMapping("/{id}")
        public ResponseEntity<SerieDTO> getSerieById(@PathVariable int id) {
            Optional<SerieDTO> serie = serieService.getSerieById(id);
            return serie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public SerieDTO createSerie(@RequestBody SerieDTO serieDTO) {
            return serieService.addSerie(serieDTO);
        }

        @PutMapping("/{id}")
        public ResponseEntity<SerieDTO> updateSerie(@PathVariable int id, @RequestBody SerieDTO serieDTO) {
            Optional<SerieDTO> updatedSerie = serieService.updateSerie(id, serieDTO);
            return updatedSerie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteSerie(@PathVariable int id) {
            serieService.deleteSerie(id);
            return ResponseEntity.noContent().build();
        }


}
