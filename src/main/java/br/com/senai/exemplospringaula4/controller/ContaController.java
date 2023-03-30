package br.com.senai.exemplospringaula4.controller;

import br.com.senai.exemplospringaula4.model.Conta;
import br.com.senai.exemplospringaula4.service.ContaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/conta")
@RestController
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }


    @GetMapping
    public List<Conta> listarConta() {
        return service.pesquisarContas();
    }

    @GetMapping(params = "id")
    public Conta listarContaId(
            @RequestParam Integer id
    ) {
        return service.pesquisarContaPorId(id);
    }


    @PostMapping
    public void criarConta(
            @RequestParam Integer id,
            @RequestBody
            @Validated
            Conta conta
    ) {
        service.criarConta(id, conta);
    }

    @DeleteMapping("/{id}")
    public boolean listarConta(@PathVariable Integer id) {
        return service.deletarContaPorId(id);
    }

    @PutMapping()
    public void atualizarConta(
            @RequestParam Integer id,
            @RequestBody @Validated Conta conta
    ) {
        service.alterarContaPorId(id, conta);
    }

    @PutMapping("/saque")
    public String saque(
            @RequestParam Integer id,
//            @RequestBody @Validated Conta conta,
            @RequestParam Double valor
    ) {
        return service.sacar(id, valor);
    }

    @PutMapping("/deposito")
    public String deposito(
            @RequestParam Integer id,
//            @RequestBody @Validated Conta conta,
            @RequestParam Double valor
    ) {

        return service.depositar(id, valor);
    }

    @PutMapping("/transfere")
    public String transfere(
            @RequestParam Integer idContaEnvia,
            @RequestParam Integer idContaRecebe,
//            @RequestBody @Validated Conta conta,
            @RequestParam Double valor
    ) {

        return service.transferir(idContaEnvia, idContaRecebe, valor);
    }
}