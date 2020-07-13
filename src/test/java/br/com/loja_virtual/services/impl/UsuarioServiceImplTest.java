package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.entities.Usuario;
import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.domain.enums.ESimNao;
import br.com.loja_virtual.domain.exceptions.*;
import br.com.loja_virtual.services.IFuncaoService;
import br.com.loja_virtual.services.IUsuarioService;
import br.com.loja_virtual.services.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @MockBean
    UsuarioRepository usuarioRepositoryMock;

    IUsuarioService usuarioService;

    @MockBean
    PasswordEncoder encoderMock;

    @MockBean
    IFuncaoService funcaoServiceMock;

    @BeforeEach
    void setUp() {
        this.usuarioService = new UsuarioServiceImpl(encoderMock, funcaoServiceMock, usuarioRepositoryMock);
    }

    @Test
    @DisplayName("Deve retornar um usuário que combine com o nome de usuário e senha enviados")
    void autenticarTest() {
        String nomeUsuarioEntrada = "nomeusuario@email.com";
        String senhaLimpaEntrada = "1234";
        String senhaEncriptada = passwordEncoder.encode(senhaLimpaEntrada);
        Usuario usuarioMock = Usuario.builder()
                .id(1l)
                .nome(nomeUsuarioEntrada)
                .senha(senhaEncriptada)
                .ativo(ESimNao.SIM)
                .build();
        when(usuarioRepositoryMock.findByNome(nomeUsuarioEntrada)).thenReturn(Optional.of(usuarioMock));
        when(encoderMock.matches(usuarioMock.getSenha(), senhaLimpaEntrada)).thenReturn(true);
        Usuario usuarioAutenticacao = usuarioService.autenticar(nomeUsuarioEntrada, senhaLimpaEntrada);
        assertThat(usuarioAutenticacao).isNotNull();
        assertThat(usuarioAutenticacao.equals(usuarioMock)).isTrue();
    }

    @Test
    @DisplayName("Deve retornar um erro CombinacaoUsuarioESenhaNaovalidaException quando não encontrar o usuário pelo nome")
    void autenticarUsuarioNaoEncontradoTest() {
        String nomeUsuarioEntrada = "nomeusuario@email.com";
        String senhaLimpaEntrada = "1234";
        String mensagemException = "Combinação usuário e senha inválda";
        when(usuarioRepositoryMock.findByNome(nomeUsuarioEntrada)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> usuarioService.autenticar(nomeUsuarioEntrada, senhaLimpaEntrada));
        assertThat(throwable).isInstanceOf(CombinacaoUsuarioSenhaNaoValidaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retornar um erro CombinacaoUsuarioESenhaNaovalidaException quando a senha informada não bater com a senha do usuário")
    void autenticarCombinacaoUsuarioESenhaNaoValidoTest() {
        String nomeUsuarioEntrada = "nomeusuario@email.com";
        String senhaLimpaEntrada = "1234";
        String senhaEncriptada = passwordEncoder.encode("4321");
        String mensagemException = "Combinação usuário e senha inválda";
        Usuario usuarioMock = Usuario.builder()
                .id(1l)
                .nome(nomeUsuarioEntrada)
                .senha(senhaEncriptada)
                .ativo(ESimNao.SIM)
                .build();
        when(usuarioRepositoryMock.findByNome(nomeUsuarioEntrada)).thenReturn(Optional.of(usuarioMock));
        when(encoderMock.matches(usuarioMock.getSenha(), senhaLimpaEntrada)).thenReturn(false);
        Throwable throwable = catchThrowable(() -> usuarioService.autenticar(nomeUsuarioEntrada, senhaLimpaEntrada));
        assertThat(throwable).isInstanceOf(CombinacaoUsuarioSenhaNaoValidaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve inserir um usuario com sucesso")
    void inserirUsuarioTest() {
        Set<Funcao> funcoesUsuarioInserir =
                Arrays.asList(Funcao.builder()
                        .nome(EFuncao.GERENTE)
                        .build())
                        .stream().collect(Collectors.toSet());
        Usuario usuarioInserir = Usuario.builder()
                .nome("email@email.com")
                .senha("1234")
                .funcoes(funcoesUsuarioInserir)
                .build();
        Set<Funcao> funcoesUsuarioMock = Arrays.asList(Funcao.builder()
                .id(1l)
                .nome(EFuncao.GERENTE)
                .descricao("Pode consultar e cadastrar novos gerentes e operadores, e consultar clientes")
                .build())
                .stream().collect(Collectors.toSet());
        String senhaEncriptada = passwordEncoder.encode("1234");
        Usuario usuarioMock = Usuario.builder()
                                    .id(1l).ativo(ESimNao.SIM)
                                    .nome("email@mail.com")
                                    .funcoes(funcoesUsuarioMock)
                                    .senha(senhaEncriptada)
                                    .build();
        when(usuarioRepositoryMock.existsByNome(Mockito.anyString())).thenReturn(false);
        List<EFuncao> nomesFuncoes = funcoesUsuarioMock.stream().map((m) -> m.getNome()).collect(Collectors.toList());
        when(funcaoServiceMock.buscarFuncoesPorNomes(nomesFuncoes)).thenReturn(funcoesUsuarioMock.stream().collect(Collectors.toList()));
        when(encoderMock.encode(usuarioInserir.getSenha())).thenReturn(senhaEncriptada);
        Usuario usuarioInserirComFuncoesESenhaEcriptadaMock =
                Usuario.builder()
                        .nome(usuarioInserir.getNome())
                        .senha(senhaEncriptada)
                        .funcoes(funcoesUsuarioMock)
                        .build();
        when(usuarioRepositoryMock.save(usuarioInserirComFuncoesESenhaEcriptadaMock)).thenReturn(usuarioMock);
        Usuario usuarioSalvo = usuarioService.inserir(usuarioInserir);
        assertThat(usuarioSalvo).isNotNull();
        assertThat(usuarioSalvo.equals(usuarioMock)).isTrue();
    }

    @Test
    @DisplayName("Deve retornar um erro NomeUsuarioExistenteException quando houver nome do usuário existente ")
    void inserirNomeUsuarioExisteTest() {
        Usuario usuarioInserir = Usuario.builder()
                .nome("email@email.com")
                .senha("1234")
                .funcoes(new HashSet<>(Arrays.asList(Funcao.builder().build())))
                .build();
        String mensagemException = "Não é possível usar este nome de usuário: " + usuarioInserir.getNome();
        when(usuarioRepositoryMock.existsByNome(usuarioInserir.getNome())).thenReturn(true);
        Throwable throwable = catchThrowable(() -> {
            usuarioService.inserir(usuarioInserir);
        });
        assertThat(throwable).isInstanceOf(NomeUsuarioEmUsoException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retornar um erro UsuarioSemFuncaoException quando a lista de funções estiver vazia")
    void inserirUsuarioSemFuncaoTest() {
        Usuario usuarioInserir = Usuario.builder()
                .nome("email@email.com")
                .senha("1234")
                .build();
        String mensagemException = "Não é possível cadastrar um usuário sem função";
        when(usuarioRepositoryMock.existsByNome(usuarioInserir.getNome())).thenReturn(false);
        Throwable throwable = catchThrowable(() -> {
            usuarioService.inserir(usuarioInserir);
        });
        assertThat(throwable).isInstanceOf(UsuarioSemFuncaoException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }


    @Test
    @DisplayName("Deve retornar um erro CombinacaoUsuarioSenhaNaoValidaException quando a senha antiga não bater com a salva")
    void alterarSenhaTest() {
        Long idUsuario = 1l;
        String senhaAtual = "1234";
        String senhaNova = "12345";
        String senhaNovaConfirmacao = "12345";
        Usuario usuarioMock = Usuario.builder()
                .id(idUsuario)
                .nome("email@mail.com")
                .senha(passwordEncoder.encode(senhaAtual))
                .ativo(ESimNao.SIM)
                .build();
        when(usuarioRepositoryMock.findById(idUsuario)).thenReturn(Optional.of(usuarioMock));
        when(encoderMock.matches(usuarioMock.getSenha(), senhaAtual)).thenReturn(true);
        String senhaNovaSeguraMock = passwordEncoder.encode(senhaNova);
        when(encoderMock.encode(senhaNova)).thenReturn(senhaNovaSeguraMock);
        Usuario usuarioComNovaSenha =
                Usuario.builder()
                        .id(idUsuario)
                        .nome(usuarioMock.getNome())
                        .senha(senhaNovaSeguraMock)
                        .ativo(usuarioMock.getAtivo())
                        .build();
        usuarioService.alterarSenha(idUsuario, senhaAtual, senhaNova, senhaNovaConfirmacao);
        verify(usuarioRepositoryMock, times(1)).save(usuarioComNovaSenha);
    }

    @Test
    @DisplayName("Deve retornar um erro SenhaNovaEConfirmacaoNaoCombinamException quando a senha nova e a confirmação não forem iguais")
    void alterarSenhaNovSenhaEConfirmacaoDivergenteTest() {
        String senhaNova = "1234";
        String senhaNovaConfirmacao = "12345";
        String senhaAntiga = "123456";
        Long idUsuario = 1l;
        String mensagemException = "A senha nova e a confirmação não são iguais";
        Throwable throwable = catchThrowable(() -> usuarioService.alterarSenha(idUsuario, senhaAntiga, senhaNova, senhaNovaConfirmacao));
        assertThat(throwable).isInstanceOf(SenhaNovaEConfirmacaoNaoCombinamException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
        verify(usuarioRepositoryMock, never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Deve retornar um erro CombinacaoUsuarioSenhaNaoValidaException quando o usuário não for encontrado pelo id informado")
    void alterarSenhaUsuarioNaoEncontradoPelodIdTest() {
        String senhaNova = "1234";
        String senhaNovaConfirmacao = "1234";
        String senhaAntiga = "123456";
        Long idUsuario = 1l;
        String mensagemException = "Combinção usuário e senha inválida";
        when(usuarioRepositoryMock.findById(idUsuario)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> usuarioService.alterarSenha(idUsuario, senhaAntiga, senhaNova, senhaNovaConfirmacao));
        assertThat(throwable).isInstanceOf(CombinacaoUsuarioSenhaNaoValidaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retornar um erro CombinacaoUsuarioSenhaNaoValidaException quando a senha antiga não bater com a salva")
    void alterarSenhaCombinacaoUsuarioSenhaInvalidosTest() {
        String senhaNova = "1234";
        String senhaNovaConfirmacao = "1234";
        String senhaAntiga = "123456";
        Long idUsuario = 1l;
        Usuario usuarioMock = Usuario.builder().id(idUsuario).nome("email@mail.com").senha("54321").build();
        String mensagemException = "Combinção usuário e senha inválida";
        when(usuarioRepositoryMock.findById(idUsuario)).thenReturn(Optional.of(usuarioMock));
        when(encoderMock.matches(usuarioMock.getSenha(), senhaAntiga)).thenReturn(false);
        Throwable throwable = catchThrowable(() -> usuarioService.alterarSenha(idUsuario, senhaAntiga, senhaNova, senhaNovaConfirmacao));
        assertThat(throwable).isInstanceOf(CombinacaoUsuarioSenhaNaoValidaException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve retornar um erro UsuarioNaoEncontradoException quando não encontrado pelo id")
    void desabilitarUsuarioNaoEncontradoTest() {
        Long idUsuarioConsulta = 1l;
        String mensagemException = "Nenhum usuário encontrado pelo id: " + idUsuarioConsulta;
        when(usuarioRepositoryMock.findById(idUsuarioConsulta)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> usuarioService.desabilitar(idUsuarioConsulta));
        verify(usuarioRepositoryMock, never()).save(any());
        assertThat(throwable).isInstanceOf(UsuarioNaoEncontradoException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve desabilitar um usuario")
    void desabilitarTest() {
        Long idUsuarioConsulta = 1l;
        Usuario usuarioConsultaMock =
                Usuario.builder()
                        .id(idUsuarioConsulta)
                        .nome("email@mail.com")
                        .ativo(ESimNao.SIM)
                        .build();
        when(usuarioRepositoryMock.findById(idUsuarioConsulta)).thenReturn(Optional.of(usuarioConsultaMock));
        usuarioService.desabilitar(idUsuarioConsulta);
        Usuario usuarioDesabilitado =
                Usuario.builder()
                        .id(idUsuarioConsulta)
                        .nome(usuarioConsultaMock.getNome())
                        .ativo(ESimNao.NAO)
                        .build();
        verify(usuarioRepositoryMock, times(1)).save(usuarioDesabilitado);
    }

    @Test
    @DisplayName("Deve retornar um erro UsuarioNaoEncontradoException quando não encontrar o usuário pelo id")
    void habilitarUsuarioNaoEncontradoTest() {
        Long idUsuarioConsulta = 1l;
        String mensagemException = "Nenhum usuário encontrado pelo id: " + idUsuarioConsulta;
        when(usuarioRepositoryMock.findById(idUsuarioConsulta)).thenReturn(Optional.empty());
        Throwable throwable = catchThrowable(() -> usuarioService.habilitar(idUsuarioConsulta));
        verify(usuarioRepositoryMock, never()).save(any());
        assertThat(throwable).isInstanceOf(UsuarioNaoEncontradoException.class);
        assertThat(throwable.getMessage()).isEqualTo(mensagemException);
    }

    @Test
    @DisplayName("Deve habilitar um usuário")
    void habilitarTest() {
        Long idUsuarioConsulta = 1l;
        Usuario usuarioConsultaMock =
                Usuario.builder()
                        .id(idUsuarioConsulta)
                        .nome("email@mail.com")
                        .ativo(ESimNao.NAO)
                        .build();
        when(usuarioRepositoryMock.findById(idUsuarioConsulta)).thenReturn(Optional.of(usuarioConsultaMock));
        usuarioService.habilitar(idUsuarioConsulta);
        Usuario usuarioHabilitado =
                Usuario.builder()
                        .id(idUsuarioConsulta)
                        .nome(usuarioConsultaMock.getNome())
                        .ativo(ESimNao.SIM)
                        .build();
        verify(usuarioRepositoryMock, times(1)).save(usuarioHabilitado);
    }
}