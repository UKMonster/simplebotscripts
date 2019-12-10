package nElves;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class ElvesGUI extends JFrame {

	public static JComboBox comboFood;
	public static JCheckBox chckbxAlchDiamonds;
	private JPanel contentPane;
	public static JTextField hpField;

	/**
	 * Create the frame.
	 */
	public ElvesGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 189);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nElves.botstarted = 1;
			}
		});
		btnStart.setBounds(48, 105, 89, 25);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nElves.botstarted = 0;
			}
		});
		btnStop.setBounds(199, 105, 89, 25);
		contentPane.add(btnStop);
		
		comboFood = new JComboBox();
		comboFood.setModel(new DefaultComboBoxModel(new String[] {"Tuna", "Lobster", "Monkfish", "Shark"}));
		comboFood.setBounds(105, 11, 140, 23);
		contentPane.add(comboFood);
		
		chckbxAlchDiamonds = new JCheckBox("");
		chckbxAlchDiamonds.setForeground(Color.WHITE);
		chckbxAlchDiamonds.setBackground(Color.DARK_GRAY);
		chckbxAlchDiamonds.setBounds(91, 51, 21, 23);
		contentPane.add(chckbxAlchDiamonds);
		
		JLabel lblSelectFood = new JLabel("Select Food:");
		lblSelectFood.setForeground(Color.WHITE);
		lblSelectFood.setBounds(37, 15, 75, 14);
		contentPane.add(lblSelectFood);
		
		JLabel lblAlchDiamonds = new JLabel("Alch Diamonds?");
		lblAlchDiamonds.setForeground(Color.WHITE);
		lblAlchDiamonds.setBounds(10, 54, 89, 14);
		contentPane.add(lblAlchDiamonds);
		
		hpField = new JTextField();
		hpField.setText("10");
		hpField.setBounds(199, 51, 86, 20);
		contentPane.add(hpField);
		hpField.setColumns(10);
		
		JLabel lblHpToEat = new JLabel("Hp to eat at?");
		lblHpToEat.setForeground(Color.WHITE);
		lblHpToEat.setBounds(131, 49, 68, 25);
		contentPane.add(lblHpToEat);
	}
}
