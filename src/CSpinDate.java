
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;
import javax.swing.plaf.basic.BasicSpinnerUI;



public class CSpinDate extends JPanel 

{

// inicio

private static final long serialVersionUID = -5425607412712040614L;
private 	JPanel 		spin;
private     JSpinner[]  spinners;   
			Calendar gc;   
			

public JPanel spin (String titulo) 
 {   
		 	
  spinners = new JSpinner[3];   
  gc = new GregorianCalendar();    

    Date d = gc.getTime();   
    
    int[] calendarFields = {Calendar.DAY_OF_MONTH,Calendar.MONTH,Calendar.YEAR,   
                          Calendar.HOUR_OF_DAY,Calendar.MINUTE,Calendar.SECOND};   
    String[] dateParts = {"dd","MMM","yyyy","HH","mm","ss"};   
    
    
    
    spin = new JPanel();
    
    spin.setLayout(new GridLayout(1,3));
      

    spin.add( new JLabel(titulo));
    
    
    for(int x = 0, y = spinners.length; x < y; x++)   
    {   
      SpinnerDateModel model = new SpinnerDateModel(d,null,null,calendarFields[x]);   
      spinners[x] = new JSpinner(model);   
      spinners[x].setUI(new MyUI(calendarFields[x]));   
      spinners[x].setEditor(new DateEditor(spinners[x],dateParts[x]));   
      spin.add(spinners[x]);   
    }   
    	
    	        
     return spin;
    
}
  
public class MyUI extends BasicSpinnerUI   
  {   
    int field;   
    public MyUI(int f)   
    {   
      field = f;   
    }   
    protected Component createNextButton()   
    {   
      JButton btnNext = (JButton)super.createNextButton();   
      btnNext.removeActionListener(btnNext.getActionListeners()[0]);   
      btnNext.addActionListener(new ActionListener(){   
        public void actionPerformed(ActionEvent ae){   
          changeSpinners(field,1);   
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
          changeSpinners(field,-1);   
        }   
      });   
      return btnPrevious;   
    }
    
    public void changeSpinners(int field,int amount)   
    {   
      gc.add(field,amount);   
      for(int x = 0, y = spinners.length; x < y; x++)   
      {   
        spinners[x].setValue(gc.getTime());   
                
      }   
    }
  }   




}






	
	
	
	
	
	
	
	
	
	

