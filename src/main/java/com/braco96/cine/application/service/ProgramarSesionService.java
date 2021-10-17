package com.braco96.cine.application.service;
import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.in.*; import com.braco96.cine.domain.port.out.*; import org.springframework.stereotype.Service; import java.time.LocalDateTime;
@Service public class ProgramarSesionService implements ProgramarSesionUseCase {
  private final PeliculaRepository peliculaRepo; private final SalaRepository salaRepo; private final SesionRepository sesionRepo;
  public ProgramarSesionService(PeliculaRepository p,SalaRepository s,SesionRepository r){this.peliculaRepo=p;this.salaRepo=s;this.sesionRepo=r;}
  public Sesion programar(String titulo,String salaId,LocalDateTime fecha,double precio){ var peli=peliculaRepo.findByTitulo(titulo).orElseThrow(); salaRepo.findById(salaId).orElseThrow(); if(sesionRepo.existsBySalaAndFecha(salaId,fecha)) throw new IllegalStateException("Solape"); return sesionRepo.save(new Sesion(null,peli.id(),salaId,fecha,precio)); }
}
