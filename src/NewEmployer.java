import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.management.remote.SubjectDelegationPermission;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class NewEmployer extends JPanel implements FocusListener, ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * y position of label
	 */
	private final int LY = 250;
	
	/**
	 * x position of label
	 */
	private final int LX = 300;
	
	/**
	 * width of the Textfield
	 */
	private final int TW = 150;
	
	/**
	 * height of the TextField
	 */
	private final int TH = 25;
	
	/**
	 * width of the label
	 */
	private final int LW = 95;
	
	/**
	 * height of the label
	 */
	private final int LH = 25;
	
	/**
	 * vertical space of the label
	 */
	private final int LVS = 40;
	
	/**
	 * horizontal space of the label
	 */
	private final int LHS = 30;
	
	/**
	 * width of the button
	 */
	private final int BW = 80;
	
	/**
	 * height of the button
	 */
	private final int BH = 30;
	
	private final int TEXT_EDGE = 30;
	
	private JLabel name_label, surname_label, business_label, phoneNumber_label, image_label, imageText_label;
	private JTextField name_text, surname_text, business_text, phoneNumber_text;
	
	private JButton save_button, paneSave_button, paneCancel_button;
	private JPanel saveButton_panel;
	
	private int currentTextArray = 0;
	private ArrayList<JTextField> textArray;
	
	public NewEmployer() {
		
		setLayout(null);
		
		image_label = new JLabel();
		image_label.setIcon(new ImageIcon("src\\icons\\new_employer.png"));
		image_label.setBounds(LX + 135, 40, 128, 130);
		add(image_label);
		
		imageText_label = new JLabel("NEW EMPLOYER");
		imageText_label.setBounds(425, 180, 150, 30);
		imageText_label.setFont(new Font("SansSerif", Font.PLAIN, 18));
		add(imageText_label);
		
		textArray = new ArrayList<JTextField>();
		
		name_label = new JLabel("Name");
		name_label.setBounds(LX, LY, LW, LH);
		add(name_label);
		
		name_text = new JTextField();
		name_text.setBounds(name_label.getX() + LW + LHS, name_label.getY(), TW, TH);
		name_text.addFocusListener(this);
		textArray.add(name_text);
		name_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(save_button != null) {
					surname_text.requestFocus();
				}
			}
		});
		add(name_text);
		
		surname_label = new JLabel("Surname");
		surname_label.setBounds(name_label.getX(), name_label.getY() + LVS, LW, LH);
		add(surname_label);
		
		surname_text = new JTextField();
		surname_text.setBounds(surname_label.getX() + LW + LHS, surname_label.getY(), TW, TH);
		surname_text.addFocusListener(this);
		textArray.add(surname_text);
		surname_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(save_button != null) {
					business_text.requestFocus();
				}
			}
		});
		add(surname_text);
		
		business_label = new JLabel("Business");
		business_label.setBounds(surname_label.getX(), surname_label.getY() + LVS, LW, LH);
		add(business_label);
		
		business_text = new JTextField();
		business_text.setBounds(business_label.getX() + LW + LHS, business_label.getY(), TW, TH);
		business_text.addFocusListener(this);
		textArray.add(business_text);
		surname_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(save_button != null) {
					phoneNumber_text.requestFocus();
				}
			}
		});
		add(business_text);
		
		phoneNumber_label = new JLabel("Phone Number");
		phoneNumber_label.setBounds(business_label.getX(), business_label.getY() + LVS, LW, LH);
		add(phoneNumber_label);
		
		phoneNumber_text = new JTextField();
		phoneNumber_text.setBounds(phoneNumber_label.getX() + LW + LHS, phoneNumber_label.getY(), TW, TH);
		phoneNumber_text.addFocusListener(this);
		phoneNumber_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(save_button != null) {
					save_button.doClick();
				}
				
				
			}
		});
		textArray.add(phoneNumber_text);
		add(phoneNumber_text);
		
		save_button = new JButton("SAVE");
		save_button.setBounds(phoneNumber_text.getX() + ((TW - BW) / 2), phoneNumber_text.getY() + LH + 20, BW, BH);
		//save_button.setContentAreaFilled(false);
		save_button.setFocusPainted(false);
		save_button.addActionListener(this);
		add(save_button);
		
	}
	
	
	@Override
	public void focusGained(FocusEvent e) {
		((JTextField)e.getSource()).setBorder(new LineBorder(Color.blue));
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		Color color = Color.green;
		if( ((JTextField) e.getSource()).getText().replaceAll("\\s+", "").equals("")) {
			
			color = Color.red;	
			
		} else {
			if(e.getSource() == phoneNumber_text) {
				if( phoneNumberControl(phoneNumber_text.getText())) {
					color = Color.white;
				} else {
					color = Color.red;
				}
			}
		}
			
		((JTextField) e.getSource()).setBorder(new LineBorder(color));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if(		name_text.getText().replaceAll("\\s+", "").equals("") || 
				surname_text.getText().replaceAll("\\s+", "").equals("") || 
				business_text.getText().replaceAll("\\s+", "").equals("") ||
				!phoneNumberControl(phoneNumber_text.getText()) ) {
			
			JOptionPane.showMessageDialog(this, "Please enter the information correctly\ncannot be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
			
		} else {
			
			String text = "\n  Name :\t" + name_text.getText().toUpperCase() + "\n\n" + "  Surname : \t" + surname_text.getText().toUpperCase() + 
					"\n\n" + "  Business : \t" + business_text.getText().toUpperCase() + "\n\n" + "  Phone number :   " + phoneNumber_text.getText().toUpperCase() + "\n";
			Object[] pane = {
					new JLabel("Will be saved as"),
					new JTextArea(text) {
						public boolean isEditable() {
							return false;
						};
					}
				
			};
			
			int result = JOptionPane.showOptionDialog(this, pane, "DOCUMENT", 1, 1, 
					new ImageIcon("src\\icons\\accounting_icon_1_32.png"), new Object[] {"SAVE", "CANCEL"}, "CANCEL");
			
			// System.out.println(result);
			// 0 -> SAVE
			// 1 -> CANCEL
					
			if(result == 0) {
				
				if(DataBase.addEmployer(name_text.getText().toUpperCase(), surname_text.getText().toUpperCase(), 
						business_text.getText().toUpperCase(), phoneNumber_text.getText().toUpperCase())) {
					
					JOptionPane.showMessageDialog(this, "SAVED");
					clearPanel();
					
				} else {
					
					JOptionPane.showMessageDialog(this, "NOT SAVED", "DATABASE ERROR", JOptionPane.ERROR_MESSAGE);
				
				}
			}
			
		}
	}
	
	
	private boolean phoneNumberControl(String phoneNumber) {
		
		if(phoneNumber.equals("")) {
			return false;
		} else if( (phoneNumber.charAt(0) == '0' && phoneNumber.length() == 11) 
				|| (phoneNumber.charAt(0) != '0' && phoneNumber.length() == 10) 
				|| (phoneNumber.charAt(0) == '+' && phoneNumber.length() == 13)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	private void clearPanel() {
		
		name_text.setText("");
		surname_text.setText("");
		business_text.setText("");
		phoneNumber_text.setText("");
		
		name_text.setBorder(new LineBorder(Color.white));
		surname_text.setBorder(new LineBorder(Color.white));
		business_text.setBorder(new LineBorder(Color.white));
		phoneNumber_text.setBorder(new LineBorder(Color.white));
		
	}


	@Override
	public String toString() {
		return "New Employer";
	}
	

}
