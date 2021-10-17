package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Sesion; import java.time.LocalDateTime; import java.util.List;
public interface SesionRepository { Sesion save(Sesion s); boolean existsBySalaAndFecha(String salaId, LocalDateTime fechaHora); List<Sesion> findByPelicula(String tituloPelicula); Sesion findById(Long id); }
