package nAIOCutNFletch;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class FletchGUI extends JFrame {

	private JPanel contentPane;
	public static JComboBox comboTrees;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FletchGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("AIO Cut'n'Fletch");
		setBounds(100, 100, 334, 129);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboTrees = new JComboBox();
		comboTrees.setBounds(28, 11, 235, 20);
		comboTrees.setModel(new DefaultComboBoxModel(new String[] {"Oak", "Willow", "Maple", "Yew"}));
		contentPane.add(comboTrees);

		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.setBounds(174, 42, 134, 34);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIOCutNFletch.startedbot = 0;
			}
		});
		contentPane.add(btnNewButton_1);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(10, 42, 154, 34);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIOCutNFletch.startedbot = 1;
			}
		});
		contentPane.add(btnStart);
	}

}
