package util;

import java.util.ArrayList;
import java.util.List;
import entities.Aresta;
import entities.Grafo;
import entities.Usuario;
import entities.Vertice;

public class Rede {
	private Grafo grafo;
	
	public Rede(Grafo grafo) {
		this.grafo = grafo;
	}

	public Grafo getGrafo() {
		return grafo;
	}

	public void setGrafo(Grafo grafo) {
		this.grafo = grafo;
	}
	
	public List<Usuario> getPessoasSeguidas(Usuario user){
		
		List<Usuario> pessoasSeguidas = new ArrayList<Usuario>();
		Vertice verticeUser = grafo.getVertice(user);
		
		if(user != null) {
			for(Aresta a : verticeUser.getArestaEntrada()) {
				
				pessoasSeguidas.add(a.getInicio().getDado());
			}
			
			for(Aresta a : verticeUser.getArestaSaida()) {
				pessoasSeguidas.add(a.getFim().getDado());
			}
		}
		
		return pessoasSeguidas;
		
	}
	
	public List<Usuario> getUsuariosRecomendados(Usuario user){
		if(user != null) {
			
			List<Vertice> busca = grafo.buscaEmLargura();

			List<Usuario> usuarioSeguidos = getPessoasSeguidas(user);
			List<Usuario> usuariosRecomendados = new ArrayList<Usuario>();
			
			Vertice verticeUsuarioAtual = grafo.getVertice(user);
			
			for(Vertice b : busca) {
				if (!usuarioSeguidos.contains(b.getDado()) && !b.getDado().getNome().equals(user.getNome())) {
					usuariosRecomendados.add((b.getDado()));
				}
			}

			return usuariosRecomendados;
		}else {
			return null;
		}
		
	}
	
	public void seguir(Usuario alvo, Usuario mira) {
		grafo.adicionarAresta(mira, alvo);
	}
	
	public void deixarSeguir(Usuario alvo, Usuario mira) {
		grafo.removerAresta(alvo, mira);
	}
}
