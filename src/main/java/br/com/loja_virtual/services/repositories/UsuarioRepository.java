package br.com.loja_virtual.services.repositories;

import br.com.loja_virtual.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);

    boolean existsByNome(String nome);
}
