package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.service.RastreamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/rastreamentos")
public class RastreamentoController {

    @Autowired
    private RastreamentoService rastreamentoService;

    @GetMapping("/{id}")
    @Operation(
        summary = "Recuperar rastreamentos de um pacote",
        description = "Obtém os detalhes de rastreamento de um pacote específico pelo seu ID.",
        tags = {"Rastreamento"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Rastreamento.class))),
        @ApiResponse(responseCode = "404", description = "Rastreamento não encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public List<Rastreamento> getRastreamentosPacote(@PathVariable Long id) {
        return rastreamentoService.getRastreamentos(id);
    }
}
