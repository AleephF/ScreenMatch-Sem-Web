package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Series, Long> {
}
