package nAIOAlcher;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	public static JPanel contentPane;
	public static JTextField itemNameField;

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Alch gui");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblItemToAlch = new JLabel("Item to alch? (name)");
		lblItemToAlch.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemToAlch.setBounds(20, 16, 354, 14);
		contentPane.add(lblItemToAlch);

		itemNameField = new JTextField();
		itemNameField.setBounds(20, 41, 354, 20);
		contentPane.add(itemNameField);
		itemNameField.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NAIOAlcher.startedbot = 1;
			}
		});
		btnStart.setBounds(54, 98, 89, 23);
		contentPane.add(btnStart);

		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIOAlcher.startedbot = 0;
			}
		});
		btnStop.setBounds(203, 98, 89, 23);
		contentPane.add(btnStop);
	}
}
