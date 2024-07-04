package pdv.com.br.pdv.domain.service;

import pdv.com.br.pdv.domain.entity.Pedido;
import pdv.com.br.pdv.domain.enums.StatusPedido;
import pdv.com.br.pdv.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Long id);

    void atualizaStatus(Long id, StatusPedido statusPedido);

}
