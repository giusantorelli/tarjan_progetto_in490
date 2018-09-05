package tarjan_incrementale;

public class Fsearch implements Runnable{

	Dfs dfs;

	//costruttore
	public Fsearch(Dfs d) {
		this.dfs = d;
	}
	
	//metodo per la ricerca in avanti
	void dfssearch(Vertex v) {
		if(v.next.isEmpty()) { //se non ci sono vertici da visitare mi fermo
			return;
		}
		Vertex w = v.next.get(v.getFposition());   //primo vertice non visitato
		v.setFposition(v.getFposition()+1);		   //incremento il contatore
		if(v.getFposition() == v.next.size()) {    //se sono stati visitati tutti gli archi uscenti di v allora è scanned
			v.setSc(true);
		}
		if(w.next.isEmpty() && !v.getSc()) { //se da w non ho vertici da visitare allora proseguo con la visita di v
			this.dfssearch(v);
		}
		if(w.getBw()) {     //espone il ciclo con tutti i vertici fw e i bw trovati prima di w
			if(!dfs.component.contains(w)){
				dfs.component.add(w);
			}
			for(int i=0; i<dfs.visited.size(); i++) {
				if((dfs.visited.get(i).getFw() || i<dfs.visited.indexOf(w)) && !dfs.component.contains(dfs.visited.get(i))) {
					dfs.component.add(dfs.visited.get(i));
				}
			}
			return;
		}
		if(w.getFw()) {       //lo visito se non è scanned altrimenti visito il successivo, se sono tutti scanned mi fermo
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
						if(dfs.visited.get(i).getFw() && i>dfs.visited.indexOf(w) && !dfs.component.contains(dfs.visited.get(i))) {
							dfs.component.add(dfs.visited.get(i));
						}
					}
					return;
				}
			}
		}
		if(!w.getBw() && !w.getFw()) { //w non è mai stato visitato, allora lo visito
			w.setFw(true);
			dfs.visited.add(w);
			this.dfssearch(w);
		}
	}
	
	//metodo che avvia il thread
	public void run() {
		this.dfssearch(this.dfs.get2());
	}
	
}
