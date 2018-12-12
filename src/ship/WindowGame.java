package ship;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * 
 * @author Max
 *
 */
public class WindowGame extends JFrame{
	private static final long serialVersionUID = 1L;
	private int sizeButtonX = 60;
	private int sizeButtonY = 50;
	private JButton[][] buttons = new JButton[14][20];
	private JPanel panel=new JPanel();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuGame = new JMenu("Game");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem itemNewGame = new JMenuItem("New game");
	private JMenuItem itemExit = new JMenuItem("Exit");
	private JMenuItem itemSave = new JMenuItem("Save");
	private JMenuItem itemAbout = new JMenuItem("About");
	private JMenuItem itemLoad = new JMenuItem("Load the Game");
	private ImageIcon image;
	private int positionSaveX=0;
	private int positionSaveY=0;
	;
	
	WindowGame(){
		super("Test Ships v.1.0");
		setNevGame();
		setAddShip();
	}
	
	
	WindowGame(int[] LoadPosition){
		super("Test Ships v.1.0");
		setNevGame();
		setGoChip(positionSaveX,positionSaveY,LoadPosition[0],LoadPosition[1]);
	}
	
	private void setNevGame(){
		image = new ImageIcon("boat.gif");
		setIconImage(new ImageIcon("boat.gif").getImage());
		panel.setLayout(null);
		setContentPane(panel);
		setResizable(false);
		setSize(buttons[1].length*sizeButtonX+6,buttons.length*sizeButtonY+52);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMenuBar();
		setAddButtons();
		setVisible(true);
		setLocationRelativeTo(null); // ���� �� ������
		initListeners();
	}
	
	void initListeners() {
		itemNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				setVisible(false); 								// ���������� �� ������ �����
				new WindowGame();
			}
		});
		itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		itemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SqlConector save = new SqlConector();
				String info = save.getSave(positionSaveX, positionSaveY);
				JOptionPane.showMessageDialog(null,""+info,"Save Game",JOptionPane.QUESTION_MESSAGE);
			}
		});
		itemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null,"Creator: Maxim Nemoshenkov;","About",JOptionPane.QUESTION_MESSAGE);	
			}
		});
		itemLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int[] LoadPosition = SqlConector.getLoadGame();
				if (LoadPosition!=null){
					if (buttons.length>LoadPosition[0] && buttons[0].length>LoadPosition[1]) {
				JOptionPane.showMessageDialog(null,"OK","Load the Game",JOptionPane.QUESTION_MESSAGE);
				setGoChip(positionSaveX,positionSaveY,LoadPosition[0],LoadPosition[1]);
					} else { JOptionPane.showMessageDialog(null,"non-existent cell","Eror",JOptionPane.QUESTION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null,"Eror","Load exepsion",JOptionPane.QUESTION_MESSAGE);
				}
			}
		});
	}
	
	private void setMenuBar(){
		  menuBar.add(menuGame);
		  menuBar.add(menuHelp);
		  setJMenuBar(menuBar);
		  menuGame.add(itemNewGame);
		  menuGame.add(itemSave);
		  menuGame.add(itemLoad);
		  menuGame.add(itemExit);
		  menuHelp.add(itemAbout);
	}
	private void setAddButtons(){
		for (int i =0; i < buttons.length; i++){
			for(int j = 0; j< buttons[i].length; j++){
				buttons[i][j] = new JButton();
			    buttons[i][j].setSize(sizeButtonX,sizeButtonY);
			    buttons[i][j].setLocation(sizeButtonX*j,sizeButtonY*i);
			    buttons[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
			    panel.add(buttons[i][j]);
				}
			}
		}
	private void setAddShip(){
		int x =(int)(Math.random()*buttons.length);
		int y =(int)(Math.random()*buttons[1].length);

	    revalidate();
	    remove(buttons[x][y]);
		buttons[x][y] = new JButton(image);
	    buttons[x][y].addMouseListener(new aMouse(x,y));
	    buttons[x][y].setSize(sizeButtonX,sizeButtonY);
	    buttons[x][y].setLocation(sizeButtonX*y,sizeButtonY*x);
	    buttons[x][y].setCursor(new Cursor(Cursor.HAND_CURSOR));
	    panel.add(buttons[x][y]);
	    positionSaveX = x;
	    positionSaveY = y;
	    setContentPane(panel);
	}
	private void setGoChip(int x, int y, int newX, int newY){
		revalidate();
	    remove(buttons[y][x]);
		buttons[y][x] = new JButton();
	    buttons[y][x].setSize(sizeButtonX,sizeButtonY);
	    buttons[y][x].setLocation(sizeButtonX*x,sizeButtonY*y);
	    panel.add(buttons[y][x]);
        buttons[y][x].setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    remove(buttons[newY][newX]);
		buttons[newY][newX] = new JButton(image);
	    buttons[newY][newX].setSize(sizeButtonX,sizeButtonY);
	    buttons[newY][newX].setLocation(sizeButtonX*newX,sizeButtonY*newY);
		buttons[newY][newX].setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttons[newY][newX].addMouseListener(new aMouse(newY,newX));
	    positionSaveX = newX;
	    positionSaveY = newY;
	    panel.add(buttons[newY][newX]);
	    setContentPane(panel);
	}
	
	class aMouse extends java.awt.event.MouseAdapter {
		private int positionX;
		private int positionY;
		private int startX = 0;
		private int startY = 0;
		private int endX = 0;
		private int endY = 0;
		private int deltaX = 0;
		private int deltaY = 0;
		 aMouse(int positionY,int positionX)
		  {
		   this.positionX=positionX;
		   this.positionY=positionY;
		  }
		  public void mousePressed(MouseEvent e)// ��� ������� ������
		  {
		   buttons[positionY][positionX].setCursor(new Cursor(Cursor.MOVE_CURSOR));//������ ������
		   startX=e.getX();
		   startY=e.getY();
		  }
		  public void mouseReleased(MouseEvent e) //��� ���������� ������
		  {
		   buttons[positionY][positionX].setCursor(new Cursor(Cursor.HAND_CURSOR));//������ ������
		   endX=e.getX();
		   endY=e.getY();
		   deltaX=endX-startX;
		   deltaY=endY-startY;
		   if(Math.abs(deltaX)>Math.abs(deltaY)) {
		    if(deltaX>0) {		    	//Right
		    	if(positionX!=buttons[1].length-1){
		    		setGoChip(positionX, positionY, positionX+1, positionY);
		    	}
		    } else { 
		    	if(positionX!=0){ //LEFT
		    		setGoChip(positionX, positionY, positionX-1, positionY);
		    	}
		    } 
		   } else { if(deltaY>0) {	//up
		    
			    	if(positionY!=buttons.length-1){
			    		setGoChip(positionX, positionY, positionX, positionY+1);
			    	}
		   } else {
			   
		    	if(positionY!=0){ //down
		    		setGoChip(positionX, positionY, positionX, positionY-1);
		    	}
		   }
		   }
		  }
	}

}
