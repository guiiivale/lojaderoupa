package app.lojaDeRoupa.controller;

import app.lojaDeRoupa.entity.Cliente;
import app.lojaDeRoupa.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um cliente com sucesso")
    void deveCriarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setIdade(25);
        cliente.setEmail("joao@gmail.com");
        cliente.setTelefone("(11) 99999-9999");
        cliente.setEndereco("12345-123");
        cliente.setCpf("123.456.789-00");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/cliente/save", cliente, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cliente cadastrado com sucesso.", response.getBody());

        Cliente clienteSalvo = clienteRepository.findAll().get(0);
        assertEquals("João Silva", clienteSalvo.getNome());
    }

    @Test
    @DisplayName("Deve buscar todos os clientes")
    void deveBuscarTodosOsClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João Silva");
        cliente1.setIdade(25);
        cliente1.setEmail("joao@gmail.com");
        cliente1.setTelefone("(11) 99999-9999");
        cliente1.setEndereco("12345-123");
        cliente1.setCpf("123.456.789-00");
        clienteRepository.save(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria Souza");
        cliente2.setIdade(30);
        cliente2.setEmail("maria@gmail.com");
        cliente2.setTelefone("(22) 88888-8888");
        cliente2.setEndereco("98765-432");
        cliente2.setCpf("987.654.321-00");
        clienteRepository.save(cliente2);

        ResponseEntity<Cliente[]> response = restTemplate.getForEntity("/api/cliente/findAll", Cliente[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve deletar um cliente")
    void deveDeletarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Silva");
        cliente.setIdade(25);
        cliente.setEmail("joao@gmail.com");
        cliente.setTelefone("(11) 99999-9999");
        cliente.setEndereco("12345-123");
        cliente.setCpf("123.456.789-00");
        clienteRepository.save(cliente);

        restTemplate.delete("/api/cliente/delete/{id}", cliente.getIdCliente());

        assertEquals(0, clienteRepository.findAll().size());
    }
}
