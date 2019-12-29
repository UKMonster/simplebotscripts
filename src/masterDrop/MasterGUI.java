package masterDrop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MasterGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextPane seedList1;
	public static JComboBox<String> locationBox;

	/**
	 * Create the frame.
	 */
	public MasterGUI() {
		setTitle("Master Farmer");
		setBounds(100, 100, 291, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NMasterFarmerDrop.botstarted =1;
			}
		});
		btnNewButton.setBounds(21, 337, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Stop");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NMasterFarmerDrop.botstarted = 0;
			}
		});
		btnNewButton_1.setBounds(165, 337, 89, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblEnterItemsTo = new JLabel("Enter items to keep");
		lblEnterItemsTo.setForeground(Color.WHITE);
		lblEnterItemsTo.setBounds(64, 37, 116, 14);
		contentPane.add(lblEnterItemsTo);
		
		 seedList1 = new JTextPane();
		seedList1.setBackground(Color.LIGHT_GRAY);//rry, i deleted some shit at the top was saying to start the app or something
		seedList1.setText("torstol;\r\ntoadflax;\r\ncadantine;\r\nirit;\r\ndwarf weed;\r\nranarr;\r\nsnapdragon;\r\nkwuarm;\r\navantoe;\r\nmagic;\r\nyew;\r\nmaple;\r\npalm;");
		seedList1.setBounds(35, 62, 161, 264);
		contentPane.add(seedList1);
		
		locationBox = new JComboBox<String>();
		locationBox.setBackground(Color.LIGHT_GRAY);
		locationBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Draynor", "Zeah", "Ardy"}));
		locationBox.setToolTipText("");
		locationBox.setBounds(76, 6, 145, 20);
		contentPane.add(locationBox);
		
		JLabel lblLo = new JLabel("Location");
		lblLo.setForeground(Color.WHITE);
		lblLo.setBounds(10, 9, 48, 14);
		contentPane.add(lblLo);
	}
}
