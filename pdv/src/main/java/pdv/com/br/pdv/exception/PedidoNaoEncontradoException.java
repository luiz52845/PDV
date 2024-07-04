package pdv.com.br.pdv.exception;

public class PedidoNaoEncontradoException extends RuntimeException{
    public PedidoNaoEncontradoException(){
        super("Pedido não encontrado.");
    }



}
