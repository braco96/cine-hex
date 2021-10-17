package com.braco96.cine.domain.model;
public record Entrada(Long id, Long sesionId, int fila, int columna, Long clienteId, double precio) {}
