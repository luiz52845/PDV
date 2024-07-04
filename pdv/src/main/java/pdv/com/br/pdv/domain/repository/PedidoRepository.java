package pdv.com.br.pdv.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pdv.com.br.pdv.domain.entity.Cliente;
import pdv.com.br.pdv.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itens where p.id =:id ")
    Optional<Pedido> findByIdFetchitens(@Param("id") Long id);
}
