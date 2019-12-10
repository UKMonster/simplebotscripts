package nPlankMaker;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class PlankGui extends JFrame {

	private JPanel contentPane;
	public  JComboBox logsBox;
	public JButton btnStart;
	public JButton btnStop;
	/**
	 * Create the frame.
	 */
	private NPlankMaker script;
	public PlankGui(NPlankMaker script) {
		this.script = script;
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 322, 163);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		logsBox = new JComboBox();
		logsBox.setModel(new DefaultComboBoxModel(new String[] {"Logs", "Oak Logs", "Teak Logs", "Mahogany Logs"}));
		logsBox.setBounds(121, 30, 123, 20);
		contentPane.add(logsBox);

		JLabel lblSelectYourLog = new JLabel("Select your Log type - ");
		lblSelectYourLog.setForeground(Color.WHITE);
		lblSelectYourLog.setBounds(10, 33, 114, 14);
		contentPane.add(lblSelectYourLog);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);
				btnStop.setEnabled(false);
				if(!script.started){
					script.tasks.clear();
					script.tasks.addAll(Arrays.asList(new BankTask(script.ctx, ((String)logsBox.getSelectedItem())), new PlankMakeTask(script.ctx, ((String)logsBox.getSelectedItem()))));// Adds our tasks to our {task} list for execution
					script.started = true;
				}
			}
		});
		btnStart.setBounds(10, 87, 89, 23);
		contentPane.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				script.started = false;
			}
		});
		btnStop.setBounds(182, 87, 89, 23);
		contentPane.add(btnStop);
	}
}
