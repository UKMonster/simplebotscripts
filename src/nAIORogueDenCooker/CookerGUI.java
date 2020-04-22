package nAIORogueDenCooker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CookerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JComboBox<String> fishBox;

	/**
	 * Create the frame.
	 */
	public CookerGUI() {
		setTitle("nAIORogueCooker");
		setBounds(100, 100, 323, 162);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		fishBox = new JComboBox<String>();
		fishBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Shrimps", "Trout", "Salmon", "Tuna", "Lobster", "Swordfish", "Monkfish", "Shark", "Manta Ray", "Anglerfish"}));
		fishBox.setBounds(71, 34, 188, 20);
		contentPane.add(fishBox);
		
		JLabel lblSelectFish = new JLabel("Select Fish");
		lblSelectFish.setForeground(Color.WHITE);
		lblSelectFish.setBounds(10, 37, 65, 14);
		contentPane.add(lblSelectFish);
		
		JButton btnStartButton = new JButton("Cook!");
		btnStartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NAIORogueDenCooker.started = true;
			}
		});
		btnStartButton.setBounds(28, 87, 89, 23);
		contentPane.add(btnStartButton);
		
		JButton btnStopButton = new JButton("Stop!");
		btnStopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIORogueDenCooker.started = false;
			}
		});
		btnStopButton.setBounds(127, 87, 89, 23);
		contentPane.add(btnStopButton);
		
		JButton btnTogglePaint = new JButton("Paint");
		btnTogglePaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIORogueDenCooker.hidePaint = !NAIORogueDenCooker.hidePaint;
			}
		});
		btnTogglePaint.setBounds(226, 87, 71, 23);
		contentPane.add(btnTogglePaint);
	}
}
