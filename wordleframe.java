import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class wordleframe{
	static class Submit implements ActionListener{
		JButton btn;
		JTextField field;
		JLabel label;
		JLabel[] row1;
		JLabel[] row2;
		JLabel[] row3;
		JLabel[] row4;
		JLabel[] row5;
		JLabel[] row6;
		String chosenword;
		JLabel correctlabel;
		JLabel wordlabel;
		Map<Integer, String> map;
		ArrayList<String> list;
		int count;
		public Submit(ArrayList<String> stringlist, Map<Integer, String> minimap, String chosen, JButton button, JTextField textfield, int counter, JLabel label, JLabel correctlabel1, JLabel wordlabel, JLabel[] row1, JLabel[] row2, JLabel[] row3, JLabel[] row4, JLabel[] row5, JLabel[] row6) {
			this.btn = button;
			this.field = textfield;
			this.count = counter;
			this.label = label;
			this.row1 = row1;
			this.row2 = row2;
			this.row3 = row3;
			this.row4 = row4;
			this.row5 = row5;
			this.row6 = row6;
			this.chosenword = chosen;
			this.correctlabel = correctlabel1;
			this.map = minimap;
			this.wordlabel = wordlabel;
			this.list = stringlist;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			this.label.setText("");
			String temp = "";
			boolean istrue = false;
			boolean istrue2 = false;
			for(Map.Entry<Integer, String> entry : map.entrySet()) {
				if(entry.getValue().equals(this.field.getText())) {
					istrue = true;
				}
			}
			for(int x = 0; x<this.list.size(); x++) {
				if(this.list.get(x).equals(this.field.getText())) {
					istrue2 = true;
				}
			}
			if(e.getSource()==this.btn) {
				if(this.field.getText().length()==5 && istrue && !istrue2) {
					temp = this.field.getText();
					this.list.add(temp);
					this.count ++;
				}
				else {
					this.label.setText("The word that has been entered is not a valid five letter word");
				}
				this.field.setText("");
				if(temp.length()!=0) {
					switch(this.count) {
						case 1:
							setrow(temp, this.row1, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							break;
						case 2:
							setrow(temp, this.row2, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							break;
						case 3:
							setrow(temp, this.row3, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							break;
						case 4:
							setrow(temp, this.row4, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							break;
						case 5:
							setrow(temp, this.row5, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							break;
						case 6:
							setrow(temp, this.row6, this.chosenword, this.correctlabel, this.field, this.btn, this.count, this.wordlabel);
							this.field.setEnabled(false);
							this.btn.setEnabled(false);
							break;
					}
				}
			}
		}
	}
	public static void setrow(String text, JLabel[] row, String chosenword, JLabel label, JTextField field, JButton btn, int count, JLabel wordlabel) {
		for(int i = 0; i<text.length(); i++) {
			row[i].setText(text.substring(i,i+1));
		}
		checkrow(chosenword, row, label, field, btn, count, wordlabel);
	}
	
	public static int lettercounter(String chosenword, String chosenletter) { 
		int count = 0; // counts the number of occurrences
		for(int i = 0; i<chosenword.length(); i++) { // goes through each letter of the string variable chosenword
			if(chosenword.substring(i,i+1).equals(chosenletter)) { // if the letter is equal to the string chosenletter
				count ++; // increments count by 1
			}
		}
		return count;
	}
	public static int yellowcounter (String letter, JLabel[] row) {
		int count = 0;
		for(int i = 0; i<row.length; i++) {
			if(row[i].getText().equals(letter)) {
				if(row[i].getBackground()==Color.YELLOW) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static int greencounter (String letter, JLabel[] row) {
		int count = 0;
		for(int i = 0; i<row.length; i++) {
			if(row[i].getText().equals(letter)) {
				if(row[i].getBackground()==Color.GREEN) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void rowisgreen(JLabel[] row, JLabel label, JTextField field, JButton btn, int counter, JLabel wordlabel) {
		int count = 0;
		for(int i = 0; i<row.length; i++) {
			if(row[i].getBackground()==Color.GREEN) {
				count++;
			}
		}
		if(count==5) {
			field.setEditable(false);
			label.setText("Congratulation, you have guessed the word correctly");
			wordlabel.setVisible(true);
			btn.setEnabled(false);
		}
		else if(counter == 6) {
			label.setText("Unfortunately, you have failed to guess the word");
			wordlabel.setVisible(true);
		}
	}
	
	public static void checkrow(String chosenword, JLabel[] row, JLabel label, JTextField field, JButton btn, int count, JLabel wordlabel) {
		for(int i = 0; i<row.length; i++) {
			if(row[i].getText().equals(chosenword.substring(i, i+1))) {
				row[i].setBackground(Color.GREEN);
			}
			else if(lettercounter(chosenword, row[i].getText())==1) {
				if(yellowcounter(row[i].getText(),row)<1) {
					int index = chosenword.indexOf(row[i].getText());
					if(row[index].getText().equals(chosenword.substring(index,index+1))) {
						row[i].setBackground(Color.GRAY);
					}
					else {
						row[i].setBackground(Color.YELLOW);
					}
				}
				else {
					row[i].setBackground(Color.GRAY);
				}
			}
			else if(lettercounter(chosenword, row[i].getText())==2) {
				if(yellowcounter(row[i].getText(),row)+greencounter(row[i].getText(),row)<2) {
					int index1 = chosenword.indexOf(row[i].getText());
					int index2 = chosenword.indexOf(row[i].getText(),index1+1);
					if(row[index1].getText().equals(chosenword.substring(index1, index1+1)) && row[index2].getText().equals(chosenword.substring(index2, index2+1))) {
						row[i].setBackground(Color.GRAY);
						row[index1].setBackground(Color.GREEN);
						row[index2].setBackground(Color.GREEN);
					}
					else if(row[index1].getText().equals(chosenword.substring(index1, index1+1))) {
						row[i].setBackground(Color.YELLOW);
						row[index1].setBackground(Color.GREEN);
					}
					else if(row[index2].getText().equals(chosenword.substring(index2, index2+1))) {
						row[i].setBackground(Color.YELLOW);
						row[index2].setBackground(Color.GREEN);
					}
					else {
						row[i].setBackground(Color.YELLOW);
					}
				}
				else {
					row[i].setBackground(Color.GRAY);
				}
			}
			else if(lettercounter(chosenword, row[i].getText())==3) {
				if(yellowcounter(row[i].getText(),row)+greencounter(row[i].getText(),row)<3) {
					int index1 = chosenword.indexOf(row[i].getText());
					int index2 = chosenword.indexOf(row[i].getText(),index1+1);
					int index3 = chosenword.indexOf(row[i].getText(),index2+1);
					int [] indexes = {index1, index2, index3};
					int counter = 0;
					for(int t = 0; t<indexes.length; t++) {
						if(row[indexes[t]].getText().equals(chosenword.substring(indexes[t], indexes[t]+1))) {
							row[indexes[t]].setBackground(Color.GREEN);
							counter++;
						}
					}
					if(counter==3) {
						row[i].setBackground(Color.GRAY);
					}
					else {
						row[i].setBackground(Color.YELLOW);
					}
				}
				else {
					row[i].setBackground(Color.GRAY);
				}
			}
			else {
				row[i].setBackground(Color.GRAY);
			}
		}
		rowisgreen(row,label,field, btn, count, wordlabel);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<JLabel> list = new ArrayList<JLabel>();
		ArrayList<String> stringlist = new ArrayList<String>();
		String chosenword = "";
		Map<Integer, String> minimap = new TreeMap<Integer, String>();
		try{
			Random random = new Random();
			Scanner scanner = new Scanner(new File("valid-wordle-words.txt"));
			int count = 0; // this integer variable is used to assign key value to all the words that are stored as value in the TreeMap called minimap
			while(scanner.hasNextLine()) { // while the scanner has a nextline to read
				count ++; // increments count by 1
				minimap.put(count, scanner.nextLine()); // adds to minimap the value of count which is the key and the nextline that is read by the scanner which will be the value of the key
			}
			int randint = random.nextInt(count+1); // this variable is used to get a random number between 0 and the last line # of the .txt file
			randint ++; // increment randint by 1 so that we are starting from 1 rather than 0 and this allows randint to act as a key for minimap
			
			chosenword = minimap.get(randint); // chosenword will be intialized a random 5 letter word from the wordle.txt file
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		JFrame frame = new JFrame();
		ImageIcon img = new ImageIcon("wordle (1) (1).png");
		JLabel label = new JLabel("Enter a five letter word: ");
		JLabel label1 = new JLabel("");
		
		JLabel label_row1_1 = new JLabel("_");
		list.add(label_row1_1);
		JLabel label_row2_1 = new JLabel("_");
		list.add(label_row2_1);
		JLabel label_row3_1 = new JLabel("_");
		list.add(label_row3_1);
		JLabel label_row4_1 = new JLabel("_");
		list.add(label_row4_1);
		JLabel label_row5_1 = new JLabel("_");
		list.add(label_row5_1);
		JLabel label_row6_1 = new JLabel("_");
		list.add(label_row6_1);
		JLabel label_row1_2 = new JLabel("_");
		list.add(label_row1_2);
		JLabel label_row2_2 = new JLabel("_");
		list.add(label_row2_2);
		JLabel label_row3_2 = new JLabel("_");
		list.add(label_row3_2);
		JLabel label_row4_2 = new JLabel("_");
		list.add(label_row4_2);
		JLabel label_row5_2 = new JLabel("_");
		list.add(label_row5_2);
		JLabel label_row6_2 = new JLabel("_");
		list.add(label_row6_2);
		JLabel label_row1_3 = new JLabel("_");
		list.add(label_row1_3);
		JLabel label_row2_3 = new JLabel("_");
		list.add(label_row2_3);
		JLabel label_row3_3 = new JLabel("_");
		list.add(label_row3_3);
		JLabel label_row4_3 = new JLabel("_");
		list.add(label_row4_3);
		JLabel label_row5_3 = new JLabel("_");
		list.add(label_row5_3);
		JLabel label_row6_3 = new JLabel("_");
		list.add(label_row6_3);
		JLabel label_row1_4 = new JLabel("_");
		list.add(label_row1_4);
		JLabel label_row2_4 = new JLabel("_");
		list.add(label_row2_4);
		JLabel label_row3_4 = new JLabel("_");
		list.add(label_row3_4);
		JLabel label_row4_4 = new JLabel("_");
		list.add(label_row4_4);
		JLabel label_row5_4 = new JLabel("_");
		list.add(label_row5_4);
		JLabel label_row6_4 = new JLabel("_");
		list.add(label_row6_4);
		JLabel label_row1_5 = new JLabel("_");
		list.add(label_row1_5);
		JLabel label_row2_5 = new JLabel("_");
		list.add(label_row2_5);
		JLabel label_row3_5 = new JLabel("_");
		list.add(label_row3_5);
		JLabel label_row4_5 = new JLabel("_");
		list.add(label_row4_5);
		JLabel label_row5_5 = new JLabel("_");
		list.add(label_row5_5);
		JLabel label_row6_5 = new JLabel("_");
		list.add(label_row6_5);

		JLabel[] row1 = {label_row1_1,label_row1_2,label_row1_3,label_row1_4,label_row1_5}; // assigning JLabel array called row1
		JLabel[] row2 = {label_row2_1,label_row2_2,label_row2_3,label_row2_4,label_row2_5}; // assigning JLabel array called row2
		JLabel[] row3 = {label_row3_1,label_row3_2,label_row3_3,label_row3_4,label_row3_5}; // assigning JLabel array called row3
		JLabel[] row4 = {label_row4_1,label_row4_2,label_row4_3,label_row4_4,label_row4_5}; // assigning JLabel array called row4
		JLabel[] row5 = {label_row5_1,label_row5_2,label_row5_3,label_row5_4,label_row5_5}; // assigning JLabel array called row5
		JLabel[] row6 = {label_row6_1,label_row6_2,label_row6_3,label_row6_4,label_row6_5}; // assigning JLabel array called row6

		int counter = 0;
		JLabel btnlabel = new JLabel("");
		JLabel wordlabel = new JLabel("The word was " + chosenword);
		JTextField field = new JTextField();
		JButton btn = new JButton("Submit");
		JLabel congratslabel = new JLabel("");
		Submit submit = new Submit(stringlist, minimap, chosenword, btn, field, counter, btnlabel, congratslabel, wordlabel, row1, row2, row3, row4, row5, row6);
		Container c = frame.getContentPane();
		
		c.setLayout(null);
		c.add(label);
		c.add(label1);
		c.add(field);
		c.add(btn);
		c.add(btnlabel);
		c.add(congratslabel);
		c.add(wordlabel);
		
		frame.setTitle("Wordle GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(500, 600);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);
		
		congratslabel.setBounds(100, 515, 450, 20);
		label.setBounds(10, 142, 200, 10);
		label1.setBounds(0, 0, 500, 120);
		label1.setIcon(img);
		btnlabel.setBounds(65, 160, 450, 20);
		wordlabel.setBounds(200, 535, 450, 20);
		wordlabel.setVisible(false);
		
		for(JLabel g : list) {
			c.add(g);
			g.setOpaque(true);
			g.setBackground(Color.WHITE);
			g.setFont(new Font("Serif", Font.PLAIN, 20));
			g.setHorizontalAlignment(SwingConstants.CENTER);
			g.setVerticalAlignment(SwingConstants.CENTER);
		}
		
		label_row1_1.setBounds(140, 190, 35, 40);
		label_row1_2.setBounds(190, 190, 35, 40);
		label_row1_3.setBounds(240, 190, 35, 40);
		label_row1_4.setBounds(290, 190, 35, 40);
		label_row1_5.setBounds(340, 190, 35, 40);
		
		label_row2_1.setBounds(140, 245, 35, 40);
		label_row2_2.setBounds(190, 245, 35, 40);
		label_row2_3.setBounds(240, 245, 35, 40);
		label_row2_4.setBounds(290, 245, 35, 40);
		label_row2_5.setBounds(340, 245, 35, 40);
		
		label_row3_1.setBounds(140, 300, 35, 40);
		label_row3_2.setBounds(190, 300, 35, 40);
		label_row3_3.setBounds(240, 300, 35, 40);
		label_row3_4.setBounds(290, 300, 35, 40);
		label_row3_5.setBounds(340, 300, 35, 40);
		
		label_row4_1.setBounds(140, 355, 35, 40);
		label_row4_2.setBounds(190, 355, 35, 40);
		label_row4_3.setBounds(240, 355, 35, 40);
		label_row4_4.setBounds(290, 355, 35, 40);
		label_row4_5.setBounds(340, 355, 35, 40);
		
		label_row5_1.setBounds(140, 410, 35, 40);
		label_row5_2.setBounds(190, 410, 35, 40);
		label_row5_3.setBounds(240, 410, 35, 40);
		label_row5_4.setBounds(290, 410, 35, 40);
		label_row5_5.setBounds(340, 410, 35, 40);
		
		label_row6_1.setBounds(140, 465, 35, 40);
		label_row6_2.setBounds(190, 465, 35, 40);
		label_row6_3.setBounds(240, 465, 35, 40);
		label_row6_4.setBounds(290, 465, 35, 40);
		label_row6_5.setBounds(340, 465, 35, 40);
		
		field.setBounds(160, 139, 200, 20);
		
		btn.setSize(100, 40);
		btn.setBounds(370, 135, 100, 30);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setForeground(Color.BLUE);
		btn.addActionListener(submit);
	}
}
