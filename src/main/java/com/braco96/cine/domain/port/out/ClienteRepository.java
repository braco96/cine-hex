package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Cliente;
public interface ClienteRepository { Cliente save(Cliente c); Cliente findById(Long id); }
