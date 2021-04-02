package br.com.cwi.reset.reseat.mappers;

import br.com.cwi.reset.reseat.dominio.Entregador;
import br.com.cwi.reset.reseat.responses.EntregadorResponse;
import org.springframework.stereotype.Component;

@Component
public class EntregadorResponseMapper {

    public EntregadorResponse mapear(Entregador entregador) {
        EntregadorResponse response = new EntregadorResponse();
        response.setId(entregador.getId());
        response.setNome(entregador.getNome());
        response.setCpf(entregador.getCpf());
        response.setTelefone(entregador.getTelefone());
        response.setPlacaVeiculo(entregador.getPlacaVeiculo());
        return response;
    }
}
