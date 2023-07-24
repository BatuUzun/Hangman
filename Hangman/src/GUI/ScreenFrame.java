package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SystemClasses.DatabaseSys;
import SystemClasses.WordSys;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScreenFrame extends JFrame {

	private JPanel contentPane;
	private ImageIcon head = new ImageIcon("images/head.png");
	private ImageIcon platform = new ImageIcon("images/platform.png");
	private ImageIcon arms = new ImageIcon("images/arms.png");
	private ImageIcon body = new ImageIcon("images/body.png");
	private ImageIcon leftLeg = new ImageIcon("images/leftleg.png");
	private ImageIcon rightLeg = new ImageIcon("images/rightleg.png");
	private JTextField letterField;
	private JTextField[] textField = null;
	private String word;
	private ArrayList<Integer> occurrences= new ArrayList<Integer>();
	private int l = 0;
	private int w = 0;
	private int hint = 0;
	private JLabel headLbl;
	private JLabel leftArmLbl;
	private JLabel bodyLbl;
	private JLabel rightArmLbl;
	private JLabel leftLegLbl;
	private JLabel rightLegLbl;
	private JLabel winOrLooseLbl;
	private ArrayList<Integer> emptyLetters = new ArrayList<Integer>();
	private JButton hintButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenFrame frame = new ScreenFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScreenFrame(Connection con) {
		setTitle("HANGMAN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 643);
		contentPane = new JPanel();
		contentPane.setName("");
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		headLbl = new JLabel("");
		headLbl.setBounds(131, 79, 150, 122);
		contentPane.add(headLbl);
		headLbl.setIcon(head);
		
		
		leftArmLbl = new JLabel("");
		leftArmLbl.setBounds(104, 203, 80, 100);
		contentPane.add(leftArmLbl);
		leftArmLbl.setIcon(arms);
		
		bodyLbl = new JLabel("");
		bodyLbl.setBounds(181, 130, 20, 248);
		contentPane.add(bodyLbl);
		bodyLbl.setIcon(body);
		
		rightArmLbl = new JLabel("");
		rightArmLbl.setBounds(181, 203, 80, 100);
		contentPane.add(rightArmLbl);
		rightArmLbl.setIcon(arms);
		
		JLabel platformLbl = new JLabel("");
		platformLbl.setBounds(52, 24, 434, 73);
		contentPane.add(platformLbl);
		platformLbl.setIcon(platform);
		
		leftLegLbl = new JLabel("");
		leftLegLbl.setBounds(131, 326, 100, 100);
		contentPane.add(leftLegLbl);
		leftLegLbl.setIcon(leftLeg);
		
		rightLegLbl = new JLabel("");
		rightLegLbl.setBounds(175, 352, 67, 48);
		contentPane.add(rightLegLbl);
		rightLegLbl.setIcon(rightLeg);
		
		setVisibleFalse();
		
		hintButton = new JButton("HINT");
		hintButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hint++;
				if(hint != 0) {
					hintButton.setEnabled(false);
				}
				for(int i=0;i<textField.length;i++) {
					if(textField[i].getText().isEmpty()) {
						emptyLetters.add(i);
					}
				}
				int fillThisIndex = (int) (Math.random()*emptyLetters.size());
				
				Integer[] listToArray = emptyLetters.toArray(new Integer[emptyLetters.size()]);
				textField[listToArray[fillThisIndex]].setText(String.valueOf(String.valueOf(word.charAt(listToArray[fillThisIndex]))));
				w++;
				textField[fillThisIndex].setOpaque(true);
				textField[listToArray[fillThisIndex]].setBackground(Color.cyan);
				if(w == word.length()) {
					winOrLooseLbl.setText("YOU WIN!");winOrLooseLbl.setForeground(Color.green);
				}
				else {
					drawMan();
				}
			}
		});
		hintButton.setBounds(461, 336, 123, 23);
		contentPane.add(hintButton);
		
		JButton newButton = new JButton("NEW WORD");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				l = 0;
				w = 0;
				hint = 0;
				letterField.setText("");
				emptyLetters = new ArrayList<Integer>();
				hintButton.setEnabled(true);
				setVisibleFalse();
				for(int i = 0;i<word.length();i++) {
					textField[i].setVisible(false);
					
				}
				word = DatabaseSys.selectWord(con);
				System.out.println(word);
				textField = new JTextField[word.length()];
				
				winOrLooseLbl.setText("");
				winOrLooseLbl.setBackground(Color.white);
				
				drawTextFields();
			}
		});
		newButton.setBounds(461, 289, 123, 23);
		contentPane.add(newButton);
		
		JLabel lblNewLabel = new JLabel("Enter a letter: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(429, 407, 100, 30);
		contentPane.add(lblNewLabel);
		
		word = DatabaseSys.selectWord(con);
		System.out.println(word);
		textField = new JTextField[word.length()];
		drawTextFields();
		
		winOrLooseLbl = new JLabel("");
		winOrLooseLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		winOrLooseLbl.setBackground(new Color(255, 255, 255));
		winOrLooseLbl.setForeground(new Color(0, 0, 0));
		winOrLooseLbl.setBounds(429, 108, 133, 66);
		contentPane.add(winOrLooseLbl);
		
		letterField = new JTextField();
		letterField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(!letterField.getText().isEmpty() && e.getKeyCode() != KeyEvent.VK_ENTER) {
					letterField.setText(letterField.getText().substring(0, 0));
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER && winOrLooseLbl.getText().isEmpty()){
					if(!letterField.getText().equalsIgnoreCase("")) {
						occurrences = WordSys.doesInclude(word, letterField.getText().toLowerCase());
						
						if(!word.contains(letterField.getText()))
						{
							drawMan();
						
						}
						
						
						
					}
				
					for(int i = 0;i < occurrences.size(); i++) {
						if(textField[occurrences.get(i)].getText().isEmpty()) {
							w ++;;
						}
						
						textField[occurrences.get(i)].setText(letterField.getText().toLowerCase());
						
					}
					
					if(w == word.length()) {
						winOrLooseLbl.setText("YOU WIN!");winOrLooseLbl.setForeground(Color.green);
						
							hintButton.setEnabled(false);
						
					}
					
					
				}
			}
		});
		
		
		letterField.setBounds(542, 404, 49, 33);
		contentPane.add(letterField);
		letterField.setColumns(10);
		winOrLooseLbl.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		
		
		
		
		
		
	}
	void drawMan(){
		switch(l) {
		case 0:headLbl.setVisible(true);break;
		case 1:bodyLbl.setVisible(true);break;
		case 2:leftArmLbl.setVisible(true);break;
		case 3:rightArmLbl.setVisible(true);break;
		case 4:leftLegLbl.setVisible(true);break;
		case 5:rightLegLbl.setVisible(true);winOrLooseLbl.setText("YOU LOST!");winOrLooseLbl.setForeground(Color.red);							
			   hintButton.setEnabled(false);
			   for(int a= 0;a<textField.length;a++) {
				   if(textField[a].getText().isEmpty()) {
					   textField[a].setText(String.valueOf(word.charAt(a)));
					   textField[a].setOpaque(true);
					   textField[a].setBackground(Color.red);
				   }									  									   
			   }
		break;
		}
	l++;
	}
	
	void setVisibleFalse() {
		headLbl.setVisible(false);
		bodyLbl.setVisible(false);
		leftArmLbl.setVisible(false);
		leftLegLbl.setVisible(false);
		rightArmLbl.setVisible(false);
		rightLegLbl.setVisible(false);
	}
	void drawTextFields() {
		for(int i = 0;i<word.length();i++) {
			textField[i] = new JTextField();
			textField[i].setBounds(36 +i*20, 486, 20, 20);
			textField[i].setColumns(10);
			textField[i].setEditable(false);
			contentPane.add(textField[i]);
			textField[i].setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
}
