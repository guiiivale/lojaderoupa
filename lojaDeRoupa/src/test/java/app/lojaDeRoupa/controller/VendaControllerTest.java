package app.lojaDeRoupa.controller;

import app.lojaDeRoupa.entity.Cliente;
import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.entity.Venda;
import app.lojaDeRoupa.repository.VendaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendaControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private VendaRepository vendaRepository;

    @BeforeEach
    void setup() {
        vendaRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar uma venda com sucesso")
    void deveCriarVenda() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(100.0);

        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setIdade(25);
        cliente.setEmail("joao@gmail.com");
        cliente.setTelefone("(11) 99999-9999");
        cliente.setEndereco("12345-123");
        cliente.setCpf("123.456.789-00");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Pedro Souza");
        funcionario.setIdade(30);
        funcionario.setEmail("pedro@gmail.com");
        funcionario.setTelefone("(11) 99999-9999");
        funcionario.setEndereco("54321-987");
        funcionario.setCpf("321.654.987-00");
        funcionario.setFuncao("Vendedor");

        Venda venda = new Venda();
        venda.setProdutos(Arrays.asList(produto));
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setObs("Compra normal");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/venda/save", venda, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venda cadastrada com sucesso.", response.getBody());

        Venda vendaSalva = vendaRepository.findAll().get(0);
        assertEquals(100.0, vendaSalva.getValorTotalVenda());
    }
}
