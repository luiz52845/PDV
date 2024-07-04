package pdv.com.br.pdv.rest.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pdv.com.br.pdv.domain.entity.ItemPedido;
import pdv.com.br.pdv.domain.entity.Pedido;
import pdv.com.br.pdv.domain.enums.StatusPedido;
import pdv.com.br.pdv.domain.service.PedidoService;
import pdv.com.br.pdv.rest.dto.AtualizacaoStatusPedidoDTO;
import pdv.com.br.pdv.rest.dto.InformacaoItemPedidoDTO;
import pdv.com.br.pdv.rest.dto.InformacaoesPedidoDTO;
import pdv.com.br.pdv.rest.dto.PedidoDTO;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long save(@Valid  @RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacaoesPedidoDTO getById(@PathVariable Long id) {
        return pedidoService.obterPedidoCompleto(id)
                .map(p -> converter(p)

                )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));


    }

    private InformacaoesPedidoDTO converter(Pedido pedido) {
        return InformacaoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }






}
