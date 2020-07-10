package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.entities.Usuario;
import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.domain.enums.ESimNao;
import br.com.loja_virtual.domain.exceptions.*;
import br.com.loja_virtual.services.IFuncaoService;
import br.com.loja_virtual.services.IUsuarioService;
import br.com.loja_virtual.services.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final PasswordEncoder passwordEncoder;

    private final IFuncaoService funcaoService;

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(PasswordEncoder passwordEncoder, IFuncaoService funcaoService, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.funcaoService = funcaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario autenticar(String nome, String senha) {
        Usuario usuarioConsulta =
                usuarioRepository.findByNome(nome)
                .orElseThrow(() -> new CombinacaoUsuarioSenhaNaoValidaException("Combinação usuário e senha inválda"));
        boolean matches = passwordEncoder.matches(usuarioConsulta.getSenha(), senha);
        if(matches) return usuarioConsulta;
        throw new CombinacaoUsuarioSenhaNaoValidaException("Combinação usuário e senha inválda");
    }

    @Override
    public Usuario inserir(Usuario usuario) {
        if(usuarioRepository.existsByNome(usuario.getNome()))
            throw new NomeUsuarioEmUsoException("Não é possível usar este nome de usuário: " + usuario.getNome());
        if(usuario.getFuncoes().isEmpty())
            throw new UsuarioSemFuncaoException("Não é possível cadastrar um usuário sem função");
        List<EFuncao> nomesFuncoesUsuario =
                usuario.getFuncoes().stream()
                        .map((m) -> m.getNome()).collect(Collectors.toList());
        List<Funcao> funcoesUsuario = funcaoService.buscarFuncoesPorNomes(nomesFuncoesUsuario);
        Usuario usuarioComFuncoesESenhaEcriptada =
                    Usuario.builder()
                    .nome(usuario.getNome())
                    .senha(passwordEncoder.encode(usuario.getSenha()))
                    .ativo(ESimNao.SIM)
                    .funcoes(new HashSet<>(funcoesUsuario))
                    .build();
        return usuarioRepository.save(usuarioComFuncoesESenhaEcriptada);
    }

    @Override
    public void alterarSenha(Long id, String senhaAntiga, String senhaNova, String senhaNovaConfirmacao) {
        if(!senhaNova.equals(senhaNovaConfirmacao))
            throw new SenhaNovaEConfirmacaoNaoCombinamException("A senha nova e a confirmação não são iguais");
        Usuario usuarioConsulta = usuarioRepository.findById(id)
                .orElseThrow(() -> new CombinacaoUsuarioSenhaNaoValidaException("Combinção usuário e senha inválida"));
        boolean matches = passwordEncoder.matches(usuarioConsulta.getSenha(),senhaAntiga);
        if(!matches)
            throw new CombinacaoUsuarioSenhaNaoValidaException("Combinção usuário e senha inválida");
        Usuario usuarioComSenhaAlterada =
                Usuario.builder()
                        .id(usuarioConsulta.getId())
                        .nome(usuarioConsulta.getNome())
                        .funcoes(usuarioConsulta.getFuncoes())
                        .senha(passwordEncoder.encode(senhaNova))
                        .build();
        usuarioRepository.save(usuarioComSenhaAlterada);
    }

    @Override
    public void desabilitar(Long id) {
        Usuario usuarioConsulta = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado pelo id: " + id));
        Usuario usuarioDesabilitado =
                Usuario.builder()
                .id(usuarioConsulta.getId())
                .nome(usuarioConsulta.getNome())
                .funcoes(usuarioConsulta.getFuncoes())
                .ativo(ESimNao.NAO)
                .build();
        usuarioRepository.save(usuarioDesabilitado);
    }


    @Override
    public void habilitar(Long id) {
        Usuario usuarioConsulta = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Nenhum usuário encontrado pelo id: " + id));
        Usuario usuarioDesabilitado =
                Usuario.builder()
                        .id(usuarioConsulta.getId())
                        .nome(usuarioConsulta.getNome())
                        .funcoes(usuarioConsulta.getFuncoes())
                        .ativo(ESimNao.SIM)
                        .build();
        usuarioRepository.save(usuarioDesabilitado);
    }
}
