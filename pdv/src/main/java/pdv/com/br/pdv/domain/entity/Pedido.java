package pdv.com.br.pdv.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdv.com.br.pdv.domain.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate dataPedido;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


}
