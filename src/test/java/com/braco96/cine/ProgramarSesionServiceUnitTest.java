package com.braco96.cine;
import com.braco96.cine.application.service.ProgramarSesionService; import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.out.*; import org.junit.jupiter.api.Test; import java.time.LocalDateTime; import java.util.*; import static org.junit.jupiter.api.Assertions.*;
class PeliFake implements PeliculaRepository{ Map<String,Pelicula> m=new HashMap<>(); public Optional<Pelicula> findByTitulo(String t){return Optional.ofNullable(m.get(t));} public Pelicula save(Pelicula p){m.put(p.titulo(),p);return p;}}
class SalaFake implements SalaRepository{ Map<String,Sala> m=new HashMap<>(); public Optional<Sala> findById(String id){return Optional.ofNullable(m.get(id));} public Sala save(Sala s){m.put(s.id(),s);return s;}}
class SesionFake implements SesionRepository{ List<Sesion> l=new ArrayList<>(); public Sesion save(Sesion s){var x=new Sesion((long)(l.size()+1),s.peliculaId(),s.salaId(),s.fechaHora(),s.precioBase()); l.add(x); return x;} public boolean existsBySalaAndFecha(String salaId,LocalDateTime fh){return l.stream().anyMatch(a->a.salaId().equals(salaId)&&a.fechaHora().equals(fh));} public java.util.List<Sesion> findByPelicula(String t){return List.of();} public Sesion findById(Long id){return l.stream().filter(a->a.id().equals(id)).findFirst().orElseThrow();}}
public class ProgramarSesionServiceUnitTest {
  @Test void programar_y_evitar_solape(){ var p=new PeliFake();var s=new SalaFake();var r=new SesionFake(); var svc=new ProgramarSesionService(p,s,r); p.save(new Pelicula(1L,"Matrix",130,"+16")); s.save(new Sala("S1",5,5));
    var t=LocalDateTime.of(2025,10,15,20,0); var a=svc.programar("Matrix","S1",t,10.0); assertNotNull(a.id()); assertThrows(IllegalStateException.class,()->svc.programar("Matrix","S1",t,10.0));}
}
