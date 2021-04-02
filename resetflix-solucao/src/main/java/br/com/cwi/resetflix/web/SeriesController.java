package br.com.cwi.resetflix.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cwi.resetflix.domain.Genero;
import br.com.cwi.resetflix.request.CriarSerieRequest;
import br.com.cwi.resetflix.response.ConsultarDetalhesSerieResponse;
import br.com.cwi.resetflix.response.SerieResponse;
import br.com.cwi.resetflix.service.SeriesService;
import br.com.cwi.resetflix.service.UsuarioService;

@RestController
@RequestMapping("/series")
public class SeriesController implements SeriesContract {

    @Autowired
    private SeriesService seriesService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @GetMapping
    public List<SerieResponse> getSeries(@RequestParam(value = "genero", required = false) final Genero genero) {
        return seriesService.getSeries(genero);
    }

    @Override
    @GetMapping("/{id}")
    public ConsultarDetalhesSerieResponse getSerieById(@PathVariable("id") final Long id) {
        return seriesService.consultarDetalhesSerie(id);
    }

    @Override
    @PostMapping
    public Long criarSerie(@RequestBody final CriarSerieRequest request) {
        return seriesService.criarSerie(request);
    }

    @Override
    @GetMapping("/recomendacoes")
    public List<SerieResponse> getRecomendacoesSeries() {
        return seriesService.obterSeriesRecomendadas();
    }

    @Override
    @PostMapping("/{id}/{temporada}/{episodio}/assistir")
    public void assistirSerie(@PathVariable("id") final Long id, @PathVariable("temporada") final Integer temporada,
        @PathVariable("episodio") final Integer episodio) {
        usuarioService.assistirSerie(id, temporada, episodio);
    }
}
