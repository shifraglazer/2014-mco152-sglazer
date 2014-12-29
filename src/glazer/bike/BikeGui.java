package glazer.bike;

import java.awt.Container;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class BikeGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JPanel listPanel;
	JList<String> jList;
	JLabel pic;
	public BikeGui(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800, 800);
		this.setTitle("CitiBike");
		Container container=getContentPane();
		container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
		pic=new JLabel();
		ImageIcon icon;
		try {
			String url="https://www.citibikenyc.com/assets/images/pages/feature-pages/about-the-bike/about-the-bike-module-1.png";
			icon = new ImageIcon(new URL(url));
			pic.setIcon(icon);
			pic.setVisible(true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listPanel=new JPanel();
		panel=new JPanel();
		panel.add(pic);
		jList=new JList<String>();
		//panel.add(jList);
		listPanel.add(jList);
		listPanel.setVisible(true);
		container.add(panel);
		container.add(listPanel);
		container.setVisible(true);
	}
	public void setUp(Bike bike){
		int length=bike.getStationBeanList().length;
		StationBeanList[] list=bike.getStationBeanList();
		String[] labels=new String[length*4];
		int count=0;
		for(int i=0;i<length;i++){
		labels[count]=list[i].getStationName();
		count++;
		labels[count]="Total Docks: "+list[i].getTotalDocks();
		count++;
		labels[count]="Docks Avaliable: "+list[i].getAvailableDocks();
		count++;
		labels[count]="Bikes Avialable: "+list[i].getAvailableBikes();
		count++;
		
		}
		jList=new JList<String>(labels);
		jList.setVisible(true);
		listPanel.add(jList);
		
	}
}
