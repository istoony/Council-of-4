package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.Region;

public class RegionPanel extends JPanel implements MouseListener{

	private static final String TOOLTIP = "Region ";
	protected static final String COLORBG = "#55AE3A";
	private static final String INDENTATION = "    ";
	
	private List<DrawCity> citylist;
	private Region myregion;
	private List<Point> starts;
	private List<Point> ends;
	
	protected RegionPanel(Region re){
    	super();
    	addMouseListener(this);
		citylist = new ArrayList<>();
		starts = new ArrayList<>();
		ends = new ArrayList<>();
		myregion = re;
		setVisible(true);
		setToolTipText(TOOLTIP+(myregion.getType().toString()));
	}
	
	protected void initialize(){
		setBackground(Color.decode(COLORBG));
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new GridLayout(4, 2));
		
		for(City c : myregion.getCities()){
			citylist.add(new DrawCity(c));
		}
		
		for(DrawCity dc : citylist){
			add(dc);
		}
		
		for(DrawCity dc : citylist){
			for(DrawCity dc2 : citylist){
				if(dc.getMycity().getId()==dc2.getMycity().getId()){
					Rectangle r = dc.getBounds();
					starts.add(centerOf(r));
					Rectangle r2 = dc2.getBounds();
					ends.add(centerOf(r2));
					repaint();
				}
			}
		}
	}
	
    protected Point centerOf(Rectangle bounds) {
    	return new Point(bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2));
    }
    
    @Override
	public void paint(Graphics g) {
    	super.paint(g);
    	for(int i=0; i<starts.size(); i++){
    		g.drawLine(starts.get(i).x, starts.get(i).y, ends.get(i).x, ends.get(i).y);
    	}

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame info = createInfo();
		info.setVisible(true);
	}

	private JFrame createInfo(){
		JFrame f = new JFrame("Info of "+myregion.getType().toString());
		f.setSize(400, 250);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new GridLayout(7, 1));
		
		//balcony info
		JLabel balconytitle = new JLabel(INDENTATION+"The council in this region is composed by:");
		JLabel balcony = new JLabel(INDENTATION+myregion.getBalcony().toString());

		
		balconytitle.setVisible(true);
		balcony.setVisible(true);
		f.add(balconytitle);
		f.add(balcony);
		
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
	
	
	
}
