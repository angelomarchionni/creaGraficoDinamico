package creaGraficoDinamico;

public class Avro {
	
	String meseAnno;
	public String getMeseAnno() {
		return meseAnno;
	}
	public void setMeseAnno(String meseAnno) {
		this.meseAnno = meseAnno;
	}
	public long getNumeroS() {
		return numeroS;
	}
	public void setNumeroS(long numeroS) {
		this.numeroS = numeroS;
	}
	long numeroS;
	public Avro(String meseAnno, long numeroS) {
		super();
		this.meseAnno = meseAnno;
		this.numeroS = numeroS;
	}
	

}
