package ship;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ship.WindowGame;
/**
 * 
 * @author Maxim Nemoshenkov
 *
 */
public class MenuGame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("New Game");
	private JButton button1 = new JButton("Load the game");
	private JButton button2 = new JButton("Exit game");
	private Image backgroundImage;
	private JPanel panel=new JPanel();
	
	MenuGame() throws IOException{
		setIconImage(new ImageIcon("boat.gif").getImage());
		backgroundImage = ImageIO.read(new File("fon2.jpg"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel.setLayout(null);
		setAddButtons();
		setSize(1000,717);
		setResizable(false);
		setVisible(true);
		setContentPane(panel);
		setLocationRelativeTo(null); // ���� �� ������
		initListeners();
	}
	void initListeners() {
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false);											// ���������� �� ������ �����
				new WindowGame();
			}});
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				int[] LoadPosition = SqlConector.getLoadGame();
				if (LoadPosition!=null){
				JOptionPane.showMessageDialog(null,"OK","Load the Game",JOptionPane.QUESTION_MESSAGE);
				setVisible(false);													// ���������� �� ������ �����
				new WindowGame(LoadPosition);
				} else {
					JOptionPane.showMessageDialog(null,"Eror","Load exepsion",JOptionPane.QUESTION_MESSAGE);
				}
			}});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}
	private void setAddButtons(){
		button.setSize(200, 50);
		button1.setSize(200, 50);
		button2.setSize(200, 50);
		button.setLocation(700,300);
		button1.setLocation(700,350);
		button2.setLocation(700,400);
		panel.add(button);
		panel.add(button1);
		panel.add(button2);
		button1.setVisible(true);
	    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	@Override
	public void paint( Graphics g ) { 		  
		    super.paint(g);
		    g.drawImage(backgroundImage, 3, 25, 994, 717, null);
		  }
}
