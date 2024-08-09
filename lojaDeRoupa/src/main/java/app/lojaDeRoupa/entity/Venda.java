package app.lojaDeRoupa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenda;
	
	private String obs;
	private Double valorTotalVenda;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("vendas")
	private Cliente cliente;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties("vendas")
	private Funcionario funcionario;
	
	@ManyToMany
	@JoinTable(name="vendaProduto")
	@JsonIgnoreProperties("vendas")
	private List<Produto> produtos;
	
}
