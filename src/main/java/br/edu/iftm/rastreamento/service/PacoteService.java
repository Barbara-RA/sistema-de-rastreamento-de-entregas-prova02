package br.edu.iftm.rastreamento.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.repository.RastreamentoRepository;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    public List<Pacote> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList;
    }

    public Pacote getPacoteById(Long id) {
        return pacoteRepository.findById(id).orElseThrow(() ->
            new NaoAcheiException("Pacote com ID " + id + " não encontrado"));
    }

    public Pacote createPacote(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public Pacote updatePacote(Long id, Pacote pacoteDetails) {
        Pacote pacote = pacoteRepository.findById(id).orElseThrow(() ->
            new NaoAcheiException("Pacote com ID " + id + " não encontrado"));

        pacote.setId(id);
        pacote.atualizarStatus(pacoteDetails.getStatus(), Date.from(Instant.now()), "não implementado");

        // obter o último rastreamento
        Rastreamento ultiRastreamento = pacote.getRastreamentos().get(pacote.getRastreamentos().size() - 1);
        rastreamentoRepository.save(ultiRastreamento);

        return pacoteRepository.save(pacote);
    }

    public void deletePacote(Long id) {
        Pacote pacote = getPacoteById(id);
        pacoteRepository.delete(pacote);
    }

    public List<Pacote> buscarPacotesPorStatus(String status) {
        return pacoteRepository.findByStatus(status);
    }

    public List<Pacote> buscarPacotesPorDestinatario(String destinatario) {
        return pacoteRepository.findByDestinatario(destinatario);
    }
}