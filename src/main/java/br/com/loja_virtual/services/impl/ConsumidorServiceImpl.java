package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Consumidor;
import br.com.loja_virtual.domain.entities.Usuario;
import br.com.loja_virtual.domain.exceptions.ConsumidorNaoEncontradoException;
import br.com.loja_virtual.domain.exceptions.EmailOuCpfEmUsoException;
import br.com.loja_virtual.domain.exceptions.OperacaoNaoPermitidaException;
import br.com.loja_virtual.services.IConsumidorService;
import br.com.loja_virtual.services.repositories.ConsumidorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorServiceImpl implements IConsumidorService {

    private final ConsumidorRepository consumidorRepository;
    private final UsuarioServiceImpl usuarioService;

    public ConsumidorServiceImpl(final ConsumidorRepository consumidorRepository, final UsuarioServiceImpl usuarioService) {
        this.consumidorRepository = consumidorRepository;
        this.usuarioService = usuarioService;
    }

    private Consumidor mapSomenteDadosConsumidor(Consumidor c) {
        return Consumidor.builder()
                .id(c.getId())
                .nomeCompleto(c.getNomeCompleto())
                .dataNascimento(c.getDataNascimento())
                .email(c.getEmail())
                .cpf(c.getCpf())
                .build();
    }

    @Override
    public Consumidor buscarPorId(Long id) {
        return consumidorRepository.findById(id)
                    .map(this::mapSomenteDadosConsumidor)
                    .orElseThrow(() -> new ConsumidorNaoEncontradoException(("Nenhum consumidor encontrado pelo id: " + id)));
    }

    @Override
    public Consumidor inserir(Consumidor consumidor) {
        if(consumidorRepository.existsByEmail(consumidor.getEmail()))
            throw new EmailOuCpfEmUsoException("Não é possível utilizar o email informado: " + consumidor.getEmail());
        if(consumidorRepository.existsByCpf(consumidor.getCpf()))
            throw new EmailOuCpfEmUsoException("Não é possível utilizar o cpf informado: " + consumidor.getCpf());
        Usuario usuarioSalvo = usuarioService.inserir(consumidor.getUsuario());
        consumidor.setUsuario(usuarioSalvo);
        return mapSomenteDadosConsumidor(consumidorRepository.save(consumidor));

    }

    @Override
    public Page<Consumidor> listar(Pageable pageable) {
        return consumidorRepository.findAll(pageable)
                .map(this::mapSomenteDadosConsumidor);
    }

    @Override
    public Consumidor atualizar(Long id, @org.jetbrains.annotations.NotNull Consumidor consumidor) {
        Consumidor consumidorAtual = buscarPorId(id);
        if(!consumidor.getEmail().equals(consumidorAtual.getEmail()))
            throw new OperacaoNaoPermitidaException("Não é possivel alterar email do consumidor através desse recurso");
        if(!consumidor.getCpf().equals(consumidorAtual.getCpf()))
            throw new OperacaoNaoPermitidaException("Não é possivel alterar cpf do consumidor através desse recurso");
        Consumidor consumidorParaAtualizar =
                Consumidor.builder()
                        .id(consumidorAtual.getId())
                        .email(consumidorAtual.getEmail())
                        .cpf(consumidorAtual.getCpf())
                        .nomeCompleto(consumidor.getNomeCompleto())
                        .dataNascimento(consumidor.getDataNascimento())
                        .build();
        return mapSomenteDadosConsumidor(consumidorRepository.save(consumidorParaAtualizar));
    }
}
