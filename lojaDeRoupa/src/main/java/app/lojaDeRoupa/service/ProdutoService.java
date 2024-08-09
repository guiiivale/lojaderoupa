package app.lojaDeRoupa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lojaDeRoupa.entity.Produto;
import app.lojaDeRoupa.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public String save (Produto produto) {
		this.produtoRepository.save(produto);
		return "Carro cadastrado com sucesso";
	}
	
	public String update (Produto produto, long id) {
		produto.setIdProduto(id);
		this.produtoRepository.save(produto);
		return "Atualizado com sucesso";
	}
	
	public Produto findById (long id) {
		
		Optional<Produto> optional = this.produtoRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else
			return null;
		
	}
	
	public List<Produto> findAll () {
		
		return this.produtoRepository.findAll();
		
	}
	
	public String delete (long id) {
		this.produtoRepository.deleteById(id);
		return "Veículo deletado com sucesso!";
	}

}
