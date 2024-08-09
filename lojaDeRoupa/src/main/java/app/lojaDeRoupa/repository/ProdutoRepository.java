package app.lojaDeRoupa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.lojaDeRoupa.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
