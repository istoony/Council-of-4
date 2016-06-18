package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.PS19.model.map.City;


public class DrawCity extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1738220878679574991L;
	private static final String INDENTATION = "    ";
	
	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;
	
	private static final String YELLOW = "#FFFF00";
	private static final String YELLOW_PATH = "images/yellowcity.png";
	private static final String RED = "#FF0000";
	private static final String RED_PATH = "images/redcity.png";
	private static final String BLUE = "#0000FF";
	private static final String BLUE_PATH = "images/blucity.png";
	private static final String GREY = "#696969";
	private static final String GREY_PATH = "images/greycity.png";
	private static final String VIOLET = "#8B008B";
	private static final String VIOLET_PATH = "images/violetcity.png";

	private static final Logger log = Logger.getLogger("IMAGE_READER_LOGGER");
    private BufferedImage image;
    private JLabel img;
    private City mycity;

    protected DrawCity(City c) {
    	super();
    	addMouseListener(this);
    	setToolTipText(c.getName());
    	setVisible(true);
    	mycity=c;
    	if(c.getCitycolor().equals(Color.decode(YELLOW))){
    		try { 
    	          image = ImageIO.read(new File(YELLOW_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor().equals(Color.decode(RED))){
    		try {      
    	          image = ImageIO.read(new File(RED_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor().equals(Color.decode(BLUE))){
    		try {         
    	          image = ImageIO.read(new File(BLUE_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor().equals(Color.decode(GREY))){
    		try {    
    	          image = ImageIO.read(new File(GREY_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor().equals(Color.decode(VIOLET))){
    		try {               
    	          image = ImageIO.read(new File(VIOLET_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	img = new JLabel(new ImageIcon(image));
    	img.setBounds(WIDTH/2, HEIGHT/2, WIDTH, HEIGHT);
    	setBackground(Color.decode(RegionPanel.COLORBG));
    	add(img);
    }
    


	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame info = createInfo();
		info.setVisible(true);
	}

	
	private JFrame createInfo(){
		JFrame f = new JFrame("Info of "+mycity.getName());
		f.setSize(300, 200);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel emporiatitle = new JLabel(INDENTATION+"The following players have an emporium in "+mycity.getName());
		String s="";
		for(int i=0; i<mycity.getEmporia().size(); i++){
			s+= "player n"+mycity.getEmporia().get(i).intValue();
		}
		JLabel emporialist = new JLabel(s);
		
		f.add(emporiatitle);
		f.add(emporialist);
		
		return f;
		
	}
	
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}


	@Override
	public void mouseExited(MouseEvent e) {
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}


	/**
	 * @return the mycity
	 */
	public City getMycity() {
		return mycity;
	}


	/**
	 * @param mycity the mycity to set
	 */
	public void setMycity(City mycity) {
		this.mycity = mycity;
	}
    
}