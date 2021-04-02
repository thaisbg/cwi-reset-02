package br.com.cwi.reset.reseat.services.EstabelecimentoServices;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.exceptions.EnderecoNaoEncontradoException;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoverEnderecoEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public void removerEndereco(Long id, Long idEndereco) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException("Estabelecimento não encontrado."));
        List<Endereco> listaEnderecos = estabelecimento.getEnderecos();
        Endereco enderecoDeletar = null;

        for (Endereco endereco : listaEnderecos) {
            if (endereco.getId().equals(idEndereco)) {
                enderecoDeletar = endereco;
            }
        }

        if (enderecoDeletar == null) {
            throw new EnderecoNaoEncontradoException("Endereço não encontrado entre os endereços do estabelecimento informado.");
        }

        listaEnderecos.remove(enderecoDeletar);
        estabelecimentoRepository.save(estabelecimento);
    }
}
