package pdv.com.br.pdv.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "Campo CPF é obrigatorio.")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    public Cliente(String nome) {
        this.nome = nome;
    }
}
