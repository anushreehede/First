package math;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class User extends JFrame{
	private JTextField input;
	private JButton calc;
	public User() {
		super("Calculate using BODMAS");
		setLayout(new FlowLayout());
		input = new JTextField(20); 
		add(input);
		calc = new JButton("Calculate!");
		add(calc);
		calc.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event)
					{
						//This was initially in main()
						Calc object=new Calc();
						String str = input.getText();
						//System.out.println("Enter valid expression to calculate");
						object.getData(str);
						//Insertion of parantheses correctly
						object.parenth();
						//Infix with parantheses to postfix
						object.makePostfix();
					    //Evaluation of postfix expr
						int c =object.evaluate();
						if(c==-1) ;//exit 
						String n= Integer.toString(c);
						//n=String.format("%s",event.getActionCommand());
						JOptionPane.showMessageDialog(null, n);
					}
				}
				);
	}	
}
