package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

import it.polimi.ingsw.ps19.model.map.NobilityPath;

public class NobilityButton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5014022270132926382L;
	protected static final String NOBILITY_COMMAND = "nobility";
	private NobilityPath path;
	
	protected NobilityButton(NobilityPath p){
		super();
		path = p;
		setText("Nobility Path Bonus");
		setVisible(true);
		setMnemonic(KeyEvent.VK_P);
		setToolTipText("Shows the Nobility Path, (p)");
		setEnabled(true);
		setActionCommand(NOBILITY_COMMAND);
	}

	/**
	 * @return the path
	 */
	public NobilityPath getPath() {
		return path;
	}
	
	
	
}
