package app.lojaDeRoupa.controller;

import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.repository.FuncionarioRepository;
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
public class FuncionarioControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setup() {
        funcionarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um funcionário com sucesso")
    void deveCriarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Pedro Silva");
        funcionario.setIdade(30);
        funcionario.setEmail("pedro@gmail.com");
        funcionario.setTelefone("(11) 99999-9999");
        funcionario.setEndereco("54321-987");
        funcionario.setCpf("321.654.987-00");
        funcionario.setFuncao("Vendedor");

        ResponseEntity<String> response = restTemplate.postForEntity("/api/funcionario/save", funcionario, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Funcionário cadastrado com sucesso.", response.getBody());

        Funcionario funcionarioSalvo = funcionarioRepository.findAll().get(0);
        assertEquals("Pedro Silva", funcionarioSalvo.getNome());
    }

    @Test
    @DisplayName("Deve buscar todos os funcionários")
    void deveBuscarTodosOsFuncionarios() {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNome("Pedro Silva");
        funcionario1.setIdade(30);
        funcionario1.setEmail("pedro@gmail.com");
        funcionario1.setTelefone("(11) 99999-9999");
        funcionario1.setEndereco("54321-987");
        funcionario1.setCpf("321.654.987-00");
        funcionario1.setFuncao("Vendedor");
        funcionarioRepository.save(funcionario1);

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setNome("Ana Souza");
        funcionario2.setIdade(28);
        funcionario2.setEmail("ana@gmail.com");
        funcionario2.setTelefone("(22) 88888-8888");
        funcionario2.setEndereco("98765-432");
        funcionario2.setCpf("654.987.321-00");
        funcionario2.setFuncao("Caixa");
        funcionarioRepository.save(funcionario2);

        ResponseEntity<Funcionario[]> response = restTemplate.getForEntity("/api/funcionario/findAll", Funcionario[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve deletar um funcionário")
    void deveDeletarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Pedro Silva");
        funcionario.setIdade(30);
        funcionario.setEmail("pedro@gmail.com");
        funcionario.setTelefone("(11) 99999-9999");
        funcionario.setEndereco("54321-987");
        funcionario.setCpf("321.654.987-00");
        funcionario.setFuncao("Vendedor");
        funcionarioRepository.save(funcionario);

        restTemplate.delete("/api/funcionario/delete/{id}", funcionario.getIdFuncionario());

        assertEquals(0, funcionarioRepository.findAll().size());
    }
}
