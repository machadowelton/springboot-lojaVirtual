package br.com.loja_virtual.services.repositories;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.enums.EFuncao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class FuncaoRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private FuncaoRepository funcaoRepository;

    @Autowired
    Environment env;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Deve retornar uma lista funções de acordo com uma lista de nome de funções")
    void findByNomeInTest() {
        List<Funcao> funcoesParaSalvar =
                Arrays.asList(
                        Funcao.builder()
                                .nome(EFuncao.GERENTE)
                                .descricao("Maior nivel")
                                .build(),
                        Funcao.builder()
                                .nome(EFuncao.OPERADOR)
                                .descricao("Nivel intermediário")
                                .build(),
                        Funcao.builder()
                                .nome(EFuncao.CONSUMIDOR)
                                .descricao("Menor nivel")
                                .build()
                );
        funcoesParaSalvar.stream().forEach(testEntityManager::persist);
        List<EFuncao> nomesFuncoes = funcoesParaSalvar.stream().map(m -> m.getNome()).collect(Collectors.toList());
        List<Funcao> funcoesRetornoConsulta = funcaoRepository.findByNomeIn(nomesFuncoes);
        assertThat(funcoesRetornoConsulta).isNotEmpty();
        assertThat(funcoesRetornoConsulta.size()).isEqualTo(funcoesParaSalvar.size());
        assertThat(funcoesRetornoConsulta.equals(funcoesParaSalvar)).isTrue();
    }


    @Test
    @DisplayName("Deve retornar uma lista vazia")
    void findByNomeInEmptyListTest() {
        List<Funcao> funcoesParaSalvar =
                Arrays.asList(
                        Funcao.builder()
                                .nome(EFuncao.GERENTE)
                                .descricao("Maior nivel")
                                .build(),
                        Funcao.builder()
                                .nome(EFuncao.OPERADOR)
                                .descricao("Nivel intermediário")
                                .build()
                );
        funcoesParaSalvar.stream().forEach(testEntityManager::persist);
        List<EFuncao> nomesFuncoes = Arrays.asList(EFuncao.CONSUMIDOR);
        List<Funcao> funcoesRetornoConsulta = funcaoRepository.findByNomeIn(nomesFuncoes);
        assertThat(funcoesRetornoConsulta).isNotNull();
        assertThat(funcoesRetornoConsulta).isEmpty();
    }

}