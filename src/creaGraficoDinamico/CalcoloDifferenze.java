package creaGraficoDinamico;

public class CalcoloDifferenze {
	
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
	public CalcoloDifferenze(String meseAnno, long numeroS) {
		super();
		this.meseAnno = meseAnno;
		this.numeroS = numeroS;
	}
	

}
