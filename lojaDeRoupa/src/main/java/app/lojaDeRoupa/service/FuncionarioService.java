package app.lojaDeRoupa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lojaDeRoupa.entity.Funcionario;
import app.lojaDeRoupa.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario) {
        this.funcionarioRepository.save(funcionario);
        return "Funcionário cadastrado com sucesso.";
    }

    public String update(Funcionario funcionario, long id) {
        funcionario.setIdFuncionario(id);
        this.funcionarioRepository.save(funcionario);
        return "Funcionário atualizado com sucesso";
    }

    public Funcionario findById(long id) {
        Optional<Funcionario> optional = this.funcionarioRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Funcionario> findAll() {
        return this.funcionarioRepository.findAll();
    }

    public String delete(long id) {
        this.funcionarioRepository.deleteById(id);
        return "Funcionário deletado com sucesso!";
    }
}
