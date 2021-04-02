package br.com.cwi.reset.reseat.controllers;

import br.com.cwi.reset.reseat.requests.FazerPedidoRequest;
import br.com.cwi.reset.reseat.responses.EntregadorResponse;
import br.com.cwi.reset.reseat.responses.FazerPedidoResponse;
import br.com.cwi.reset.reseat.responses.PedidoResponse;
import br.com.cwi.reset.reseat.services.PedidoServices.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private FazerPedidoService fazerPedidoService;

    @Autowired
    private ConsultasPedidoService consultasPedidoService;

    @Autowired
    private EntregarPedidoService entregarPedidoService;

    @Autowired
    private FinalizarPedidoService finalizarPedidoService;

    @Autowired
    private CancelarPedidoService cancelarPedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FazerPedidoResponse fazerPedido(@RequestBody @Valid FazerPedidoRequest request) {
        return fazerPedidoService.fazerPedido(request);
    }

    @GetMapping("/{id}")
    public PedidoResponse buscarPedidoPorId(@PathVariable("id") Long id) {
        return consultasPedidoService.buscarPedidoPorId(id);
    }

    @PutMapping("/{id}/entregar")
    public EntregadorResponse entregarPedido(@PathVariable("id") Long id) {
        return entregarPedidoService.entregarPedido(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizarPedido(@PathVariable("id") Long id) {
        finalizarPedidoService.finalizarPedido(id);
    }

    @DeleteMapping("/{id}")
    public void cancelarPedido(@PathVariable("id") Long id) {
        cancelarPedidoService.cancelarPedido(id);
    }
}
