

public class igLegal {
	
	int dias;
	int semanas;
	
	public igLegal (int numero){
		
					
				// tenta devolver uma string que eh a representacaoo de uma fracao de um decimal
				// exemplo carrega 2.5 como double e devolve 2 1/2 como string 
			
			    //String ret = null;
				
				double racional, resto;
				int partInt;
				int partRac;
							
				racional = ((double) numero)  / 7 ;
				
				resto = (((double) numero) % 7);
				
				partInt = ((int) racional);
				partRac = (int) (resto);
				
				this.semanas = partInt;
				
				this.dias = partRac;
									  
			        
			    return;
			}
		
}		

	
	
	


