package tarjan_incrementale;

public class Bsearch implements Runnable {

	Dfs dfs;

	//costruttore
	public Bsearch(Dfs d) {
		this.dfs = d;
	}
	
	//metodo per la ricerca all'indietro
	void dfssearch(Vertex v) {
		if(v.prev.isEmpty()) { //se non ho alcun vertice da visitare mi fermo
			return;
		}
		Vertex w = v.prev.get(v.getBposition());   //primo vertice non visitato
		v.setBposition(v.getBposition()+1);		   //incremento il contatore
		if(v.getBposition() == v.prev.size()) {    //se sono stati visitati tutti gli archi entranti di v allora è scanned
			v.setSc(true);
		}
		if(w.prev.isEmpty() && !v.getSc()) { //se da w non posso visitare nulla allora proseguo la visita di v
			this.dfssearch(v);
		}
		if(w.getFw()) {  //espone il ciclo con tutti i vertici bw e i fw trovati prima di w
			if(!dfs.component.contains(w)) {
				dfs.component.add(w);
			}
			for(int i=0; i<dfs.visited.size(); i++) {
				if((dfs.visited.get(i).getBw() || i<dfs.visited.indexOf(w)) && !dfs.component.contains(dfs.visited.get(i))) {
					dfs.component.add(dfs.visited.get(i));
				}
			}
			return;
		}
		if(w.getBw()) {       //lo visito se non è scanned altrimenti visito il successivo, se sono tutti scanned mi fermo
			if(!w.getSc()) {
				this.dfssearch(w);
			}
			else {
				if(!v.getSc()) {
					this.dfssearch(v);
				}
				else {
					dfs.component.add(w);
					for(int i=0; i<dfs.visited.size(); i++) {
						if(dfs.visited.get(i).getBw() && i>dfs.visited.indexOf(w) && !dfs.component.contains(dfs.visited.get(i))) {
							dfs.component.add(dfs.visited.get(i));
						}
					}
					return;
				}
			}
		}
		if(!w.getBw() && !w.getFw()) { //vuol dire che w non è mai stato visitato, allora lo visito
			w.setBw(true);
			dfs.visited.add(w);
			this.dfssearch(w);
		}
	}
	
	//metodo che avvia il thread
	public void run() {
		this.dfssearch(this.dfs.get1());
	}
	
}
