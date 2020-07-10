package br.com.loja_virtual.services.repositories;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.enums.EFuncao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long> {

    List<Funcao> findByNomeIn(List<EFuncao> funcoes);

}
