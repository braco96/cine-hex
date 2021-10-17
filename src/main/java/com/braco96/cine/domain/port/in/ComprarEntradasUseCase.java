package com.braco96.cine.domain.port.in;
import com.braco96.cine.domain.model.Entrada; import java.util.List;
public interface ComprarEntradasUseCase {
    List<Entrada> comprarBloque(Long sesionId, Long clienteId, int cantidad);
}
