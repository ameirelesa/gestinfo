
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;



public class SplitPane extends JFrame {
	
	private static final long serialVersionUID = 8013523557285178497L;


	private 	String  	imagem = "bebe.gif";
	private		JButton		botao1, botao2;
	private     String 		dataDicas,avisoDicas,textoResultado;

	private String NOMEBT1 = "Calcular";
	private String NOMEBT2 = "Sair";
	
	private String tituloResultado = "Resultados";
	private String datas = "Datas";
	private iniCal cal ;
	private folstr fol ;
	private ouve ouvinte;

	private JTextArea rtext,tip1;
	
	private CSpinDate spin1;
	private CSpinDate spin2;
	private CSpinWeek spin3;
	
	
	
		
		
public SplitPane() {
    	

	// painel com a figura os botões
	JPanel cpp = new JPanel();
	JLabel pic = new JLabel();
	pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("./"+imagem)));
	
	
	// botoes 
	botao1 = new JButton(NOMEBT1);  
	botao2 = new JButton(NOMEBT2);
	
	// listener ouvinte para o comportamento dos botoes
	ouvinte = new ouve();
	botao1.addActionListener(ouvinte);
	botao2.addActionListener(ouvinte);		

	// escolhe gerenciador do painel e adiciona elementos ao painel
	cpp.setLayout( new BorderLayout() );
	cpp.add( pic, BorderLayout.WEST);
	cpp.add( botao1, BorderLayout.CENTER );
	cpp.add( botao2, BorderLayout.EAST );
	

	// painel com spinners personalizados
	JPanel csp = new JPanel();
	spin1 = new CSpinDate();
	spin2 = new CSpinDate();
	spin3 = new CSpinWeek();
	
	// escolhe gerenciador do painel e adiciona elementos ao painel
	 csp.setBorder(javax.swing.BorderFactory.createTitledBorder(null,datas, javax.swing.border.TitledBorder.CENTER, 
			 javax.swing.border.TitledBorder.DEFAULT_POSITION));
 	 csp.setLayout( new BorderLayout() );
	
 	 
	// adiciona Spins Especializados ao painel
	csp.add( spin1.spin("DUM :"), BorderLayout.NORTH );
	csp.add( spin2.spin("DUsg:"), BorderLayout.CENTER );
	csp.add( spin3.spin("IG Usg:"," s + d"), BorderLayout.SOUTH );

	
	// inicia os campos 
	cal = new iniCal(spin1.gc,spin2.gc,spin3.conta);
	fol = new folstr (cal);
	dataDicas = fol.satual;
	avisoDicas = fol.sdica;

	
	// adiciona o painel de dicas para o dr. leandro
	JPanel ctp = new JPanel();
	ctp.setBorder(javax.swing.BorderFactory.createTitledBorder(null,dataDicas, javax.swing.border.TitledBorder.CENTER, 
			javax.swing.border.TitledBorder.DEFAULT_POSITION));  
	
	// atribui um scroll para a area de texto 
	tip1 = new JTextArea(avisoDicas);
	tip1.setWrapStyleWord(true);
	tip1.setEditable(false);
	JScrollPane scroll1 = new JScrollPane(tip1);
	
	
	// escolhe gerenciador do painel e adiciona elementos ao painel
	ctp.setLayout( new BorderLayout() );
	ctp.add( scroll1, BorderLayout.NORTH);	


    // inicializa em branco o texto 
	textoResultado = "DPP  UM: \n"+
		             "DPP Usg: \n"+ 
			         "IG  UM: \n"+
			         "IG Usg: \n";
						
	
			

	// adiciona o painel de resultados
	JPanel crp = new JPanel();
	rtext = new JTextArea (textoResultado);
	rtext.setWrapStyleWord(true);
	rtext.setEditable(false);
		
	// atribui um scroll para a area de texto 
    rtext.setLineWrap(true);
    rtext.setWrapStyleWord(true);
    JScrollPane scroll = new JScrollPane(rtext);
    crp.setBorder(javax.swing.BorderFactory.createTitledBorder(null,tituloResultado, javax.swing.border.TitledBorder.CENTER, 
    		javax.swing.border.TitledBorder.DEFAULT_POSITION));
    
	crp.setLayout( new BorderLayout() );
	crp.add(scroll, BorderLayout.SOUTH );

	
	// organizando a bagunça com Splits 
	// mete os outros painéis -> organiza
	JSplitPane splitPaneV1 = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
	add( splitPaneV1, BorderLayout.NORTH );

	JSplitPane splitPaneV2 = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
	add( splitPaneV2, BorderLayout.CENTER );

	JSplitPane splitPaneV3 = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
	add( splitPaneV3, BorderLayout.SOUTH );

	splitPaneV1.setTopComponent ( cpp);
	splitPaneV1.setBottomComponent( splitPaneV2 );

	splitPaneV2.setTopComponent( csp );
	splitPaneV2.setBottomComponent( splitPaneV3 );

	splitPaneV3.setTopComponent( ctp );
	splitPaneV3.setBottomComponent( crp );



	pack();

   
           
    
}







class ouve implements ActionListener {
	
	public void actionPerformed(ActionEvent evt) {
		
	
		
		if (evt.getActionCommand().equals(NOMEBT1)) {
			
	
		cal = new iniCal (spin1.gc,spin2.gc,spin3.conta);
		fol = new folstr (cal);
		dataDicas = fol.satual;
		avisoDicas = fol.sdica;
	
		
		textoResultado = "DPP  UM: \n"+
	             "DPP Usg: \n"+ 
		         "IG  UM: \n"+
		         "IG Usg: \n";
					
		
		
		textoResultado =  String.format( "DPP  UM: %s\n"+
				"DPP Usg: %s\n"+ 
				"IG  UM: %s\n"+
				"IG Usg: %s\n",fol.sdpp, fol.sdppusom,fol.sigum,fol.siguso) ;
			       
		
		
		rtext.setText(textoResultado);
		rtext.repaint();
				
		dataDicas = fol.satual;
		avisoDicas = fol.sdica;


		
		
		tip1.setText(avisoDicas);
			
		
		tip1.repaint();
		
		
		}
				
		
		if (evt.getActionCommand().equals(NOMEBT2)) {
			
			dispose();
							
		}
		
		
		
	}
  }


}



