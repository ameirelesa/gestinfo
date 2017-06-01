


import java.awt.FlowLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class executa extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		

	public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new executa().GUI();
            }
        });

    }

    private void GUI() {
    
    	
    	
    	

    	SplitPane frame = new SplitPane();
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	frame.setTitle( "GestInfo" );
    	frame.getPreferredSize();
       	frame.setResizable(false);
       	frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
       	frame.setLayout( new FlowLayout() );
        frame.setLocationByPlatform(true);
       	frame.pack();
       	frame.setVisible(true);
    	
    	
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(executa.class.getName()).log(Level.SEVERE, null, ex);
                } 
                SwingUtilities.invokeLater(new Runnable() {
                	
                     @Override
                     public void run() {
                    	 
                    	                     	                    	 
                     }
                });
            }
        }).start();
    }
   
        
    
}
