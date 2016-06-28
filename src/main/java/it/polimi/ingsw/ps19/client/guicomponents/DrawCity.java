package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
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

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.map.City;


public class DrawCity extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1738220878679574991L;
	private static final String INDENTATION = "    ";
	
	private static final int WWIDTH = 40;
	private static final int HHEIGHT = 40;
	
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
    private Language language;
    JFrame info;

    protected DrawCity(City c, Language l) 
    {
    	super();
    	language = l;
    	addMouseListener(this);
    	setToolTipText(language.getString(c));
    	setVisible(true);
    	setSize(WWIDTH, HHEIGHT);
    	mycity=c;
    	
    	imageLoader();
    	img = new JLabel(new ImageIcon(image));
    	img.setBounds(WWIDTH/2, HHEIGHT/2, WWIDTH, HHEIGHT);
    	setBackground(Color.decode(RegionPanel.COLORBG));
    	add(img);
    	
    }
    


	@Override
	public void mouseClicked(MouseEvent e) {
		if(info!=null){
			info.dispatchEvent(new WindowEvent(info, WindowEvent.WINDOW_CLOSING));
		}
		info = createInfo();
		info.setLocationRelativeTo(this);
		info.setAlwaysOnTop(true);
		info.setVisible(true);
		info.setResizable(false);
	}

	
	protected JFrame createInfo(){
		JFrame f = new JFrame(language.infoCity + ": " + language.getString(mycity));
		f.setSize(400, 250);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new GridLayout(7, 1));
		
		//emporia info
		JLabel emporiatitle = new JLabel(INDENTATION + language.emporiaOfPlayers);
		String s=INDENTATION;
		if(mycity.getEmporia().isEmpty())
			s += language.noEmporia;
		else
		{
			s += language.emporiaOfPlayers + ": ";
			s += mycity.getEmporia().get(0).toString();
			for(int i = 1; i < mycity.getEmporia().size(); i++)
				s = s.concat(", " + mycity.getEmporia().get(i).toString());
		}
		JLabel emporialist = new JLabel(s);
		
		emporiatitle.setVisible(true);
		emporialist.setVisible(true);
		f.add(emporiatitle);
		f.add(emporialist);
		
		//bonus info
		JLabel bonustitle = new JLabel(INDENTATION + language.bonuses + ": ");
		bonustitle.setVisible(true);
		f.add(bonustitle);
		for(Bonus b : mycity.getBonus()){
			JLabel bonus = new JLabel(INDENTATION + language.getString(b));
			bonus.setVisible(true);
			f.add(bonus);
		}
		return f;
		
	}
	
	private void imageLoader(){
    	if(mycity.getCitycolor().equals(Color.decode(YELLOW))){
    		imageLoaderCore(YELLOW_PATH);
    	}
    	
    	else if(mycity.getCitycolor().equals(Color.decode(RED))){ 
    		imageLoaderCore(RED_PATH);
    	}
    	
    	else if(mycity.getCitycolor().equals(Color.decode(BLUE))){
    		imageLoaderCore(BLUE_PATH);
    	}
    	
    	else if(mycity.getCitycolor().equals(Color.decode(GREY))){
    		imageLoaderCore(GREY_PATH);
    	}
    	
    	else if(mycity.getCitycolor().equals(Color.decode(VIOLET))){
    		imageLoaderCore(VIOLET_PATH);
    	}

	}
	
	private void imageLoaderCore(String fname){
		try {               
	          image = ImageIO.read(new File(fname));
	       } catch (IOException e) {
				log.log(Level.SEVERE, e.toString(), e);
	       }
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {
		//not used
	}


	@Override
	public void mouseExited(MouseEvent e) {
		//not used				
	}


	@Override
	public void mousePressed(MouseEvent e) {
		//not used
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		//not used
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