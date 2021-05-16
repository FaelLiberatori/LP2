import java.util.ArrayList;

interface IPegavel {
    void pegar (Object pegador);
    void arremessar (int velocidade);
    boolean largar ();
}

interface INadavel {
    void nadar (int velocidade);
    void afundar (int profundidade);
    void subir (int profundidade);
    boolean parar ();
}

///////////////////////////////

class Laranja implements IPegavel {

    void pegar (Object pegador) {
        // Pegador pega o objeto pegável.
    }

    void arremessar (int velocidade) {
        // Arremessar de acordo com a força.
    }

    boolean largar () {
        // Se conseguiu largar:
            return true;
        // Senão (ex: já estava largado):
            return false;
    }
}

class Pedra implements IPegavel {

    void pegar (Object pegador) {
        // Pegador pega o objeto pegável.
    }

    void arremessar (int velocidade) {
        // Arremessar de acordo com a força.
    }

    boolean largar () {
        // Se conseguiu largar:
            return true;
        // Senão (ex: já estava largado):
            return false;
    }
}

class Galho implements IPegavel {

    void pegar (Object pegador) {
        // Pegador pega o objeto pegável.
    }

    void arremessar (int velocidade) {
        // Arremessar de acordo com a força.
    }

    boolean largar () {
        // Se conseguiu largar:
            return true;
        // Senão (ex: já estava largado):
            return false;
    }
}

///////////////////////////////

class Humano implements INadavel {
    
    void nadar (int velocidade) {
        // Nadar de acordo com a velocidade.
    }

    void afundar (int profundidade) {
        // Afundar de acordo com a profundidade.
    }

    void subir (int profundidade) {
        // Subir de acordo com a profundidade.
    }

    boolean parar () {
        // Se parou de nadar:
            return true;
        // Senão (ex: já estava parado):
            return false;
    }
}

class Peixe implements INadavel {
    
    void nadar (int velocidade) {
        // Nadar de acordo com a velocidade.
    }

    void afundar (int profundidade) {
        // Afundar de acordo com a profundidade.
    }

    void subir (int profundidade) {
        // Subir de acordo com a profundidade.
    }

    boolean parar () {
        // Se parou de nadar:
            return true;
        // Senão (ex: já estava parado):
            return false;
    }
}

class Cachorro implements INadavel {
    
    void nadar (int velocidade) {
        // Nadar de acordo com a velocidade.
    }

    void afundar (int profundidade) {
        // Afundar de acordo com a profundidade.
    }

    void subir (int profundidade) {
        // Subir de acordo com a profundidade.
    }

    boolean parar () {
        // Se parou de nadar:
            return true;
        // Senão (ex: já estava parado):
            return false;
    }
}

public static void main(String [] args){ 
    ArrayList<IPegavel> pegaveis = {new Laranja(), new Pedra(), new Galho()};
    ArrayList<INadavel> nadaveis = {new Humano(), new Peixe(), new Cachorro()};

    // Fazer alguma coisa.    
}
