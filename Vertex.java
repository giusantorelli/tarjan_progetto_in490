package tarjan_incrementale;

import java.util.LinkedList;

public class Vertex {
									//in un arco vw il vertice è:
	private boolean forward;        //se è w o raggiunto in avanti da w
	private boolean backward;       //se è v o raggiunto all'indietro da v
	private boolean scanned;        //se è bw e tutti gli archi entranti sono stati visitati (analogamente se fw)
	private int tag;
	private int fposition;          //punta all'ultimo arco attraversato in avanti
	private int bposition;          //punta all'ultimo arco attraversato indietro
	LinkedList<Vertex> next;        //lista evolutiva dei vertici in uscita
	LinkedList<Vertex> prev;        //lista evolutiva dei vertici in entrata

	// costruttore di un vertice isolato
	public Vertex(int n) {
		this.setTag(n);
		this.setFposition(0);
		this.setBposition(0);
		this.setBw(false);
		this.setFw(false);
		this.setSc(false);
		next = new LinkedList<Vertex>();
		prev = new LinkedList<Vertex>();
	}

	// getters e setters

	void setFw(boolean b) {
		this.forward = b;
	}

	void setBw(boolean b) {
		this.backward = b;
	}

	void setSc(boolean b) {
		this.scanned = b;
	}

	void setTag(int n) {
		this.tag = n;
	}

	void setFposition(int n) {
		this.fposition = n;
	}
	
	void setBposition(int n) {
		this.bposition = n;
	}

	boolean getFw() {
		return this.forward;
	}

	boolean getBw() {
		return this.backward;
	}

	boolean getSc() {
		return this.scanned;
	}

	int getTag() {
		return this.tag;
	}

	int getFposition() {
		return this.fposition;
	}
	
	int getBposition() {
		return this.bposition;
	}
}
