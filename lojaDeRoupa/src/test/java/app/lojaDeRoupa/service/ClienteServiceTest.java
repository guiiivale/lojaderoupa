package app.lojaDeRoupa.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import app.lojaDeRoupa.entity.Cliente;
import app.lojaDeRoupa.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um cliente com sucesso")
    void deveSalvarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        String resultado = clienteService.save(cliente);

        verify(clienteRepository, times(1)).save(cliente);
        assertEquals("Cliente cadastrado com sucesso.", resultado);
    }

    @Test
    @DisplayName("Deve atualizar um cliente com sucesso")
    void deveAtualizarCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("Maria");

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        String resultado = clienteService.update(cliente, 1L);

        verify(clienteRepository, times(1)).save(cliente);
        assertEquals("Cliente atualizado com sucesso", resultado);
    }

    @Test
    @DisplayName("Deve encontrar um cliente por ID")
    void deveEncontrarClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1L);
        cliente.setNome("Carlos");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNome());
    }

    @Test
    @DisplayName("Deve retornar null quando não encontrar o cliente por ID")
    void deveRetornarNullSeClienteNaoEncontrado() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.findById(1L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes")
    void deveRetornarListaDeClientes() {
        Cliente cliente1 = new Cliente();
        cliente1.setNome("João");

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Maria");

        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente1, cliente2));

        List<Cliente> resultado = clienteService.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Deve deletar um cliente com sucesso")
    void deveDeletarCliente() {
        String resultado = clienteService.delete(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
        assertEquals("Cliente deletado com sucesso!", resultado);
    }
}
