//
// ##### multiCal
// classe para transporte e incialização de Calendar
// DUM 		    armazena data da ultima menstruacao
// DUMmin		armazena data minima para a ultima menstruacao ser valida
// DUMmax		armazena data maxima para a ultima menstruacao ser valida 
// DUSO			armazena data do realizacao do ultrassom
// DUSOmin		armazena data minima plausivel para o ultrassom ser valido
// DUSOmax		aramzena data maxima plausivel para o ultrassom ser valido
// DA		    armazena data atual do sistema
// DPPUM		armazena data provavel do parto segundo DUM
// DPPUSO		aramzena data provavel do parto segundo ultrassom
//
//
// ##### constantes
// erroad		erro admissível  (14 dias ?) para fins computacionais
// gestacao     constante que define a gestacao do Homo sapiens em 294 dias (segundo o Ginecologista e Sexólogo Dr. Leandro M. de Andrade)
// minDADUM   	intervalo minimo ente as datas DA e DUM para ser valido como igUM baseado na DUM
// gestacao = 294;   // dias
// erroad = 14;      // erro admissível pela formula do calculo gestacional
// minDADUM = 28;     // intervalo DA-DU minimo para ser valido com igUM 
// igUMUSO1 = 5;     // regra 5 dias 
// igUMUSO2 = 10;    // regra 10 dias 
// igUMUSO3 = 15;    // regra 15 dias
// igUMC1 = 84;     // regra 84  dias 
// igUMC2 = 133;    // regra 133 dias 
// igUMC3 = 144;    // regra 144 dias
//
// ##### variaveis inteiras
// inDADUM		intervalo ente as datas DA e DUM (nao confundir com o ig)
// igUM			indice gestacional baseado na data da ultima menstruacao 
// igUSO		indice gestacional baseado na data do ultrassom
// igC			indice gestacional consolidado como analise do igUM e do igUSO
// igRef		indice gestacional de referencia mais confiavel segundo o algoritmo proposto, coerencia de datas, regras acima, erros, constantes, descritos acima
//
// ##### erro flag  #### previnem falha humana na entrada ou do processamento
// initStatus	retorna o estado de coerencia analizando varias datas e indices (DA,DUM...)
//     0		init status					  iniciado
//	   1		PUERPERIO					  ( passou o tempo de gestacao normal ?)
//    -1 		SUGERE CONFIRMAR com BETA HCG ( nao gravida? )
//     2        igUM 						  selecione IGUM   (mais confiavel)
//     3        igUSO						  selecione IGUSO  (mais confiavel)
//     4        igUM e igC					  selecione IGC    (confirmacao dupla)
//     5        
//             
// umDiaInputCheck 		retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido
// usomDiaInputCheck	retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido



import java.util.Calendar;


	public class iniCal {
	    // datas
		private Calendar DUM;          // 1) ultima menstruacao entrada pela GUI  
		private Calendar DUMmin;       // 2) ultima menstruacao minima calculada para saida   
		private Calendar DUMmax;	   // 3) ultima menstruacao minima calculada para saida
		private Calendar DUSO;         // 4) ultrassom entrada pela GUI 			
		private Calendar DUSOmin;      // 5) ultrassom minima calculada para saida 
		private Calendar DUSOmax;	   // 6) ultrassom maxima calculada para saida
		 Calendar DA;           // 7) data atual entrada pelo relogio do sistema 
		 Calendar DPPUM;        // 8) data provavel do parto segundo ultima menstruacao    
		 Calendar DPPUSO;       // 9) data provavel do parto segundo ultrassom
		
		private int inDADUM;				// dias intervalo DA DUM
		private int inDADUSO;				// dias intervalo DA DUSO
		 int igUM;					// indice gestacional baseado na DUM										
		private int igUSO;					// indice gestacional baseado no DUSO	
		 int igC;				    // indice gestacional baseado no igUM e igUSO
		 int igRef;					// indice gestacional de referencia mais confiavel
		
		byte initStatus;			// returna estado dos dados tratamento de erros etc...
		byte umDiaInputCheck; 	    // retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido
		byte usomDiaInputCheck;	    // retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido
		
		// constantes e limitadores para evitar valores incoerentes		
		private int gestacao = 280;   // dias segundo Resende
		// private int   erroad = 14;    // erro admissível pela formula do calculo gestacional Resende
		
		private int minDADUM = 28;    // intervalo DA-DU minimo para ser valido com igUM 
		private int igUMUSO1 = 5;     // regra 5 dias 
		private int igUMUSO2 = 10;    // regra 10 dias 
		private int igUMUSO3 = 15;    // regra 15 dias
		
		private int igUMC1 = 84;      // regra 84  dias 
		private int igUMC2 = 133;     // regra 133 dias 
		private int igUMC3 = 144;     // regra 144 dias
		
		private int puerperio = 375;  // gravidez mais longa registrada da humanidade (segundo google a ser verificado)
		private int maxigdusom = 308; // igdusom valor maximo computavel (a ser determinada por um especialista) 
		
	
		
	public iniCal( Calendar dum, Calendar duso, int igduso) {
	
					
		Calendar cal = Calendar.getInstance();       // PEGA DATA ATUAL DO SISTEMA
		
		// SINCRONIZA PRO CLONAGEM TODAS AS Calendar
		// inicializa os Calendar
		
		DUM     = (Calendar) cal.clone();            
		DUMmax  = (Calendar) cal.clone();  					// data maxima para o JSpinner DUM é data atual
		DUMmin  = (Calendar) cal.clone();						
		
		DUMmin.add(Calendar.DAY_OF_YEAR, -(puerperio)); 	// computa data minima parao Jpinnner
		
		
		DUSO    = (Calendar) cal.clone();    		
	 	DUSOmax = (Calendar) cal.clone();  
	 	
	 	DUSOmax.add(Calendar.DAY_OF_YEAR, (-maxigdusom)); 	// computa data minima para o Jpinnner coincide com o erro admissível
		
	 	DUSOmin = (Calendar) cal.clone();  
		
	
		
		DA      = (Calendar) cal.clone();    
		DPPUM   = (Calendar) cal.clone();    
		DPPUSO  = (Calendar) cal.clone();  
		
		
		inDADUM  = 0;					// dias intervalo DA DUM
		inDADUSO = 0;					// dias intervalo DA DUSO
		
		igUM   = 0;					// indice gestacional baseado na DUM										
		igUSO  = 0;					// indice gestacional baseado no DUSO	
		igC    = 0;				    // indice gestacional baseado no igUM e igUSO
		igRef = 0; 					// indice gestacional de referencia mais confiavel
		
		initStatus = 0;			    // retorna estado dos dados tratamento de erros etc...
				
		umDiaInputCheck   = 0;  	   // retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido
		usomDiaInputCheck = 0;	       // retorna o estado de coerencia do dia lido pelo Spin  0 = OK, 1 = invalido
		
		// inicia copia do calendar
		
		DUM   = (Calendar) dum.clone();
		DUSO  = (Calendar) duso.clone();		
		igUSO = igduso;
		
		
		
		// calcula intervalos 
		
		inDADUM =  entreCal.dias(DUM, DA);   		 // intervalo de dias entre DUM DA
		
		inDADUSO = entreCal.dias(DUSO, DA);          // intervalo de dias entre DUSO DA
		
		igC = inDADUSO + igUSO;                      // ig Consolidado = (intervalo DA DUM [dias]) + igUSO [dias]
		
		igUM = inDADUM;					    		 // ig baseado data da UM
		
		igRef = igUM;
		
		
		
		
		
		// calcula as DDP UM e USO		
		DPPUM.add(Calendar.DAY_OF_YEAR,(gestacao- igUM)); 	    // DPP em função do igUM.
		DPPUSO.add(Calendar.DAY_OF_YEAR,(gestacao - igC)); 		// DPP em função do igC
		
		
		
		// verifica a coerencia das datas lidas 
		
		if ((DUM.before(DUMmax)) && (DUM.after(DUMmin)))   {
			umDiaInputCheck = 0 ;
			
		} else { umDiaInputCheck = 1; } ;
				
		
		if ((DUSO.after(DUSOmax)) && (DUSO.before(DUSOmin)))   {
			usomDiaInputCheck = 0 ;
			
		} else { usomDiaInputCheck = 1 ; }
		
		
		
		
		if ((inDADUM <= minDADUM ) && (umDiaInputCheck==0) && (usomDiaInputCheck ==0)){
			initStatus = -1;
					
		} // exames para confirmar gravidez
		
						
		
		if ((inDADUM > gestacao) && (umDiaInputCheck==0) && (usomDiaInputCheck ==0)) {
			
			initStatus = 1;
			
		} // passou do periodo gestacional normal puerperio
		
		
		
		if ((inDADUM < gestacao) && (umDiaInputCheck==0) && (usomDiaInputCheck ==0) && (inDADUM <= minDADUM )) {
			
			
		
		
		// matriz de teste principal (a)
		if (igUM>igC) {
			
			if (igUSO<igUMC1) {
				
				if ((igUM-igUSO)< igUMUSO1) {
					initStatus = 2;
					igRef = igUM;
				}
				
			}
			
			if (igUSO<igUMC2) {
				
				if ((igUM-igUSO)< igUMUSO2) {
					initStatus = 2;
					igRef = igUM;
				}
				
			}
			
			
			if (igUSO<igUMC3) {
				
				if ((igUM-igUSO)< igUMUSO3) {
					initStatus = 2;
					igRef = igUM;
				}
				
			}
					
		}
		
		// matriz de teste principal (b)
		
		
		if (igUM<igC) {
			
			if (igUSO<igUMC1) {
				
				if ((igUM-igUSO)< igUMUSO1) {
					initStatus = 3;
					igRef = igC;
				}
				
			}
			
			if (igUSO<igUMC2) {
				
				if ((igUM-igUSO)< igUMUSO2) {
					initStatus = 3;
					igRef = igC;
				}
				
			}
			
			
			if (igUSO<igUMC3) {
				
				if ((igUM-igUSO)< igUMUSO3) {
					initStatus = 3;
					igRef = igC;
				}
				
			}
					
		}
		
				
		
		
		
		if (((igUM==igUSO) && (igUSO >= igUMUSO1) &&  (igUM >= minDADUM)) && (umDiaInputCheck==0) && (usomDiaInputCheck ==0)) {
			
			initStatus = 4;
			igRef = igC;
				
			
		}
		
				
		
		
		} // parece tudo OK.
					
			
		// valores para inicializar os Spins 
		
			
		
	return ;
	
	
	
	}
	
	}

	
	
	
