package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Sesion; import com.braco96.cine.domain.port.out.SesionRepository; import org.springframework.stereotype.Repository; import java.time.LocalDateTime; import java.util.List;
@Repository public class SesionAdapter implements SesionRepository {
  private final SpringSesionRepository repo; public SesionAdapter(SpringSesionRepository r){this.repo=r;}
  public Sesion save(Sesion s){ var e=new JpaSesion(); e.id=s.id(); e.peliculaId=s.peliculaId(); e.salaId=s.salaId(); e.fechaHora=s.fechaHora(); e.precioBase=s.precioBase(); e=repo.save(e); return new Sesion(e.id,e.peliculaId,e.salaId,e.fechaHora,e.precioBase); }
  public boolean existsBySalaAndFecha(String salaId, LocalDateTime fh){ return repo.existsBySalaIdAndFechaHora(salaId,fh); }
  public List<Sesion> findByPelicula(String t){ return List.of(); }
  public Sesion findById(Long id){ var e=repo.findById(id).orElseThrow(); return new Sesion(e.id,e.peliculaId,e.salaId,e.fechaHora,e.precioBase); }
}
