package it.polimi.ingsw.PS19.client.clientmodel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

/**
 * Interface to update local model
 */
@FunctionalInterface
public interface ClientUpdate
{
	/**
	 * Updates model
	 * @param model: model to be updated
	 */
	public void update(ClientModel model);
}
