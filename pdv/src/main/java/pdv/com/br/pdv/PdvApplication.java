package pdv.com.br.pdv;

import pdv.com.br.pdv.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PdvApplication {

//    @Bean
//    public CommandLineRunner init(@Autowired Clientes clientes){
//        return args->{
//
//            System.out.println("Salvando clientes");
//            clientes.save(new Cliente("birobiro"));
//        };
//
//    }


    public static void main(String[] args) {
        SpringApplication.run(PdvApplication.class, args);



    }

}
