package app.lojaDeRoupa.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;
    
    @NotBlank(message = "O nome não pode ser vazio.")
    @Pattern(regexp = "^[\\p{L}]+(?:\\s+[\\p{L}]+)+$", message = "O nome deve conter pelo menos duas palavras.")
    private String nome;
    
    @Email(message = "O e-mail deve ser válido.")
    private String email;

    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "O telefone deve seguir o padrão: (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;
    
    @Min(value = 0, message = "A idade não pode ser negativa.")
    private int idade;
    
    @NotBlank(message = "O endereço não pode ser vazio.")
    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "O CEP deve seguir o padrão brasileiro (XXXXX-XXX).")
    private String endereco;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve ser válido e seguir o padrão XXX.XXX.XXX-XX.")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Venda> vendas;
}
