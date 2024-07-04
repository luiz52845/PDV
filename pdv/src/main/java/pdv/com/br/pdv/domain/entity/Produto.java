package pdv.com.br.pdv.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Campo descrição é obrigatório.")
    private String descricao;
    @NotNull(message = "preço Obrigatório")
    private BigDecimal preco;


}
