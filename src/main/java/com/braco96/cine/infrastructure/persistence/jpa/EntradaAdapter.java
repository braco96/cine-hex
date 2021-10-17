package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Entrada; import com.braco96.cine.domain.port.out.EntradaRepository; import org.springframework.stereotype.Repository; import java.util.stream.Collectors; import java.util.List;
@Repository public class EntradaAdapter implements EntradaRepository {
  private final SpringEntradaRepository repo; public EntradaAdapter(SpringEntradaRepository r){this.repo=r;}
  public boolean asientoOcupado(Long s,int f,int c){ return repo.existsBySesionIdAndFilaAndColumna(s,f,c); }
  public Entrada save(Entrada e){ var x=new JpaEntrada(); x.id=e.id(); x.sesionId=e.sesionId(); x.fila=e.fila(); x.columna=e.columna(); x.clienteId=e.clienteId(); x.precio=e.precio(); x=repo.save(x); return new Entrada(x.id,x.sesionId,x.fila,x.columna,x.clienteId,x.precio); }
  public List<Entrada> findBySesion(Long s){ return repo.findBySesionId(s).stream().map(x->new Entrada(x.id,x.sesionId,x.fila,x.columna,x.clienteId,x.precio)).collect(Collectors.toList()); }
}
