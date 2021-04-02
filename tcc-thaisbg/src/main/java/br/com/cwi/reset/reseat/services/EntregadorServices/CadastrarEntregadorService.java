package br.com.cwi.reset.reseat.services.EntregadorServices;

import br.com.cwi.reset.reseat.dominio.Entregador;
import br.com.cwi.reset.reseat.exceptions.EntregadorComMesmoCpfException;
import br.com.cwi.reset.reseat.repositories.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Entregador cadastrarEntregador (Entregador entregador) {
        validarEntregador(entregador);
        return entregadorRepository.save(entregador);
    }

    private void validarEntregador(Entregador entregador) {
        if (entregadorRepository.existsByCpf(entregador.getCpf())) {
            throw new EntregadorComMesmoCpfException("Já existe um entregador cadastrado com este CPF.");
        }
    }
}
