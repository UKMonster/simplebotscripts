package nAIOCannonBalls;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

public class CannonGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	public CannonGUI() {
		setTitle("AIO CannonBalls");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 295, 130);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectLocation = new JLabel("Select Location");
		lblSelectLocation.setForeground(Color.WHITE);
		lblSelectLocation.setBounds(10, 14, 89, 14);
		contentPane.add(lblSelectLocation);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NAIOCannonBalls.started = true;
			}
		});
		btnStart.setBackground(Color.LIGHT_GRAY);
		btnStart.setBounds(20, 49, 62, 23);
		contentPane.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NAIOCannonBalls.started = false;
			}
		});
		btnStop.setBackground(Color.LIGHT_GRAY);
		btnStop.setBounds(197, 49, 62, 23);
		contentPane.add(btnStop);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Home", "Shilo"}));
		comboBox.setBounds(96, 11, 118, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Paint");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NAIOCannonBalls.hidePaint = !NAIOCannonBalls.hidePaint;
			}
		});
		btnNewButton.setBounds(106, 49, 64, 23);
		contentPane.add(btnNewButton);
	}
}
