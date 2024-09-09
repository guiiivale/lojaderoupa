package app.lojaDeRoupa.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.repository.FuncionarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve salvar um funcionário com sucesso")
    void deveSalvarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        String resultado = funcionarioService.save(funcionario);

        verify(funcionarioRepository, times(1)).save(funcionario);
        assertEquals("Funcionário cadastrado com sucesso.", resultado);
    }

    @Test
    @DisplayName("Deve atualizar um funcionário com sucesso")
    void deveAtualizarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Pedro");

        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        String resultado = funcionarioService.update(funcionario, 1L);

        verify(funcionarioRepository, times(1)).save(funcionario);
        assertEquals("Funcionário atualizado com sucesso", resultado);
    }

    @Test
    @DisplayName("Deve encontrar um funcionário por ID")
    void deveEncontrarFuncionarioPorId() {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(1L);
        funcionario.setNome("Carlos");

        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));

        Funcionario resultado = funcionarioService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Carlos", resultado.getNome());
    }

    @Test
    @DisplayName("Deve retornar null quando não encontrar o funcionário por ID")
    void deveRetornarNullSeFuncionarioNaoEncontrado() {
        when(funcionarioRepository.findById(1L)).thenReturn(Optional.empty());

        Funcionario resultado = funcionarioService.findById(1L);

        assertNull(resultado);
    }

    @Test
    @DisplayName("Deve retornar uma lista de funcionários")
    void deveRetornarListaDeFuncionarios() {
        Funcionario funcionario1 = new Funcionario();
        funcionario1.setNome("João");

        Funcionario funcionario2 = new Funcionario();
        funcionario2.setNome("Maria");

        when(funcionarioRepository.findAll()).thenReturn(Arrays.asList(funcionario1, funcionario2));

        List<Funcionario> resultado = funcionarioService.findAll();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("Deve deletar um funcionário com sucesso")
    void deveDeletarFuncionario() {
        String resultado = funcionarioService.delete(1L);

        verify(funcionarioRepository, times(1)).deleteById(1L);
        assertEquals("Funcionário deletado com sucesso!", resultado);
    }
}
