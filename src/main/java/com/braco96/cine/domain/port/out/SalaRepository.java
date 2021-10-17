package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Sala; import java.util.Optional;
public interface SalaRepository { Optional<Sala> findById(String id); Sala save(Sala s); }
