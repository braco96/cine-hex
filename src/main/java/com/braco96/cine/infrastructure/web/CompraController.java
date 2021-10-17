package com.braco96.cine.infrastructure.web;
import com.braco96.cine.application.service.ComprarEntradasService; import com.braco96.cine.domain.model.Entrada; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/compra")
public class CompraController {
  private final ComprarEntradasService svc; public CompraController(ComprarEntradasService s){this.svc=s;}
  public record CompraRequest(Long sesionId, Long clienteId, int cantidad){}
  @PostMapping("/bloque") public List<Entrada> comprar(@RequestBody CompraRequest r){ return svc.comprarBloque(r.sesionId(), r.clienteId(), r.cantidad()); }
}
