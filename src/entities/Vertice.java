
package entities;

import java.util.ArrayList;

public class Vertice {
    private Usuario dado;
    private ArrayList<Aresta> arestaEntrada;
    private ArrayList<Aresta> arestaSaida;

    
    public Vertice(Usuario valor){
        this.dado = valor;
        this.arestaEntrada = new ArrayList<Aresta>();
        this.arestaSaida = new ArrayList<Aresta>();

    }

    public Usuario getDado() {
        return dado;
    }

    public void setDado(Usuario dado) {
        this.dado = dado;
    }
    
    public void adicionarArestaEntrada(Aresta aresta){
        this.arestaEntrada.add(aresta);
    }
    
    public void removerArestaEntrada(Aresta aresta) {
    	for(int i = 0; i < arestaEntrada.size();i++) {
    		if(arestaEntrada.get(i).equals(aresta)) {
    			arestaEntrada.remove(i);
    		}
    	}
    }
    
        public void adicionarArestaSaida(Aresta aresta){
        this.arestaSaida.add(aresta);
    }
        
    public void removerArestaSaida(Aresta aresta) {
    	for(int i = 0; i < arestaSaida.size();i++) {
    		if(arestaSaida.get(i).equals(aresta)) {
    			arestaSaida.remove(i);
    		}
    	}
    }
    
    public ArrayList<Aresta> getArestaEntrada() {
        return arestaEntrada;
    }

    public ArrayList<Aresta> getArestaSaida() {
        return arestaSaida;
    }
    
}
