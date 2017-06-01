import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.basic.BasicSpinnerUI;



public class CSpinWeek extends JPanel 

{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// inicio


private 	JPanel 		spin;
private		 int	   	digits = 2;
private		 int[]   	min = { 0 , 0 }; 
private		 int[]		max = { 49 ,6 };
private		 int[]      unit = { 7, 1 }; // unidade de cada base (ex. semana = 7, dias = 1, mes = 31 ou 30 etc...
private     JSpinner[] 	spinners;
			int			conta;

public JPanel spin (String titulo, String unidade) 
 {   
	
   
	
   spinners = new JSpinner[digits];  
       
     
    spin = new JPanel();
    
    spin.setLayout(new GridLayout(1,3));
    
        

    spin.add( new JLabel(titulo), BorderLayout.WEST );
    
    
    
    for(int x = 0, y = spinners.length; x < y; x++)   
    {   
      SpinnerNumberModel model = new SpinnerNumberModel(0,min[x],max[x],1);   
      
      spinners[x] = new JSpinner(model);   
      spinners[x].setUI(new MyUI(x));   
     
          
      spin.add(spinners[x]);   
    }   
    
    spin.add( new JLabel(unidade), BorderLayout.EAST );
    
                 
     return spin;
    
}
  
public class MyUI extends BasicSpinnerUI   
  {   
    int x;   
    public MyUI(int f)   
    {   
      x = f;   
      
    }   
    protected Component createNextButton()   
    {   
      JButton btnNext = (JButton)super.createNextButton();   
      btnNext.removeActionListener(btnNext.getActionListeners()[0]);   
      btnNext.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent ae){   
          changeSpinners(x,1);  
          
        }   
      });   
      return btnNext;   
    }   
    protected Component createPreviousButton()   
    {   
      JButton btnPrevious = (JButton)super.createPreviousButton();   
      btnPrevious.removeActionListener(btnPrevious.getActionListeners()[0]);   
      btnPrevious.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent ae){   
          changeSpinners(x,-1); 
            
        }   
      });   
      return btnPrevious;   
    }
    
    public void changeSpinners(int x, int amount)   
    {   
                  
    	
    	 boolean test1=true, test2= true;
    	  
         int ad = (int) (spinners[x].getValue()) ;
         
         
         int step = ad + amount;
         
                      
        	 if (step < min[x]) { 
        		 
        		 spinners[x].setValue(((int) max[x]));
        		 test1 = false;
        		        		 
        	 }
         
         
        
        
             
        	 if (step > max[x]) { 
        		 
        		 spinners[x].setValue(((int) min[x]));
        		 test2 = false;
        	 } 
         
         
      
         
        if (test1 && test2) {
        	
   		 spinners[x].setValue(step);
   		    	 
        	
        }
 
        
        
        
        conta = 0;
   		
   		for(int xx = 0, yy = spinners.length; xx < yy; xx++)   
        {   
          
       	 conta = conta + ( ((int) (spinners[xx].getValue())) * unit[xx]); 
   		}
   		
   		
   		
   		
   		
   		
   		
  }   




  }

}






	
	
	
	
	
	
	
	

