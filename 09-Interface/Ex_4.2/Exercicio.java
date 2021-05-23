import java.util.ArrayList;

public interface ICadastrável {
    public void cadastrar();
    public void atualizar();    
    public void excluir();
}

////////////////////////////

public abstract class Cliente implements ICadastrável{
    public string nome;
    public string endereço;
    public ArrayList<Pedido> históricoPedidos;

    public Cliente (string nome, string endereço) {
        this.nome = nome;
        this.endereço = endereço;
        históricoPedidos = new ArrayList<Pedido>();
    }

    // Suas subclasses implementariam o cadastrável
}

/////////////////////////////

public class Pedido {
    // Esboço não relevante para o exercício.
} 
