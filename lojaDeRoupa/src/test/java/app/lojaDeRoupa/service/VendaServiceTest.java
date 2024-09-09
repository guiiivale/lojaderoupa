package app.lojaDeRoupa.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import app.lojaDeRoupa.entity.Cliente;
import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.entity.Venda;
import app.lojaDeRoupa.repository.ClienteRepository;
import app.lojaDeRoupa.repository.FuncionarioRepository;
import app.lojaDeRoupa.repository.ProdutoRepository;
import app.lojaDeRoupa.repository.VendaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar uma venda com sucesso")
    void deveSalvarVenda() {
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        produto.setPreco(100.0);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setIdade(25);

        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(1L);

        Venda venda = new Venda();
        venda.setProdutos(Arrays.asList(produto));
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
        when(vendaRepository.save(any(Venda.class))).thenReturn(venda);

        String resultado = vendaService.save(venda);

        verify(vendaRepository, times(1)).save(venda);
        assertEquals("Venda cadastrada com sucesso.", resultado);
    }

    @Test
    @DisplayName("Deve lançar exceção se produto não encontrado")
    void deveLancarExcecaoSeProdutoNaoEncontrado() {
        Venda venda = new Venda();
        Produto produto = new Produto();
        produto.setIdProduto(1L);
        venda.setProdutos(Arrays.asList(produto));

        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            vendaService.save(venda);
        });

        assertEquals("Produto não encontrado", exception.getMessage());
    }

    @Test
    @DisplayName("Deve encontrar uma venda por ID")
    void deveEncontrarVendaPorId() {
        Venda venda = new Venda();
        venda.setIdVenda(1L);

        when(vendaRepository.findById(1L)).thenReturn(Optional.of(venda));

        Venda resultado = vendaService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdVenda());
    }

    @Test
    @DisplayName("Deve retornar null se venda não encontrada")
    void deveRetornarNullSeVendaNaoEncontrada() {
        when(vendaRepository.findById(1L)).thenReturn(Optional.empty());

        Venda resultado = vendaService.findById(1L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista de vendas")
    void deveRetornarListaDeVendas() {
        Venda venda1 = new Venda();
        Venda venda2 = new Venda();

        when(vendaRepository.findAll()).thenReturn(Arrays.asList(venda1, venda2));

        List<Venda> resultado = vendaService.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Deve deletar uma venda com sucesso")
    void deveDeletarVenda() {
        String resultado = vendaService.delete(1L);

        verify(vendaRepository, times(1)).deleteById(1L);
        assertEquals("Venda deletada com sucesso!", resultado);
    }
}
