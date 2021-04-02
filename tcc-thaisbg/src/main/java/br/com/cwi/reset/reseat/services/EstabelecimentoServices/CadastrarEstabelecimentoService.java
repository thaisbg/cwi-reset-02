package br.com.cwi.reset.reseat.services.EstabelecimentoServices;

import br.com.cwi.reset.reseat.dominio.Estabelecimento;
import br.com.cwi.reset.reseat.exceptions.EstabelecimentoComMesmoCnpjException;
import br.com.cwi.reset.reseat.repositories.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento cadastrarEstabelecimento(Estabelecimento estabelecimento) {
        validarEstabelecimento(estabelecimento);
        return estabelecimentoRepository.save(estabelecimento);
    }

    private void validarEstabelecimento(Estabelecimento estabelecimento) {
        if (estabelecimentoRepository.existsByCnpj(estabelecimento.getCnpj())) {
            throw new EstabelecimentoComMesmoCnpjException("Já existe um estabelecimento cadastrado com este CNPJ." +
                    "Acesse a conta já existente ou tenta novamente com outro CNPJ.");
        }
    }
}
