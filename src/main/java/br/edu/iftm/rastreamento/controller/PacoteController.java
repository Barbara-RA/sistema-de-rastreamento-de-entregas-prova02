package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    @Operation(
        summary = "Listar todos os pacotes",
        description = "Recupera uma lista de todos os pacotes cadastrados.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public List<Pacote> getAllPacotes() {
        return pacoteService.getAllPacotes();
    }

    @PostMapping
    @Operation(
        summary = "Criar um novo pacote",
        description = "Cria um novo pacote e retorna o objeto criado.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pacote criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public Pacote createPacote(@RequestBody Pacote pacote) {
        return pacoteService.createPacote(pacote);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Recuperar pacote por ID",
        description = "Obtém os detalhes de um pacote específico pelo seu ID.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "404", description = "Pacote não encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public Pacote getPacoteById(@PathVariable Long id) {
        return pacoteService.getPacoteById(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar um pacote",
        description = "Atualiza os detalhes de um pacote existente pelo seu ID.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pacote atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "404", description = "Pacote não encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {
        return pacoteService.updatePacote(id, pacoteDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Excluir um pacote",
        description = "Exclui um pacote pelo seu ID.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pacote excluído com sucesso",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Pacote não encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public void deletePacote(@PathVariable Long id) {
        pacoteService.deletePacote(id);
    }

	@GetMapping("/status/{status}")
    @Operation(
        summary = "Buscar pacotes por status",
        description = "Recupera uma lista de pacotes filtrados pelo status fornecido.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "404", description = "Pacotes não encontrados",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public List<Pacote> getPacotesByStatus(@PathVariable String status) {
        return pacoteService.buscarPacotesPorStatus(status);
    }

    @GetMapping("/destinatario/{destinatario}")
    @Operation(
        summary = "Buscar pacotes por destinatário",
        description = "Recupera uma lista de pacotes filtrados pelo destinatário fornecido.",
        tags = {"Pacote"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Pacote.class))),
        @ApiResponse(responseCode = "404", description = "Pacotes não encontrados",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public List<Pacote> getPacotesByDestinatario(@PathVariable String destinatario) {
        return pacoteService.buscarPacotesPorDestinatario(destinatario);
    }
}