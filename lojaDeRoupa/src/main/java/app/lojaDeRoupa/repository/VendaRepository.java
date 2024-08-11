package app.lojaDeRoupa.repository;

import app.lojaDeRoupa.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	List<Venda> findByClienteNomeContaining(String nome);
    List<Venda> findByFuncionarioNomeContaining(String nome);
    List<Venda> findTop10ByOrderByValorTotalVendaDesc();
}
