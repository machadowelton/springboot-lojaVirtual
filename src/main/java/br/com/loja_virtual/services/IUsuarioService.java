package br.com.loja_virtual.services;

import br.com.loja_virtual.domain.entities.Usuario;

public interface IUsuarioService {

    Usuario autenticar(String nome, String senha);

    Usuario inserir(Usuario usuario);

    void alterarSenha(Long id, String senhaAntiga, String senhaNova, String senhaNovaConfirmacao);

    void desabilitar(Long id);

    void habilitar(Long id);

}
