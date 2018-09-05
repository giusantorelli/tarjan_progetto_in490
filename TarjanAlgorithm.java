package tarjan_incrementale;

public class TarjanAlgorithm {
	
	public static void main(String[] args) {          //bisogna dare in input il numero di vertici del grafo da costruire
		int n = Integer.parseInt(args[0]);
		System.out.println("Creo un grafo con " +n+ " vertici");
		Graph g = new Graph(n);
		System.out.println("Avvio l'algoritmo di Tarjan");
		g.tarjan();
	
		g.print();
	}
	
}
