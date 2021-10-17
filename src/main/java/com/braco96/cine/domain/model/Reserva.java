package com.braco96.cine.domain.model;
import java.util.List;
public record Reserva(Long sesionId, Long clienteId, List<Entrada> entradas) {}
