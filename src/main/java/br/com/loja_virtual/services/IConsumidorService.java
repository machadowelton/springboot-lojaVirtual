package br.com.loja_virtual.services;

import br.com.loja_virtual.domain.entities.Consumidor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConsumidorService {

    Consumidor buscarPorId(Long id);

    Consumidor inserir(Consumidor consumidor);

    Page<Consumidor> listar(Pageable pageable);

    Consumidor atualizar(Long id, Consumidor consumidor);


}
