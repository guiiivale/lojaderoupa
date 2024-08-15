package app.lojaDeRoupa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduto;
    
    @NotBlank(message = "O nome não pode ser vazio.")
    private String nome;
    private String descricao;
    private Double preco;
    
    @ManyToMany(mappedBy = "produtos")
    @JsonIgnoreProperties("produtos")
    private List<Venda> vendas;
    
}
