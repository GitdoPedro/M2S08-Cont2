package br.com.senai.exemplospringaula4.service;

import br.com.senai.exemplospringaula4.model.Conta;
import br.com.senai.exemplospringaula4.repository.ClienteRepository;
import br.com.senai.exemplospringaula4.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;
import br.com.senai.exemplospringaula4.service.ClienteService;

import java.util.List;
@Service
public class ContaService {

    private final ContaRepository repository;
    private ClienteRepository clienteRepository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public void criarConta(Integer id, Conta conta){
        try{
//            if (clienteRepository.encontrarClienteId(conta.getId_cliente()) != 0) {
//                return repository.salvarConta(conta);
//                return true;
//            }
//            return clienteRepository.encontrarClienteId(conta.getId_cliente());
            repository.salvarConta(id, conta);

//            return false;
        } catch (Exception e) {
            throw new ServerErrorException("ContaService", e);
        }
    }

    public List<Conta> pesquisarContas(){
        return repository.encontrarContas();
    }

    public Conta pesquisarContaPorId(Integer id){
        try{
            return repository.encontrarContaId(id);
        } catch (Exception e) {
            throw new ServerErrorException("ContaService", e);
        }
    }

    public boolean deletarContaPorId(Integer id){
        return repository.deletarContaId(id);
    }

    public void alterarContaPorId(Integer id, Conta conta){
        repository.atualizarContaId(id, conta);
    }

    public String sacar(Integer id, Double valor) {
        return repository.sacarDinheiroConta(id, valor);
    }

    public String depositar(Integer id, Double valor) {
        return repository.depositarDinheiroConta(id, valor);
    }

    public String transferir(Integer idContaEnvia, Integer idContaRecebe, Double valor) {

        try{
            Conta contaEnvia = repository.encontrarContaId(idContaEnvia);
            Conta contaRecebe = repository.encontrarContaId(idContaRecebe);

            if (contaEnvia.getSaldo() >= valor) {
                contaEnvia.setSaldo(contaEnvia.getSaldo() - valor);
                contaRecebe.setSaldo(contaRecebe.getSaldo() + valor);
                return "Transferência efetuada com sucesso";
            } else {
                return "Valor do saldo insuficiente para transferência";
            }
        } catch (IndexOutOfBoundsException erro) {
            return "Conta não encontrada";
        }
    }
}