package app.lojaDeRoupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.lojaDeRoupa.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}

