package pdv.com.br.pdv.rest.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {
    private Long produto;
    private Long quantidade;
}
