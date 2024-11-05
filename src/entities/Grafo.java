package entities;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    
    public Grafo(){
        this.vertices = new ArrayList<Vertice>();
        this.arestas = new ArrayList<Aresta>();
    }
    
    public void adicionarVertice(Usuario dado){
        Vertice novoVertice = new Vertice(dado);
        this.vertices.add(novoVertice);
    }
    
    public void adicionarAresta(Usuario dadoInicio, Usuario dadoFim){
        Vertice inicio = this.getVertice(dadoInicio);
        Vertice fim = this.getVertice(dadoFim);
        Aresta aresta = new Aresta(inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }
    
    public Vertice getVertice(Usuario dado){
        Vertice vertice = null;
        for(int i=0; i<this.vertices.size(); i++){
            if(this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }
    
    public ArrayList<Aresta> getArestas() {
		return arestas;
	}

	public List<Vertice> buscaEmLargura(){
        ArrayList<Vertice> marcados = new ArrayList<Vertice>();
        ArrayList<Vertice> fila = new ArrayList<Vertice>();
        Vertice atual = this.vertices.get(0);
        marcados.add(atual);
        fila.add(atual);
        while(fila.size() > 0){
            Vertice visitado = fila.get(0);
            for(int i = 0; i < visitado.getArestaSaida().size(); i++){
                Vertice proximo = visitado.getArestaSaida().get(i).getFim();
                if(!marcados.contains(proximo)){
                    marcados.add(proximo);
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
        
        return marcados;
    }
	
	public List<Vertice> buscaEmLargura(Usuario u){
        ArrayList<Vertice> marcados = new ArrayList<Vertice>();
        ArrayList<Vertice> fila = new ArrayList<Vertice>();
        Vertice atual = null;
        
        for(Vertice v : this.vertices) {
        	if(v.getDado().equals(u)) {
        		atual = v;
        	}
        }

        marcados.add(atual);
        fila.add(atual);
        while(fila.size() > 0){
            Vertice visitado = fila.get(0);
            for(int i = 0; i < visitado.getArestaSaida().size(); i++){
                Vertice proximo = visitado.getArestaSaida().get(i).getFim();
                if(!marcados.contains(proximo)){
                    marcados.add(proximo);
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }
        
        return marcados;
    }

    public ArrayList<Vertice> getVertices(){
        return this.vertices;
    }
    
    public void removerVertice(Usuario alvo) {
    	for(int i  = 0; i < vertices.size(); i++) {
    		if(vertices.get(i).getDado().equals(alvo)) {
    			vertices.remove(i);
    		}
    	}
    }
    
    public void removerAresta(Usuario alvo, Usuario mira) {
    	Vertice inicio = this.getVertice(alvo);
        Vertice fim = this.getVertice(mira);
        
        Aresta aresta = new Aresta(inicio, fim);
        inicio.removerArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        
        for(int i = 0; i < arestas.size(); i++) {
        	if(arestas.get(i).getFim() == aresta.getFim() && arestas.get(i).getInicio() == aresta.getInicio()) {
        		arestas.remove(i);
        	}
        }
        
        for(int i = 0; i < inicio.getArestaSaida().size(); i++) {
        	if(inicio.getArestaSaida().get(i).getFim().equals(fim)) {
        		inicio.getArestaSaida().remove(i);
        	}
        	
        }
        
        for(int i = 0; i < inicio.getArestaEntrada().size(); i++) {
        	if(inicio.getArestaEntrada().get(i).getInicio().equals(fim)) {
        		inicio.getArestaEntrada().remove(i);
        	}
        	
        }
        
        for(int i = 0; i < fim.getArestaSaida().size(); i++) {
        	if(fim.getArestaSaida().get(i).getFim().equals(inicio)) {
        		fim.getArestaSaida().remove(i);
        	}
        	
        }
        
        for(int i = 0; i < fim.getArestaEntrada().size(); i++) {
        	if(fim.getArestaEntrada().get(i).getInicio().equals(inicio)) {
        		fim.getArestaEntrada().remove(i);
        	}
        	
        }
    }
}
