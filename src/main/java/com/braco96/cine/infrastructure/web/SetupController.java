package com.braco96.cine.infrastructure.web;
import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.out.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/setup")
public class SetupController {
  private final PeliculaRepository pr; private final SalaRepository sr; private final ClienteRepository clr; private final CuentaRepository cur;
  public SetupController(PeliculaRepository pr,SalaRepository sr,ClienteRepository clr,CuentaRepository cur){this.pr=pr;this.sr=sr;this.clr=clr;this.cur=cur;}
  @PostMapping("/pelicula") public Pelicula pelicula(@RequestBody Pelicula p){ return pr.save(p); }
  @PostMapping("/sala") public Sala sala(@RequestBody Sala s){ return sr.save(s); }
  @PostMapping("/cliente") public Cliente cliente(@RequestBody Cliente c){ var x=clr.save(c); cur.save(new Cuenta(null,x.id(),100.0)); return x; }
}
