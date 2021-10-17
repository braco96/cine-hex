package com.braco96.cine.domain.port.in;
import com.braco96.cine.domain.model.Sesion;
import java.time.LocalDateTime;
public interface ProgramarSesionUseCase { Sesion programar(String tituloPelicula, String salaId, LocalDateTime fechaHora, double precioBase); }
