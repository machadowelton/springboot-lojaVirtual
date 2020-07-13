package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Consumidor;
import br.com.loja_virtual.domain.exceptions.ConsumidorNaoEncontradoException;
import br.com.loja_virtual.services.IConsumidorService;
import br.com.loja_virtual.services.impl.UsuarioServiceImpl;
import br.com.loja_virtual.services.impl.ConsumidorServiceImpl;
import br.com.loja_virtual.services.repositories.ConsumidorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ConsumidorServiceImplTest {


    @MockBean
    ConsumidorRepository consumidorRepositoryMock;

    @MockBean
    UsuarioServiceImpl usuarioService;

    IConsumidorService consumidorService;

    @BeforeEach
    void setUp() {
        this.consumidorService = new ConsumidorServiceImpl(consumidorRepositoryMock, usuarioService);
    }

    @Test
    @DisplayName("Deve retornar um erro ConsumidorNaoEncontradoException quando não encontrar pelo id")
    void buscarPorIdConsumidorNaoEncontradoTest() {
        Long idConsumidorConsulta = 1l;
        String mensagemException = "Nenhum consumidor encontrado pelo id: " + idConsumidorConsulta;
        when(consumidorRepositoryMock.findById(idConsumidorConsulta)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> consumidorService.buscarPorId(idConsumidorConsulta));
        assertThat(throwable).isInstanceOf(ConsumidorNaoEncontradoException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retornar um consumidor pelo id")
    void buscarPorIdTest() {
        Long idConsumidorConsulta = 1l;
        Consumidor consumidorMock =
                Consumidor.builder()
                        .id(idConsumidorConsulta)
                        .nomeCompleto("Jose da Silva")
                        .dataNascimento(LocalDate.now())
                        .email("jose@email.com")
                        .cpf("12345678989")
                        .build();
        when(consumidorRepositoryMock.findById(idConsumidorConsulta)).thenReturn(Optional.of(consumidorMock));
        Consumidor consumidorConsulta = consumidorService.buscarPorId(idConsumidorConsulta);
        assertThat(consumidorConsulta).isNotNull();
        assertThat(consumidorConsulta.equals(consumidorMock));
    }


    @Test
    void inserir() {
    }

    @Test
    @DisplayName("Deve retornar uma page com uma lista de consumidores e informações da page")
    void listarTest() {
        Pageable pageableEntrada = PageRequest.of(0, 10);
        List<Consumidor> consumidoresMock =
                Arrays.asList(
                        Consumidor.builder()
                                .id(1l)
                                .nomeCompleto("João da Silva")
                                .dataNascimento(LocalDate.now())
                                .email("joaodasilva@email.com")
                                .cpf("12345678989")
                                .build(),
                        Consumidor.builder()
                                .id(2l)
                                .nomeCompleto("José da Silva")
                                .dataNascimento(LocalDate.now())
                                .email("josedasilva@email.com")
                                .cpf("98765432112")
                                .build()
                );
        Page<Consumidor> pageConsumidorMock = new PageImpl<Consumidor>(consumidoresMock, pageableEntrada, consumidoresMock.size());
        when(consumidorRepositoryMock.findAll(pageableEntrada)).thenReturn(pageConsumidorMock);
        Page<Consumidor> consumidoresConsulta = consumidorService.listar(pageableEntrada);
        assertThat(consumidoresConsulta).isNotNull();
        assertThat(consumidoresConsulta.getContent()).isNotEmpty();
        assertThat(consumidoresConsulta.equals(consumidoresMock));
    }

    @Test
    @DisplayName("Deve retornar uma page com uma lista vazia e informações da page")
    void listarListaVaziaTest() {
        Pageable pageableEntrada = PageRequest.of(0, 10);
        List<Consumidor> consumidoresMock = Arrays.asList();
        Page<Consumidor> pageConsumidorMock = new PageImpl<Consumidor>(consumidoresMock, pageableEntrada, consumidoresMock.size());
        when(consumidorRepositoryMock.findAll(pageableEntrada)).thenReturn(pageConsumidorMock);
        Page<Consumidor> consumidoresConsulta = consumidorService.listar(pageableEntrada);
        assertThat(consumidoresConsulta).isNotNull();
        assertThat(consumidoresConsulta.getContent()).isEmpty();
        assertThat(consumidoresConsulta.equals(consumidoresMock));
    }

    @Test
    void atualizar() {
    }


}