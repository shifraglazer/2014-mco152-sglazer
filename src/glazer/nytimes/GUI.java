package glazer.nytimes;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.google.gson.Gson;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pagenum;

	public GUI(NYTimes times) {
		this.pagenum = 0;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800, 400);
		this.setTitle("Last 10 headlines and lead_paragraphs in NY Times");
		Container container = getContentPane();
		JPanel panel = new JPanel();
		container.setLayout(new BorderLayout());

		Docs[] docs = times.getResponse().getDocs();
		String[] documents = new String[docs.length];
		for (int i = 0; i < docs.length; i++) {
			documents[i] = "Headline: " + docs[i].getHeadline().getMain()
					+ " Lead paragraph: " + docs[i].getLead_paragraph();
		}
		JList<String> list = new JList<String>(documents);
		panel.add(list);
		list.setVisible(true);
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
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(2, 2, 800, 300);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(800, 400));
		contentPane.add(scrollPane);

		container.add(contentPane, BorderLayout.NORTH);
		JPanel b = new JPanel();
		button.setVisible(true);
		b.add(button);
		b.setVisible(true);

		container.add(b, BorderLayout.SOUTH);
	
		//this.setContentPane(container);
		container.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
