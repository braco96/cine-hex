package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Entrada; import java.util.List;
public interface EntradaRepository { boolean asientoOcupado(Long sesionId, int fila, int columna); Entrada save(Entrada e); List<Entrada> findBySesion(Long sesionId); }
