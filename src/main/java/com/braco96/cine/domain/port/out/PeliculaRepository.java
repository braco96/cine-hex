package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Pelicula; import java.util.Optional;
public interface PeliculaRepository { Optional<Pelicula> findByTitulo(String titulo); Pelicula save(Pelicula p); }
