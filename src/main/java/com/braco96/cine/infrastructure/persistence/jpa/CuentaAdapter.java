package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Cuenta; import com.braco96.cine.domain.port.out.CuentaRepository; import org.springframework.stereotype.Repository;
@Repository public class CuentaAdapter implements CuentaRepository {
  private final SpringCuentaRepository repo; public CuentaAdapter(SpringCuentaRepository r){this.repo=r;}
  public Cuenta findByClienteId(Long cid){ var e=repo.findByClienteId(cid); if(e==null) throw new IllegalStateException("Cuenta no encontrada"); return new Cuenta(e.id,e.clienteId,e.saldo); }
  public Cuenta save(Cuenta c){ var e=new JpaCuenta(); e.id=c.id(); e.clienteId=c.clienteId(); e.saldo=c.saldo(); e=repo.save(e); return new Cuenta(e.id,e.clienteId,e.saldo); }
}
