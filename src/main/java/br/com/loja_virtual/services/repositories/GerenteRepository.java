package br.com.loja_virtual.services.repositories;

import br.com.loja_virtual.domain.entities.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteRepository extends JpaRepository<Gerente, Long> {
}
