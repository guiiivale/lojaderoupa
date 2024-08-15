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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    private Long idVenda;
    
    @NotBlank(message = "A observação não pode ser vazia.")
    private String obs;
    private Double valorTotalVenda;
    
    @NotNull(message = "A venda precisa ter um cliente.")
    @ManyToOne
    @JsonIgnoreProperties("vendas")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("vendas")
    private Funcionario funcionario;
    
    @NotEmpty(message = "A venda precisa ter no mínimo um produto.")
    @ManyToMany
    @JoinTable(name="vendaProduto")
    @JsonIgnoreProperties("vendas")
    private List<Produto> produtos;
    
}
