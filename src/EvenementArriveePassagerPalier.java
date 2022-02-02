public class EvenementArriveePassagerPalier extends Evenement {
    /* APP: Arrivee Passager Palier
       L'instant precis ou un nouveau passager arrive sur un palier donne, dans le but
       de monter dans la cabine.
    */

    private Etage etage;

    public EvenementArriveePassagerPalier(long d, Etage edd) {
        super(d);
        etage = edd;
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("APP ");
        buffer.append(etage.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        assert etage != null;
        assert immeuble.etage(etage.numero()) == etage;
        Passager p = new Passager(date, etage, immeuble);
        Cabine c = immeuble.cabine;

        notYetImplemented();
	/* 
	if (c.porteOuverte && c.etage == etage) {
	    if (c.intention() == '-') {
		//notYetImplemented();
		c.changerIntention(p.sens());
		echeancier.ajouter(new EvenementFermeturePorteCabine(date + tempsPourOuvrirOuFermerLesPortes)); 
		char fmp = c.faireMonterPassager(p);
		// Faudrait aussi ajouter le premier PCP...
		if (fmp == 'O') {
		    assert true;
		} else {
		    assert false : "else impossible";
		};	
	    } else {
		notYetImplemented();
	    };
	} else {
	    notYetImplemented();
	};
	//*/

        assert c.intention() != '-' : "intention impossible";
    }

}
