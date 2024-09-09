package app.lojaDeRoupa.controller;

import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setup() {
        produtoRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve criar um produto com sucesso")
    void deveCriarProduto() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(100.0);

        ResponseEntity<String> response = restTemplate.postForEntity("/api/produto/save", produto, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto cadastrado com sucesso.", response.getBody());

        Produto produtoSalvo = produtoRepository.findAll().get(0);
        assertEquals("Camiseta", produtoSalvo.getNome());
        assertEquals(100.0, produtoSalvo.getPreco());
    }

    @Test
    @DisplayName("Deve atualizar um produto")
    void deveAtualizarProduto() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(100.0);
        produtoRepository.save(produto);

        produto.setNome("Camiseta Atualizada");
        produto.setPreco(150.0);

        ResponseEntity<String> response = restTemplate.exchange("/api/produto/update/{id}", HttpMethod.PUT, new HttpEntity<>(produto), String.class, produto.getIdProduto());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Atualizado com sucesso", response.getBody());
    }

    @Test
    @DisplayName("Deve buscar produto por ID")
    void deveBuscarProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(100.0);
        produtoRepository.save(produto);

        ResponseEntity<Produto> response = restTemplate.getForEntity("/api/produto/findById/{id}", Produto.class, produto.getIdProduto());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Camiseta", response.getBody().getNome());
    }

    @Test
    @DisplayName("Deve deletar um produto")
    void deveDeletarProduto() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setPreco(100.0);
        produtoRepository.save(produto);

        restTemplate.delete("/api/produto/delete/{id}", produto.getIdProduto());

        assertEquals(0, produtoRepository.findAll().size());
    }

    @Test
    @DisplayName("Deve buscar todos os produtos")
    void deveBuscarTodosOsProdutos() {
        Produto produto1 = new Produto();
        produto1.setNome("Camiseta");
        produto1.setPreco(100.0);
        produtoRepository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Calça");
        produto2.setPreco(200.0);
        produtoRepository.save(produto2);

        ResponseEntity<Produto[]> response = restTemplate.getForEntity("/api/produto/findAll", Produto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }

    @Test
    @DisplayName("Deve buscar top 10 produtos por preço")
    void deveBuscarTop10ProdutosPorPreco() {
        Produto produto1 = new Produto();
        produto1.setNome("Camiseta");
        produto1.setPreco(100.0);
        produtoRepository.save(produto1);

        Produto produto2 = new Produto();
        produto2.setNome("Calça");
        produto2.setPreco(200.0);
        produtoRepository.save(produto2);

        ResponseEntity<Produto[]> response = restTemplate.getForEntity("/api/produto/top10", Produto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }
}
