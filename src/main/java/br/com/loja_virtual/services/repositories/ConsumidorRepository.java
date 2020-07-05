package br.com.loja_virtual.services.repositories;

import br.com.loja_virtual.domain.entities.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {
}
