package com.braco96.cine.infrastructure.persistence.jpa; import com.braco96.cine.domain.model.Pelicula; import com.braco96.cine.domain.port.out.PeliculaRepository; import org.springframework.stereotype.Repository; import java.util.Optional;
@Repository public class PeliculaAdapter implements PeliculaRepository {
  private final SpringPeliculaRepository repo; public PeliculaAdapter(SpringPeliculaRepository r){this.repo=r;}
  public Optional<Pelicula> findByTitulo(String t){ return repo.findByTitulo(t).map(e->new Pelicula(e.id,e.titulo,e.duracionMin,e.clasificacion)); }
  public Pelicula save(Pelicula p){ var e=new JpaPelicula(); e.id=p.id(); e.titulo=p.titulo(); e.duracionMin=p.duracionMin(); e.clasificacion=p.clasificacion(); e=repo.save(e); return new Pelicula(e.id,e.titulo,e.duracionMin,e.clasificacion); }
}
