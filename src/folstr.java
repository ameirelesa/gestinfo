


import java.text.SimpleDateFormat;



public class folstr {

	String satual;          // string DA (data atual)
	String sigum;			// string igUM  (indice gestacional UM)
	String siguso;			// string igUSO (indice gestacional ultrassom)
	String sdpp;            // string DPP determinada pelo algoritmo comparando DPPUSO e DPPUM
	String sdppusom;        // string DPPUSO DPP baseada só na data  UM *** reservado para futuras extensoes
	String sdica;			// string dica	informa sobre possivel problemas de consistencia
	
	
		
	public folstr (iniCal folha) {
		
		this.satual = null;
		this.sigum = null;
		this.siguso = null;
		this.sdpp = null;
		this.sdppusom = null;
		this.sdica = "\n\n\n";
					 	
				
		
		SimpleDateFormat Formata = new SimpleDateFormat("dd/MM/yyyy"); // seta formato de data
		
				
		String t1 = null;
		String t2 = null;
		String t3 = null;
		
		
		
		t1 =  String.valueOf(Formata.format(folha.DA.getTime()));
		t2 =  String.valueOf(Formata.format(folha.DPPUM.getTime()));
		t3 =  String.valueOf(Formata.format(folha.DPPUSO.getTime()));
		
				
				
		igLegal i1 = new igLegal(folha.igUM);
		igLegal i2 = new igLegal(folha.igC);
				
		this.sigum  = i1.semanas+" sem. "+i1.dias+" dias";
		
		this.siguso = i2.semanas+" sem. "+i2.dias+" dias";
	   	    
		
		// Exames Primeiro Trimestre
		if ((folha.igRef >= 0 ) && (folha.igRef <= 84 )) {
			this.sdica = "TSM VDRL EQU URO Glic\nHbSag Anti-Hiv Toxo Hcv HMG";
			
		}

		// Exames Segundo Trimestre
		if ((folha.igRef > 84 ) && (folha.igRef <= 239 )) {
			this.sdica = "EQU URO TTG Toxo(nr/nr)\nHMG";
			

		}


		// Exames Terceiro Trimestre
		if ((folha.igRef > 240 ) && (folha.igRef <= 294 )) {
			this.sdica = "VDRL Anti-Hiv EQU URO\nToxo(nr/nr) HMG SWAB";

		}		
						
		this.satual    =  t1;
		this.sdpp      =  t2;
		this.sdppusom   = t3;
		
		return;

		
	}
	
	
	
}