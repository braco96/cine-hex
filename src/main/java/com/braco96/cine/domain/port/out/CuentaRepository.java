package com.braco96.cine.domain.port.out;
import com.braco96.cine.domain.model.Cuenta;
public interface CuentaRepository { Cuenta findByClienteId(Long clienteId); Cuenta save(Cuenta c); }
