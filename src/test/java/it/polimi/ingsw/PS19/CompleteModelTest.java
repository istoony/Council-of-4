package it.polimi.ingsw.PS19;

//bitbucket.org/CoF_ps19/ps19.git
public class CompleteModelTest {
	/*
	Scanner in;
	Model m;
	GameController g;
	int turn;
	
	@Test
	public void testModel() 
	{	
		m = new Model(2);
		g = new GameController(m);
		System.out.println(m.toString());
		in = new Scanner(System.in); 
		while(true)
		{
			turn = m.getCurrentState().getPlayerTurnId();
			System.out.println(m.toString());
			System.out.println("CURRENT TURN" + turn);
		/*	for(int i = 0; i<6; i++)
			{
				DrawPoliticsCardMessage message = new DrawPoliticsCardMessage();
				message.setId(turn);
				g.update(null, message);
			}
			System.out.println(m.getPlayerById(turn).toString());
			System.out.println(m.getCurrentState().toString());
			System.out.println("MAIN ACTION: \n"
					+ "1 - Corrompere un consiglio e comprare una business\n"
					+ "2 - Cambiare il colore a un balconcino\n"
					+ "3 - fine del turno");
			int action = in.nextInt();
			if(action == 1)
				drawBusinessCardTest();
			if(action == 2)
				electCouncillorTest();
			if(action == 3)
				fineTurno();
			
			System.out.println(m.toString());
		}
	}
	private void drawBusinessCardTest()
	{
		RegionType region = getRegionCLI();
		int c = in.nextInt();
		BusinessCard card = null;
		if(c == 1)
			card = m.getMap().getRegionByType(region).getFirstcard();
		else 
			card = m.getMap().getRegionByType(region).getSecondcard();

	
		int i = 0;
		ArrayList<Color> polcard = new ArrayList<>();
		while(i != -1)
		{
			System.out.println("BALCONE DELLA REGIONE" + m.getMap().getRegionByType(region).getBalcony().toString());
			System.out.println(m.getPlayerById(turn).toString());
			System.out.println("SCEGLI LA CARTA CHE VUOI SELEZIONARE");
			i = in.nextInt();
			if(i!=-1)
			{
				polcard.add(m.getPlayerById(turn).getPoliticcard().get(i).getColor());
				System.out.println("HAI SCELTO  " +polcard.get(polcard.size() -1).toString());
			}
		}
		
		GetBusinessCardMessage draw = new GetBusinessCardMessage(card, region,polcard);
		draw.setId(turn);
		g.update(null, draw);
	}
	
	private RegionType getRegionCLI() 
	{
		System.out.println("Inserisci la regione\n"
				+ "1 - " + RegionType.PLAIN 
				+ "\n2 - " + RegionType.HILL
				+ "\n3 - " + RegionType.MOUNTAIN);
		int r;
		r = in.nextInt();
		RegionType region = null;
		if(r == 1) 
			region = RegionType.PLAIN;
		if(r == 2) 
			region = RegionType.HILL;
		if(r == 3) 
			region = RegionType.MOUNTAIN;
	
		System.out.println(m.getMap().getRegionByType(region).toString());
		return region;
	}

	private void electCouncillorTest()
	{
		RegionType region = getRegionCLI();
		Color color = getColorCLI();
		ElectCouncillorMessage mess = new ElectCouncillorMessage(color, region);
		mess.setId(turn);
		mess.setMainAction(true);
		g.update(null, mess);
	}
	
	private Color getColorCLI()
	{
		System.out.println("1 - #FFFFFF\n"
				+ "2 - #000000\n"
				+ "3 - #FF7F00\n"
				+ "4 - #0000FF\n"
				+ "5 - #FFC0CB\n"
				+ "6 - #FF0000");
		int scelta = in.nextInt();
		if(scelta == 1)
			return Color.decode("#FFFFFF");
		if(scelta == 2)
			return Color.decode("#000000");
		if(scelta == 3)
			return Color.decode("#FF7F00");
		if(scelta == 4)
			return Color.decode("#0000FF");
		if(scelta == 5)
			return Color.decode("#FFC0CB");
		else
			return Color.decode("#FF0000");
	}
	
	private void fineTurno()
	{
		EndTurnMessage m = new EndTurnMessage();
		m.setId(turn);
		g.update(null, m);
	} */
}
