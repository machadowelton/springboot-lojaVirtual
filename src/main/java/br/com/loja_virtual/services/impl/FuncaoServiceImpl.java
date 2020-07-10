package br.com.loja_virtual.services.impl;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.enums.EFuncao;
import br.com.loja_virtual.domain.exceptions.FuncaoNaoEncontradaException;
import br.com.loja_virtual.services.IFuncaoService;
import br.com.loja_virtual.services.repositories.FuncaoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncaoServiceImpl implements IFuncaoService {

    private final FuncaoRepository funcaoRepository;

    public FuncaoServiceImpl(FuncaoRepository funcaoRepository) {
        this.funcaoRepository = funcaoRepository;
    }

    @Override
    public List<Funcao> listarFuncoesGerenteEOperador() {
        List<Funcao> funcoesGerenteEOperador =
                funcaoRepository.findByNomeIn(Arrays.asList(EFuncao.GERENTE, EFuncao.OPERADOR));
        if(!funcoesGerenteEOperador.isEmpty())
            return funcoesGerenteEOperador;
        String nomesFuncoes = Arrays.asList(EFuncao.GERENTE,EFuncao.OPERADOR).toString();
        throw new FuncaoNaoEncontradaException("Os seguintes nomes de funções não foram encontrados: " + nomesFuncoes);
    }

    @Override
    public List<Funcao> buscarFuncoesPorNomes(List<EFuncao> nomes) {
        List<Funcao> funcoes = funcaoRepository.findByNomeIn(nomes);
        if(!funcoes.isEmpty())
            return funcoes;
        throw new FuncaoNaoEncontradaException("Nenhuma função encontrada pelos nomes: " + nomes);
    }
}
