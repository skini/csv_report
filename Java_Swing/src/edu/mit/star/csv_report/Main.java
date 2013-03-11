package edu.mit.star.csv_report;

import java.awt.Color;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.awt.event.*;

//Modified by Shloka Kini

public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	JTextArea input;
	JButton calculate;
	JPanel report;
	JTextArea info_from_file = new JTextArea();

	@Override
	public void addNotify()
	{
		super.addNotify();
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		input = new JTextArea(12, 40);
		input.setBorder(BorderFactory.createTitledBorder("Input"));
		calculate = new JButton("Calculate");
		report = new JPanel();
		report.setBorder(BorderFactory.createTitledBorder("Report"));
		c.add(input);
		c.add(calculate);
		c.add(report);

		report.add(info_from_file);
		
		//This adds an action listener for the jbutton calculate
		calculate.addActionListener(new ActionListener() {
			  public void actionPerformed(ActionEvent evt) {
				  
				  String s = input.getText();
				  //Resets input to empty
				  input.setText("");
				  //Creates a report object and populates table
				  Report newRep = new Report(s);
				  newRep.populateTable();
				  //sets output string with formatted records
				  s = newRep.getRecords();
				  info_from_file.setText(s);
				  
			  }
		});
	}

	public static void main(String[] args)
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{

				@Override
				public void run()
				{
					Main m = (new Main());
					m.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					m.pack();
					m.setVisible(true);
				}
			});
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
	}
}
