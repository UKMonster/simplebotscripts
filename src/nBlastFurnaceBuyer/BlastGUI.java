package nBlastFurnaceBuyer;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class BlastGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JComboBox<String> itemName;


	/**
	 * Create the frame.
	 */
	public BlastGUI() {
		setTitle("Blast Furnace Buyer");
		setBounds(100, 100, 520, 318);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		itemName = new JComboBox<String>();
		itemName.setBackground(new Color(192, 192, 192));
		itemName.setForeground(Color.BLACK);
		itemName.setModel(new DefaultComboBoxModel<String>(new String[] {"Copper ore", "Tin ore", "Iron ore", "Mithril ore", "Silver ore", "Gold ore", "Coal"}));
		itemName.setBounds(102, 76, 89, 20);
		contentPane.add(itemName);
		
		JButton btnBuy = new JButton("Buy");
		btnBuy.setBackground(Color.GRAY);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NBlastFurnaceBuyer.buying = 1;
			}
		});
		btnBuy.setBounds(39, 143, 73, 35);
		contentPane.add(btnBuy);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NBlastFurnaceBuyer.buying = 0;
			}
		});
		btnStop.setBounds(152, 143, 73, 35);
		contentPane.add(btnStop);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BlastGUI.class.getResource("/nBlastFurnaceBuyer/lol.jpg")));
		lblNewLabel.setBounds(0, 0, 533, 284);
		contentPane.add(lblNewLabel);
	}
}
