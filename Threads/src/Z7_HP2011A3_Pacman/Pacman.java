package Z7_HP2011A3_Pacman;

public class Pacman extends SpielFigur {

	FressPunkt derFresspunkt[];
	private int diePunkte;

	public Pacman(FressPunkt[] pFP) {
		derFresspunkt = pFP;
	}

	@Override
	public void zeichne(ZeichenFlaeche pZF) {
		pZF.zeichnePacMan(getPosX(), getPosY());

	}

	public void setzeRichtung(int pRichtung) {
		this.dieRichtung = pRichtung;
	}

	public void loeschePunkte() {
		diePunkte = 0;
	}

	public void aktualisierePunkte() {
		int x, y;
		boolean gleichePos, gefressen = true;

		// Für alle Fresspunkte
		for (int i = 0; i < derFresspunkt.length; i++) {
			x = derFresspunkt[i].getPosX();
			y = derFresspunkt[i].getPosY();

			// Wenn der Pacman auf einen Fresspunkt trifft
			// soll er diesen fressen.
			if (hatGleichePos(x, y)) {
				// Wenn der FressPunkt noch nicht gefressen ist
				if (!derFresspunkt[i].gibGefressen()) {
					// friss ihn
					derFresspunkt[i].setzeGefressen(true);
				}
			}

		}
	
	}

	public int gibPunkte() {
		return diePunkte;
	}

}
