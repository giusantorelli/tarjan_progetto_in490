package tarjan_incrementale;

import java.util.LinkedList;

public class Dfs {

	private Vertex root1,root2;    //i vertici da cui parte la ricerca
	Graph graph;
	LinkedList<Vertex> component;  //la componente trovata alla fine della ricerca
	LinkedList<Vertex> visited;    //i vertici già visitati
	
	//getters e setters
	
	synchronized void set1(Vertex v) {
		this.root1 = v;
	}
	
	synchronized void set2(Vertex v) {
		this.root2 = v;
	}
	
	synchronized Vertex get1() {
		return this.root1;
	}
	
	synchronized Vertex get2() {
		return this.root2;
	}
	
	//costruttore
	public Dfs(Graph g, Vertex v, Vertex w) {
		this.graph = g;
		this.set1(v);
		this.set2(w);
		this.component = new LinkedList<Vertex>();
		this.visited = new LinkedList<Vertex>();
	}
	
	//metodo di ricerca della componente connessa
	LinkedList<Vertex> search(){
		this.get1().setBw(true); //il primo vertice dell'arco è backward
		this.get2().setFw(true); //il secondo vertice è forward
		this.visited.add(get1());//li aggiungo alla lista dei vertici visitati
		this.visited.add(get2());
		Thread t1 = new Thread(new Bsearch(this)); //creo e avvio un thread per la ricerca all'indietro a partire dal primo vertice
		Thread t2 = new Thread(new Fsearch(this)); //creo e avvio un thread per la ricerca in avanti a partire dal secondo vertice
		t1.start();
		System.out.println("Avvio il primo thread");
		t2.start();
		System.out.println("Avvio il secondo thread");
		while(t1.isAlive() || t2.isAlive()) { //il metodo non prosegue fintanto che i due thread sono attivi
			
		}
		System.out.println("Ricerca terminata");
		System.out.println("Ho trovato la seguente componente: ");
		for(int i=0; i<this.component.size(); i++) {
			System.out.print(this.component.get(i).getTag()+" ");
		}
		System.out.println("");
		return this.component;
	}
}
