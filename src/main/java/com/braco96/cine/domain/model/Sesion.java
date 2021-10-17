package com.braco96.cine.domain.model;
import java.time.LocalDateTime;
public record Sesion(Long id, Long peliculaId, String salaId, LocalDateTime fechaHora, double precioBase) {}
