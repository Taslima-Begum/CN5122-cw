<<<<<<< HEAD

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NewChatGUI extends JFrame implements ActionListener {

	private JPanel contentPane = new JPanel();
	JTextField chatNameField = new JTextField();
	JLabel lblNewLabel = new JLabel("Start a new Chat");
	JButton nextBtn = new JButton("Next");
	JLabel lblEnterChatName = new JLabel("Chat Name:");
	JLabel errorLabel = new JLabel("");
	chatGUI a;
	
	public NewChatGUI() {
		setBounds(100, 100, 552, 438);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		contentPane.setLayout(null);
		lblNewLabel.setBounds(99, 9, 314, 45);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
	
		nextBtn.addActionListener(this);
		nextBtn.setBounds(405, 335, 95, 29);
		contentPane.add(nextBtn);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 80, 468, 208);
		
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
		chatNameField.setBounds(151, 339, 209, 20);
		contentPane.add(chatNameField);
		chatNameField.setColumns(10);
		lblEnterChatName.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblEnterChatName.setBounds(60, 339, 81, 20);
		contentPane.add(lblEnterChatName);
		errorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		errorLabel.setForeground(Color.RED);
		

		errorLabel.setBounds(151, 367, 209, 14);
		contentPane.add(errorLabel);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==nextBtn) {
			if(chatNameField.getText().equals("")) {
				errorLabel.setText("Error! Please enter Chat name");
			}
			else {
				a =new chatGUI(chatNameField.getText());
				chats.addchat(a);
				setVisible(false);
			}
			
		}
	}
=======
package Client;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewChatGUI extends JFrame implements ActionListener {

	private JPanel contentPane = new JPanel();
	private JTextField chatNameField = new JTextField();
	private JLabel lblNewLabel = new JLabel("Start a new Chat");
	private JButton nextBtn = new JButton("Next");
	private JLabel lblEnterChatName = new JLabel("Chat Name:");
	private JLabel errorLabel = new JLabel("");
	private JPanel panel;
	private ArrayList<String> users = new ArrayList<String>();
	private JScrollPane scrollPane = new JScrollPane(panel);
	private String type;

	public NewChatGUI(String type) {
		this.type=type;
		setBounds(100, 100, 552, 438);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(null);
		lblNewLabel.setBounds(99, 9, 314, 45);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		nextBtn.addActionListener(this);
		nextBtn.setBounds(405, 335, 95, 29);
		contentPane.add(nextBtn);

		panel = new JPanel();
		for(String o:MainActivityGUI.onlineUsers) {
			panel.add(new JCheckBox(o));
		}

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(48, 75, 439, 208);
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(scrollPane);

		if(type.equals("group")) {
			chatNameField.setBounds(151, 339, 209, 20);
			contentPane.add(chatNameField);
			lblEnterChatName.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnterChatName.setBounds(60, 339, 81, 20);
			contentPane.add(lblEnterChatName);
		}

		errorLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		errorLabel.setForeground(Color.RED);

		errorLabel.setBounds(151, 367, 209, 14);
		contentPane.add(errorLabel);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==nextBtn) {
			if(type.equals("group")) {
				if(chatNameField.getText().equals("")) {
					errorLabel.setText("Error! Please enter Chat name");
				}
				else {
					getListComponents();
					if(users.size()<2) {
						errorLabel.setText("Please select at least 2 users");
					}
					else {
						chats.addchat(new chatGUI(createChatRoomName(chatNameField.getText()),users));
						setVisible(false);
					}
				}
			}
			if(type.equals("private")) {
				getListComponents();
				if(users.size()!=1) {
					errorLabel.setText("Please select 1 user");
				}
				else {//checks if a chat with the user already exists. if it does then makes chat visible.If not creates new chat
					boolean exists=false;
					for(chatGUI a :chats.listChats) {
						if(a.getChatName().equals(users.get(0))){
							a.setVisible(true);
							exists=true;
							setVisible(false);
						}
					}
					if(!exists) {
						chats.addchat(new chatGUI(createChatRoomName(users.get(0))));
						setVisible(false);
					}
				}
			}
		}
	}
	//gets all selected checkboxes
	public  void getListComponents() {
		users.clear();
		Component[] list =panel.getComponents();
		for(Component s: list) {
			if(((JCheckBox)s).isSelected()) {
				users.add(((JCheckBox) s).getText());
			}
		}
	}

	public static String createChatRoomName(String chatName){
		String fullChat = chatName + getTimeStamp();
		return fullChat;
	}
	//This method creates the timestamp when the class is created
	public static String getTimeStamp(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("_yyyy-MM-dd_HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

>>>>>>> ce77366b3ec70f731b58bae5fa0255ec2e23d551
}