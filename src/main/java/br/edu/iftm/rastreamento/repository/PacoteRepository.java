package br.edu.iftm.rastreamento.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.rastreamento.model.Pacote;
@Repository
public interface PacoteRepository extends CrudRepository<Pacote, Long> {
	// busca pacotes pelo status
    List<Pacote> findByStatus(String status);

    // busca pacotes pelo destinatário
    List<Pacote> findByDestinatario(String destinatario);
}
