package br.com.cwi.reset.reseat.services.EstabelecimentoServices;

import br.com.cwi.reset.reseat.dominio.Endereco;
import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoNaoEncontradoException;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarEnderecoEstabelecimentoService {

    @Autowired
    EstabelecimentoRepository estabelecimentoRepository;

    public void cadastrarEndereco(Long id, Endereco endereco) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException("Estabelecimento não encontrado. Digite um ID válido."));
        Endereco enderecoSalvar = endereco;
        List<Endereco> listaEnderecos = estabelecimento.getEnderecos();

        listaEnderecos.add(enderecoSalvar);
        estabelecimento.setEnderecos(listaEnderecos);
        estabelecimentoRepository.save(estabelecimento);
    }
}
