package glazer.nytimes;

import glazer.bike.BikeGui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.gson.Gson;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pagenum;
	private Docs[] docs;

	public GUI(NYTimes times) {
		this.pagenum = 0;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800, 800);
		this.setTitle("Last 10 headlines and lead_paragraphs in NY Times");
		Container container = getContentPane();
		JPanel panel = new JPanel();
		container.setLayout(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		docs = times.getResponse().getDocs();
		JPanel[] documents = new JPanel[docs.length + 1];

		for (int i = 0; i < docs.length; i++) {
			documents[i] = new JPanel();
			documents[i]
					.setLayout(new BoxLayout(documents[i], BoxLayout.Y_AXIS));
			JLabel headline = new JLabel();

			headline.setText("Headline: " + docs[i].getHeadline().getMain());
			headline.setVisible(true);
			documents[i].add(headline);
			JLabel leadParagraph = new JLabel();
			leadParagraph.setText(" Lead paragraph: "
					+ docs[i].getLead_paragraph());
			JButton view = new JButton();
			view.setText("View Article");
			final int num = i;
			ActionListener viewArticle = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						java.awt.Desktop.getDesktop().browse(
								java.net.URI.create(docs[num].getWeb_url()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			};
			view.addActionListener(viewArticle);

			documents[i].add(leadParagraph);
			documents[i].add(view);
			documents[i].setVisible(true);
			panel.add(documents[i]);

		}

		// JList<JPanel> list = new JList<>(documents);
		// documents[docs.length].add(list);

		panel.setVisible(true);
		ActionListener view = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				pagenum += 1;
				Gson gson = new Gson();
				try {
					String string = "http://api.nytimes.com/svc/search/v2/articlesearch.json?q=headline&page=";
					string += pagenum;
					string += "&sort=newest&api-key=2a9da2ffbe28f55ce356da64297b3fda:13:70503580";
					URL url = new URL(string);
					URLConnection connection = url.openConnection();
					InputStream in = connection.getInputStream();
					byte b[] = new byte[4096];
					int n = -1;
					StringBuilder builder = new StringBuilder();

					while ((n = in.read(b)) != -1) {
						builder.append(new String(b, 0, n));
					}

					String json = builder.toString();
					NYTimes nytimes = gson.fromJson(json, NYTimes.class);
					Docs docs[] = nytimes.getResponse().getDocs();
					for (int j = 0; j < docs.length; j++) {
						String urls = docs[j].getWeb_url(); // path to your new
															// file
						java.awt.Desktop.getDesktop().browse(
								java.net.URI.create(urls));

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};
		JButton button = new JButton();
		button.setText("View next 10 articles");
		button.addActionListener(view);
		JScrollPane scrollPane = new JScrollPane(panel);
		container.add(scrollPane, BorderLayout.NORTH);
		JPanel b = new JPanel();
		button.setVisible(true);
		b.add(button);
		b.setVisible(true);

		container.add(b, BorderLayout.SOUTH);

		// this.setContentPane(container);
		container.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
BikeGui gui=new BikeGui();
gui.setVisible(true);
	}

}
