package com.braco96.cine;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.test.context.DynamicPropertyRegistry; import org.springframework.test.context.DynamicPropertySource; import org.testcontainers.containers.MySQLContainer; import org.testcontainers.junit.jupiter.Container; import org.testcontainers.junit.jupiter.Testcontainers;
import com.braco96.cine.domain.model.*; import com.braco96.cine.domain.port.out.*; import com.braco96.cine.application.service.*;
import java.time.LocalDateTime; import java.util.List; import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Testcontainers
public class CineHexIntegrationTest {
  @Container public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.3").withDatabaseName("cine").withUsername("root").withPassword("collado");
  @DynamicPropertySource static void props(DynamicPropertyRegistry r){ r.add("spring.datasource.url", mysql::getJdbcUrl); r.add("spring.datasource.username", mysql::getUsername); r.add("spring.datasource.password", mysql::getPassword); }
  @Autowired PeliculaRepository peli; @Autowired SalaRepository sala; @Autowired ClienteRepository cli; @Autowired CuentaRepository cta; @Autowired ProgramarSesionService prog; @Autowired ComprarEntradasService comp;
  @Test void flujo_completo(){ peli.save(new Pelicula(null,"Dune 2",165,"+12")); sala.save(new Sala("S1",10,12)); var x=cli.save(new Cliente(null,"Luis","luis@cine.es")); cta.save(new Cuenta(null,x.id(),200));
    var s = prog.programar("Dune 2","S1", LocalDateTime.of(2025,10,15,20,30), 12.0); assertNotNull(s.id());
    List<Entrada> es = comp.comprarBloque(s.id(), x.id(), 4); assertEquals(4, es.size());
  }
}
