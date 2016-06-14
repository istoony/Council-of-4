package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;


import it.polimi.ingsw.PS19.model.map.City;


public class DrawCity extends JComponent{
	
	private static final int WIDTH = 40;
	private static final int HEIGHT = 40;
	
	private static final String YELLOW = "FFFF00";
	private static final String YELLOW_PATH = "images/yellowcity.png";
	private static final String RED = "FF0000";
	private static final String RED_PATH = "images/redcity.png";
	private static final String BLUE = "0000FF";
	private static final String BLUE_PATH = "images/blucity.png";
	private static final String GREY = "696969";
	private static final String GREY_PATH = "images/greycity.png";
	private static final String VIOLET = "8B008B";
	private static final String VIOLET_PATH = "images/violetcity.png";

	private static final Logger log = Logger.getLogger("IMAGE_READER_LOGGER");
    private BufferedImage image;
//TODO
    protected DrawCity(City c) {
    	setVisible(true);
    	if(c.getCitycolor()==Color.getColor(YELLOW)){
    		try {                
    	          image = ImageIO.read(new File(YELLOW_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor()==Color.getColor(RED)){
    		try {                
    	          image = ImageIO.read(new File(RED_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor()==Color.getColor(BLUE)){
    		try {                
    	          image = ImageIO.read(new File(BLUE_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor()==Color.getColor(GREY)){
    		try {                
    	          image = ImageIO.read(new File(GREY_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
    	
    	else if(c.getCitycolor()==Color.getColor(VIOLET)){
    		try {                
    	          image = ImageIO.read(new File(VIOLET_PATH));
    	       } catch (IOException e) {
    				log.log(Level.SEVERE, e.toString(), e);
    	       }
    	}
       
    }

    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(image, 100, 100, null); // see javadoc for more info on the parameters            
    }
}