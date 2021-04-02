package br.com.cwi.resetflix.service;

import static org.apache.logging.log4j.util.Strings.isEmpty;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.entity.FilmeEntity;
import br.com.cwi.resetflix.exception.BadRequestException;
import br.com.cwi.resetflix.exception.NotFoundException;
import br.com.cwi.resetflix.mapper.ConsultarDetalhesDiretorResponseMapper;
import br.com.cwi.resetflix.mapper.DiretorEntityMapper;
import br.com.cwi.resetflix.mapper.DiretoresResponseMapper;
import br.com.cwi.resetflix.repository.DiretoresRepository;
import br.com.cwi.resetflix.repository.FilmeRepository;
import br.com.cwi.resetflix.request.CriarDiretorRequest;
import br.com.cwi.resetflix.response.ConsultarDetalhesDiretorResponse;
import br.com.cwi.resetflix.response.DiretoresResponse;

@Service
public class DiretoresService {

    @Autowired
    private DiretoresRepository diretoresRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private DiretoresResponseMapper diretoresResponseMapper;

    @Autowired
    private DiretorEntityMapper diretorEntityMapper;

    @Autowired
    private ConsultarDetalhesDiretorResponseMapper consultarDetalhesDiretorResponseMapper;

    public List<DiretoresResponse> getDiretores() {
        final List<DiretorEntity> diretores = diretoresRepository.getDiretores();
        return diretoresResponseMapper.mapear(diretores);
    }

    public Long criarDiretor(final CriarDiretorRequest request) {

        if (request == null || isEmpty(request.getNome())) {
            throw new BadRequestException("Dados inválidos para cadastro de diretor");
        }

        final DiretorEntity diretorSalvar = diretorEntityMapper.mapear(request);

        return diretoresRepository.criarDiretor(diretorSalvar);
    }

    public ConsultarDetalhesDiretorResponse consultarDetalhesDiretor(final Long id) {
        final DiretorEntity diretorSalvo = diretoresRepository.acharDiretorPorId(id);

        if (diretorSalvo == null) {
            throw new NotFoundException("Diretor não encontrado");
        }

        final List<FilmeEntity> filmesDiretor = filmeRepository.acharFilmesDiretor(id);
        return consultarDetalhesDiretorResponseMapper.mapear(diretorSalvo, filmesDiretor);
    }
}
