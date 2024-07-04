package pdv.com.br.pdv.domain.repository;

import pdv.com.br.pdv.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //List<Cliente> findByNomeLike(String nome);
    // List<Cliente> findByNomeOrIdOrderById(String nome, Long id);

    // @Query(value = " select c from cliente c where c.nome like :nome ")
    //  List<Cliente> buscarPorNome(@Param("nome") String nome);

}
