package app.lojaDeRoupa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lojaDeRoupa.entity.Cliente;
import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.entity.Venda;
import app.lojaDeRoupa.repository.VendaRepository;
import app.lojaDeRoupa.repository.ProdutoRepository;
import app.lojaDeRoupa.repository.ClienteRepository;
import app.lojaDeRoupa.repository.FuncionarioRepository;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;


    public String save(Venda venda) {
        double valorTotal = 0.0;

        for (Produto produto : venda.getProdutos()) {
            Produto produtoCompleto = produtoRepository.findById(produto.getIdProduto())
                                     .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            if (produtoCompleto.getPreco() != null) {
                valorTotal += produtoCompleto.getPreco();
            } else {
                throw new IllegalArgumentException("O produto com ID " + produto.getIdProduto() + " não possui um preço definido.");
            }
        }

        venda.setValorTotalVenda(valorTotal);

        Cliente cliente = venda.getCliente();
        if (cliente != null) {
            cliente = clienteRepository.findById(cliente.getIdCliente())
                        .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
            venda.setCliente(cliente);
        }

        Funcionario funcionario = venda.getFuncionario();
        if (funcionario != null) {
            funcionario = funcionarioRepository.findById(funcionario.getIdFuncionario())
                          .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
            venda.setFuncionario(funcionario);
        }

        vendaRepository.save(venda);
        return "Venda cadastrada com sucesso.";
    }


    public String update(Venda venda, long id) {
        venda.setIdVenda(id);

        double valorTotal = venda.getProdutos().stream()
                .mapToDouble(produto -> produto.getPreco())
                .sum();
        venda.setValorTotalVenda(valorTotal);

        this.vendaRepository.save(venda);
        return "Venda atualizada com sucesso";
    }

    public Venda findById(long id) {
        Optional<Venda> optional = this.vendaRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Venda> findAll() {
        return this.vendaRepository.findAll();
    }

    public String delete(long id) {
        this.vendaRepository.deleteById(id);
        return "Venda deletada com sucesso!";
    }
    
    public List<Venda> findByClienteNome(String nome) {
        return vendaRepository.findByClienteNomeContaining(nome);
    }

    public List<Venda> findByFuncionarioNome(String nome) {
        return vendaRepository.findByFuncionarioNomeContaining(nome);
    }

    public List<Venda> findTop10ByValorTotal() {
        return vendaRepository.findTop10ByOrderByValorTotalVendaDesc();
    }
}
