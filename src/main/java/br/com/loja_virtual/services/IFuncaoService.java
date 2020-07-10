package br.com.loja_virtual.services;

import br.com.loja_virtual.domain.entities.Funcao;
import br.com.loja_virtual.domain.enums.EFuncao;

import java.util.List;

public interface IFuncaoService {

    List<Funcao> listarFuncoesGerenteEOperador();

    List<Funcao> buscarFuncoesPorNomes(List<EFuncao> nomes);

}
