package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.domain.exceptions.FuncaoNaoEncontradaException;
import br.com.loja_virtual.services.IFuncaoService;
import br.com.loja_virtual.services.repositories.FuncaoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FuncaoServiceImplTest {


    @MockBean
    private FuncaoRepository funcaoRepository;

    IFuncaoService funcaoService;

    @BeforeEach
    void setUp() {
        this.funcaoService = new FuncaoServiceImpl(funcaoRepository);
    }

    @Test
    @DisplayName("Deve retornar uma lista de funções com dois elementos: GERENTE e OPERADOR")
    void listarFuncoesGerenteEOperadorTest() {
        List<Funcao> funcoesMock =
                Arrays.asList(
                        Funcao.builder()
                            .id(1l)
                            .nome(EFuncao.GERENTE)
                            .descricao("Pode consultar, alterar e deletar gerentes e operadores")
                            .build(),
                        Funcao.builder()
                                .id(2l)
                                .nome(EFuncao.OPERADOR)
                                .descricao("Pode consultar, alterar e deletar produtos, e consultar pedidos")
                                .build()
                );
        List<EFuncao> nomesFuncoes = funcoesMock.stream().map(m -> m.getNome()).collect(Collectors.toList());
        when(funcaoRepository.findByNomeIn(nomesFuncoes)).thenReturn(funcoesMock);
        List<Funcao> funcoesGerenteEOperadorConsulta = funcaoService.listarFuncoesGerenteEOperador();
        assertThat(funcoesGerenteEOperadorConsulta.isEmpty()).isFalse();
        assertThat(funcoesGerenteEOperadorConsulta.equals(funcoesGerenteEOperadorConsulta)).isTrue();
    }

    @Test
    @DisplayName("Deve retornar um erro FuncaoNaoEncontradaException quando não achar as funções Gerente e Operador")
    void listarFuncoesGerenteEOperadorFuncaoNaoEncontradaTest() {
        List<EFuncao> nomesFuncoes = Arrays.asList(EFuncao.GERENTE, EFuncao.OPERADOR);
        String mensagemException = "Os seguintes nomes de funções não foram encontrados: " + nomesFuncoes;
        when(funcaoRepository.findByNomeIn(nomesFuncoes)).thenReturn(Arrays.asList());
        Throwable throwable = catchThrowable(() -> {
            funcaoService.listarFuncoesGerenteEOperador();
        });
        assertThat(throwable).isInstanceOf(FuncaoNaoEncontradaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retorar uma lista com 1 ou mais funções buscadas por nomes da função")
    void buscarFuncoesPorNomesTest() {
        List<Funcao> funcoesMock =
                Arrays.asList(
                        Funcao.builder()
                                .id(1l)
                                .nome(EFuncao.GERENTE)
                                .descricao("Pode consultar, alterar e deletar gerentes e operadores")
                                .build(),
                        Funcao.builder()
                                .id(2l)
                                .nome(EFuncao.OPERADOR)
                                .descricao("Pode consultar, alterar e deletar produtos, e consultar pedidos")
                                .build(),
                        Funcao.builder()
                                .id(3l)
                                .nome(EFuncao.CONSUMIDOR)
                                .descricao("Pode consultar, alterar seus dados, consultar produtos e fazer pedidos")
                                .build()
                );
        List<EFuncao> nomesFuncoes = funcoesMock.stream().map(m -> m.getNome()).collect(Collectors.toList());
        when(funcaoRepository.findByNomeIn(nomesFuncoes)).thenReturn(funcoesMock);
        List<Funcao> funcoesConsulta = funcaoService.buscarFuncoesPorNomes(nomesFuncoes);
        assertThat(funcoesConsulta.isEmpty()).isFalse();
        assertThat(funcoesConsulta.equals(funcoesMock)).isTrue();
    }

    @Test
    @DisplayName("Deve retornar um erro FuncaoNaoEncontradaException quando não encontradas funcções pelos nomes")
    void buscarFuncoesPorNomesFuncoesNaoEncontradaTest() {
        List<EFuncao> nomesFuncoes = Arrays.asList(EFuncao.GERENTE, EFuncao.CONSUMIDOR);
        String mensagemException = "Nenhuma função encontrada pelos nomes: " + nomesFuncoes;
        when(funcaoRepository.findByNomeIn(nomesFuncoes)).thenReturn(Arrays.asList());
        Throwable throwable = catchThrowable(() -> {
            funcaoService.buscarFuncoesPorNomes(nomesFuncoes);
        });
        assertThat(throwable).isInstanceOf(FuncaoNaoEncontradaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }
}