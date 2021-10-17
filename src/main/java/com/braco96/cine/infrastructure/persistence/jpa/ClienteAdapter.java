package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Cliente; import com.braco96.cine.domain.port.out.ClienteRepository; import org.springframework.stereotype.Repository;
@Repository public class ClienteAdapter implements ClienteRepository {
  private final SpringClienteRepository repo; public ClienteAdapter(SpringClienteRepository r){this.repo=r;}
  public Cliente save(Cliente c){ var e=new JpaCliente(); e.id=c.id(); e.nombre=c.nombre(); e.email=c.email(); e=repo.save(e); return new Cliente(e.id,e.nombre,e.email); }
  public Cliente findById(Long id){ var e=repo.findById(id).orElseThrow(); return new Cliente(e.id,e.nombre,e.email); }
}
