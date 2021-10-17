package com.braco96.cine.infrastructure.web;
import com.braco96.cine.application.service.ProgramarSesionService; import com.braco96.cine.domain.model.Sesion; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime;
@RestController @RequestMapping("/api/sesiones")
public class SesionController {
  private final ProgramarSesionService svc; public SesionController(ProgramarSesionService s){this.svc=s;}
  public record ProgramarRequest(String titulo,String salaId,String fechaHora,double precioBase){}
  @PostMapping public Sesion programar(@RequestBody ProgramarRequest r){ return svc.programar(r.titulo(), r.salaId(), LocalDateTime.parse(r.fechaHora()), r.precioBase()); }
}
