package glazer.connectfour;



import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4Frame extends JFrame{
	//started
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Connect4Frame(){
		this.setSize(800,600);
		this.setTitle("Connect Four");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		GridLayout layout=new GridLayout(6,7);
		Container container= getContentPane();
		container.setLayout(layout);
		for(int i=0;i<42;i++){
			final JButton button=new JButton();
		container.add(button);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				if(button.getBackground()==Color.GREEN){
				button.setBackground(Color.PINK);
				}
				else{
					button.setBackground(Color.GREEN);
				}
			}
		
		});
		}
		
	}
	public static void main(String args[]) {
		Connect4Frame frame = new Connect4Frame();
		frame.setVisible(true);
	}
	
}
