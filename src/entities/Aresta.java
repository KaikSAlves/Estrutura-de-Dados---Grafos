
package entities;

public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    
    public Aresta(Vertice inicio, Vertice fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }
    
    
}
