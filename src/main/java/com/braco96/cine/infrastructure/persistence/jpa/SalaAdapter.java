package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Sala; import com.braco96.cine.domain.port.out.SalaRepository; import org.springframework.stereotype.Repository; import java.util.Optional;
@Repository public class SalaAdapter implements SalaRepository {
  private final SpringSalaRepository repo; public SalaAdapter(SpringSalaRepository r){this.repo=r;}
  public Optional<Sala> findById(String id){ return repo.findById(id).map(e->new Sala(e.id,e.filas,e.butacasPorFila)); }
  public Sala save(Sala s){ var e=new JpaSala(); e.id=s.id(); e.filas=s.filas(); e.butacasPorFila=s.butacasPorFila(); e=repo.save(e); return new Sala(e.id,e.filas,e.butacasPorFila); }
}
