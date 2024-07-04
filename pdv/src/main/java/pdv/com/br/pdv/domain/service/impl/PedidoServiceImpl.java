package pdv.com.br.pdv.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pdv.com.br.pdv.domain.entity.Cliente;
import pdv.com.br.pdv.domain.entity.ItemPedido;
import pdv.com.br.pdv.domain.entity.Pedido;
import pdv.com.br.pdv.domain.entity.Produto;
import pdv.com.br.pdv.domain.enums.StatusPedido;
import pdv.com.br.pdv.domain.repository.ClienteRepository;
import pdv.com.br.pdv.domain.repository.ItemPedidoRepository;
import pdv.com.br.pdv.domain.repository.PedidoRepository;
import pdv.com.br.pdv.domain.repository.ProdutoRepository;
import pdv.com.br.pdv.domain.service.PedidoService;
import pdv.com.br.pdv.exception.PedidoNaoEncontradoException;
import pdv.com.br.pdv.exception.RegranegocioException;
import pdv.com.br.pdv.rest.dto.ItemPedidoDTO;
import pdv.com.br.pdv.rest.dto.PedidoDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidosRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtosRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Long idCliente = dto.getCliente();
        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegranegocioException("Código cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Long id) {
        return pedidosRepository.findByIdFetchitens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Long id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map( pedido -> {
                  pedido.setStatus(statusPedido);
                  return pedidosRepository.save(pedido);
                }).orElseThrow(()-> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegranegocioException("não é possivel realizar um pedido sem items.");
        }
        return items
                .stream()
                .map(dto -> {
                    Long idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegranegocioException("Código do produto inválido: " + idProduto));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;

                }).collect(Collectors.toList());

    }

}