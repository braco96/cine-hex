package com.braco96.cine;
import com.braco96.cine.application.service.ComprarEntradasService; import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.out.*; import org.junit.jupiter.api.Test; import java.time.LocalDateTime; import java.util.*; import static org.junit.jupiter.api.Assertions.*;
class SesRepoF implements SesionRepository{ Sesion s=new Sesion(1L,1L,"S1",LocalDateTime.now(),12.0); public Sesion save(Sesion x){return x;} public boolean existsBySalaAndFecha(String a,LocalDateTime b){return false;} public java.util.List<Sesion> findByPelicula(String t){return List.of();} public Sesion findById(Long id){return s;}}
class EntRepoF implements EntradaRepository{ Set<String> oc=new HashSet<>(); java.util.List<Entrada> all=new ArrayList<>(); public boolean asientoOcupado(Long sid,int f,int c){return oc.contains(f+"-"+c);} public Entrada save(Entrada e){oc.add(e.fila()+"-"+e.columna()); all.add(e); return e;} public java.util.List<Entrada> findBySesion(Long s){return all;}}
class CtaRepoF implements CuentaRepository{ Cuenta c=new Cuenta(1L,99L,200.0); public Cuenta findByClienteId(Long id){return c;} public Cuenta save(Cuenta x){c=x; return x;}}
public class ComprarEntradasServiceUnitTest {
  @Test void dto_y_cobro(){ var svc=new ComprarEntradasService(new SesRepoF(),new EntRepoF(),new CtaRepoF()); var es=svc.comprarBloque(1L,99L,4); assertEquals(4,es.size()); assertTrue(es.stream().allMatch(e->e.precio()==10.8)); }
}
