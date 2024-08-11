package app.lojaDeRoupa.repository;

import app.lojaDeRoupa.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByIdadeBetween(int idadeInicio, int idadeFim);
}
