package com.braco96.cine.infrastructure.persistence.jpa; import jakarta.persistence.*;
@Entity @Table(name="peliculas") public class JpaPelicula { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id; @Column(unique=true,nullable=false) public String titulo; @Column(name="duracion_min") public int duracionMin; public String clasificacion; }
