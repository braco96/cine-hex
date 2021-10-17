package com.braco96.cine.application.service;
import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.in.*; import com.braco96.cine.domain.port.out.*; import org.springframework.stereotype.Service; import java.util.*;
@Service public class ComprarEntradasService implements ComprarEntradasUseCase {
  private final SesionRepository sesionRepo; private final EntradaRepository entradaRepo; private final CuentaRepository cuentaRepo;
  public ComprarEntradasService(SesionRepository s,EntradaRepository e,CuentaRepository c){this.sesionRepo=s;this.entradaRepo=e;this.cuentaRepo=c;}
  public List<Entrada> comprarBloque(Long sesionId, Long clienteId, int cantidad){
    var sesion = sesionRepo.findById(sesionId); double p = (cantidad>=4)? sesion.precioBase()*0.9 : sesion.precioBase();
    var cuenta = cuentaRepo.findByClienteId(clienteId); double total=p*cantidad; if(cuenta.saldo()<total) throw new IllegalStateException("Saldo");
    List<int[]> bloque=new ArrayList<>(); outer: for(int f=1;f<=10;f++){int libres=0,ini=1;for(int c=1;c<=10;c++){ if(!entradaRepo.asientoOcupado(sesionId,f,c)){ if(libres==0)ini=c; libres++; if(libres==cantidad){ for(int k=0;k<cantidad;k++) bloque.add(new int[]{f,ini+k}); break outer; } } else { libres=0; ini=c+1; } } }
    if(bloque.isEmpty()) throw new IllegalStateException("Sin bloque");
    cuentaRepo.save(new Cuenta(cuenta.id(), cuenta.clienteId(), cuenta.saldo()-total));
    List<Entrada> out=new ArrayList<>(); for(int[] a:bloque) out.add(entradaRepo.save(new Entrada(null,sesionId,a[0],a[1],clienteId,p)));
    return out;
  }
}
