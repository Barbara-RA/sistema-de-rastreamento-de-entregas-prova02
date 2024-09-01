package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @Operation(
        summary = "Listar todos os endereços",
        description = "Recupera uma lista de todos os endereços cadastrados.",
        tags = {"Endereço"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public List<EnderecoDTO> getAllEnderecos() {
        System.out.println("-----------------------------------------");
        return enderecoService.getAllEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Recuperar endereço por ID",
        description = "Obtém os detalhes de um endereço específico pelo seu ID.",
        tags = {"Endereço"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Encontrado",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        EnderecoDTO endereco = enderecoService.getEnderecoById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(endereco);
    }

    @PostMapping
    @Operation(
        summary = "Criar um novo endereço",
        description = "Cria um novo endereço e retorna o objeto criado.",
        tags = {"Endereço"}
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = EnderecoDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor",
            content = @Content)
    })
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO novoRecursEnderecoDTO = enderecoService.createEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRecursEnderecoDTO);
    }
}
