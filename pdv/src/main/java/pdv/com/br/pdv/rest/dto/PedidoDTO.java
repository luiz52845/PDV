package pdv.com.br.pdv.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdv.com.br.pdv.validation.NotEmptyList;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente.")
    private Long cliente;
    @NotNull(message = "Campo Total é obrigatório.")
    private BigDecimal total;
    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> items;
}
