package tarjan_incrementale;

import java.util.LinkedList;

public class Graph {

	private int numvertexes;                      //numero di vertici del grafo
	private int numedges;                         //numero di archi del grafo
	LinkedList<Vertex> order;                     //vertci del grafo
	LinkedList<LinkedList<Vertex>> sccomponents;  //componenti fortemente connesse
	
	//getters e setters
	
	void setNvert(int n) {
		this.numvertexes = n;
	}
	
	void setNedges(int n) {
		this.numedges = n;
	}
	
	int getNvert() {
		return this.numvertexes;
	}
	
	int getNedges() {
		return this.numedges;
	}

	//costruttore di un grafo di n vertici isolati
	public Graph(int n) {
		this.setNvert(n);
		this.order = new LinkedList<Vertex>();
		this.sccomponents = new LinkedList<LinkedList<Vertex>>();
		int i = 0;
		while(i<n) {
			System.out.println("Creo il vertice " +i);
			Vertex v = new Vertex(i);
			System.out.println("Lo aggiungo alla lista");
			this.order.add(v);
			System.out.println("Passo al successivo");
			i = i+1;
		}
	}
	
	//seleziona un vertice casuale del grafo
	Vertex randomV() {
		int n = (int) (Math.random()*this.getNvert());
		return this.order.get(n);
	}
			
	//aggiunge un arco
	void addEdge(Vertex v, Vertex w) {
		v.next.add(w);
		w.prev.add(v);
	}
		
	//verifica l'esistenza di un arco
	boolean edge(Vertex v, Vertex w) {
		if(v.next.contains(w) && w.prev.contains(v)) {
			return true;
		}
		return false;
	}
			
	//azzera tutte le informazioni riguardanti i vertici (è necessario ogni qual volta riavvio una ricerca)
	void reset() {
		for(int i=0; i<this.order.size(); i++) {
			Vertex v = this.order.get(i);
			v.setBw(false);
			v.setFw(false);
			v.setSc(false);
			v.setFposition(0);
			v.setBposition(0);
		}
	}
		
	//confronta una componente gia nota con quella appena trovata 
	void compare(LinkedList<Vertex> list) {
		for(int i=0; i<this.sccomponents.size(); i++) {
			for(int j=0; j<this.sccomponents.get(i).size(); j++) {
				if(list.contains(this.sccomponents.get(i).get(j))) {
					this.merge(list,this.sccomponents.get(i));
					System.out.println("Ho aggiornato una componente già nota");
					return;
				}
			}
		}
		this.sccomponents.add(list);
		System.out.println("Ho trovato una nuova componente");
	}
	
	//unisce due componenti che in realtà ne sono una unica
	void merge(LinkedList<Vertex> list1, LinkedList<Vertex> list2) {
		for(int i=0; i<list1.size(); i++) {
			if(!list2.contains(list1.get(i))) {
				list2.add(list1.get(i));
			}
		}
	}
	
	//stampa le componenti connesse del grafo
	void print() {
		for(int i=0; i<this.sccomponents.size(); i++) {
			System.out.println("La componente numero " +(i+1)+ " è composta da " +this.sccomponents.get(i).size()+ " vertici: ");
			for(int j=0; j<this.sccomponents.get(i).size(); j++) {
				System.out.print(this.sccomponents.get(i).get(j).getTag()+" ");
			}
			System.out.println(" ");
		}
	}
	
	//avvia la ricerca ogni volta che viene aggiunto un nuovo arco
	void tarjan() {
		LinkedList<Vertex> list = new LinkedList<Vertex>();
		for(int i=0; i<2*(this.getNvert()-1); i++) {//itero in modo da avere in media pochi archi per vertice,
			Vertex v = this.randomV();              // per non avere un grafo troppo denso, al massimo posso avere N(N-1)/2 archi (grafo diretto completo)
			Vertex w = this.randomV();
			if((v!=w) && !this.edge(v,w)) { //avvio effettivamente la ricerca solo se è un arco nuovo e non è un cappio
				this.addEdge(v,w);
				this.setNedges(this.getNedges()+1);
				System.out.println("La ricerca parte dai vertici " +v.getTag()+ " " +w.getTag());
				Dfs dfs = new Dfs(this,v,w);
				list = dfs.search();
				if(!list.isEmpty()) {
					this.compare(list);
				}
				this.reset();
			}
		}
	}
}
