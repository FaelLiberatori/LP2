public abstract class A {
    public abstract void Ação1 () {}
    public abstract void Ação2 () {}    
}

public class B extends A {

    public B {}

    public void Ação1 () {
        // Uma implementação para a ação 1.
    }
    public void Ação2 () {
        // Uma implementação para a ação 2.
    }    
}

public class C extends A {

    public C {}

    public void Ação1 () {
        // Outra implementação diferente para a ação 1.
    }
    public void Ação2 () {
        // Outra implementação diferente para a ação 2.
    }    
}

public class D extends B, C {
    public D {}

    // Neste caso, o programador não está interessado em fazer um override e sim em herdar a implementação dos métodos.
}

public class Main {
    public static void main(String[] args) {
        C c = new C();

        c.Ação1();
        c.Ação2();

        // O que aconteceria nesse cenário? Nós estamos chamando um método com duas implementações diferentes, ambas herdadas.
        // Aqui, temos um exemplo do problema do diamante.
    }
}
