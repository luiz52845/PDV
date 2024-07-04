package pdv.com.br.pdv.domain.repository;

import org.springframework.stereotype.Repository;
import pdv.com.br.pdv.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
