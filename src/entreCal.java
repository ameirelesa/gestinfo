


	
	// nota: import java.text.ParseExceptio opcional se for usar la embaixo retorno de erro
	// calcula o numero de dias entre duas Date 
	// dias = Calendar Mais proxima - Calendar Mais Antiga
	// retorna um inteiro
	// usa milisegundos para o calculo como long depois converte o long com inteiro
	// importante a diferenca em dias nao pode ser maior que a capacidade do int



	import java.util.Calendar;

	public class entreCal {
		
		private static final int MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;
		 
		public static int dias (Calendar start, Calendar end) {
		
			boolean teste =  end.before(start);
			
			
	    if (teste == false) {
		 
	    		  	    	
	    	
	    	long startTime = start.getTimeInMillis();
	    	    	
	    	
	    	long endTime = end.getTimeInMillis();
	    	
	     
	    	
	    	int dias = convLong2Int((endTime - startTime) / MILLISECONDS_IN_DAY);
	        
	    	return dias; // (dias) / MILLISECONDS_IN_DAY;
	    	
	    	} else {
	    		
	    		return 0;
	    				     	  
	      	  		// opcao new IllegalArgumentException("erro na conversao da data");
	      	  		// devolve um erro caso nao consiga converter
	    	}
	       	     	  
	      	  
		 }
		 
		
			
		// tenta converter com seguranca um long em um int
		public static int convLong2Int(long l) {
		    int i = (int)l;
		    if (i != l) {
		        throw new IllegalArgumentException(l + " inteiro longo demais para int");
		    }
		    return i;
		}
		
		
	}

