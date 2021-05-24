public abstract class Atleta {
    public abstract void agir () {}
    public abstract void parar () {}    
}

public class Corredor extends Atleta {

    public Corredor {}

    public void agir () {
        // Uma implementação para a ação agir.
    }
    public void parar () {
        // Uma implementação para a ação parar.
    }    
}

public class Nadador extends Atleta {

    public Nadador {}

    public void agir () {
        // Outra implementação diferente para a ação agir.
    }
    public void parar () {
        // Outra implementação diferente para a ação parar.
    }    
}

public class Ciclista extends Atleta {

    public Ciclista {}

    public void agir () {
        // Outra implementação diferente para a ação agir.
    }
    public void parar () {
        // Outra implementação diferente para a ação parar.
    }    
}

public class Triatleta extends Corredor, Nadador, Ciclista {
    // Aqui temos um atleta que disputa a provas de triatlo, por tanto, ele é, ao mesmo tempo, um corredor, nadador e um ciclista.

    public Triatleta {}

    // Neste caso, o programador não está interessado em fazer um override e sim em herdar a implementação dos métodos.
}

public class Triatlo {
    public static void main(String[] args) {
        Triatleta triatleta = new Triatleta();

        // Prova de corria
        triatleta.agir();
        triatleta.parar();

        // Prova de natação
        triatleta.agir();
        triatleta.parar();

        // Prova de ciclismo
        triatleta.agir();
        triatleta.parar();

        // O que aconteceria nesse cenário? Nós estamos chamando um método com duas implementações diferentes, ambas herdadas.
        // Aqui, temos um exemplo do problema do diamante.
    }
}
