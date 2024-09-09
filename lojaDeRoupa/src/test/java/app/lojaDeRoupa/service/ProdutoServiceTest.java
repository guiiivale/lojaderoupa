package app.lojaDeRoupa.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.repository.ProdutoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um produto com sucesso")
    void deveSalvarProduto() {
        Produto produto = new Produto();
        produto.setNome("Camiseta");

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        String resultado = produtoService.save(produto);

        verify(produtoRepository, times(1)).save(produto);
        assertEquals("Produto cadastrado com sucesso.", resultado);
    }

    @Test
    @DisplayName("Deve atualizar um produto com sucesso")
    void deveAtualizarProduto() {
        Produto produto = new Produto();
        produto.setNome("Camiseta Atualizada");

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        String resultado = produtoService.update(produto, 1L);

        verify(produtoRepository, times(1)).save(produto);
        assertEquals("Atualizado com sucesso", resultado);
    }

    @Test
    @DisplayName("Deve encontrar um produto por ID")
    void deveEncontrarProdutoPorId() {
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        produto.setNome("Calça");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Calça", resultado.getNome());
    }

    @Test
    @DisplayName("Deve retornar null quando não encontrar o produto por ID")
    void deveRetornarNullSeProdutoNaoEncontrado() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Produto resultado = produtoService.findById(1L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos")
    void deveRetornarListaDeProdutos() {
        Produto produto1 = new Produto();
        produto1.setNome("Camiseta");

        Produto produto2 = new Produto();
        produto2.setNome("Calça");

        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<Produto> resultado = produtoService.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Deve deletar um produto com sucesso")
    void deveDeletarProduto() {
        String resultado = produtoService.delete(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
        assertEquals("Produto deletado com sucesso!", resultado);
    }
}
