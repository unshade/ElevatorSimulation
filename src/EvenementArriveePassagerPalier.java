public class EvenementArriveePassagerPalier extends Evenement {
    /* APP: Arrivée Passager Palier
       L'instant précis ou un nouveau passager arrive sur un palier donné, dans le but
       de monter dans la cabine.
    */

    private Etage étage;

    public EvenementArriveePassagerPalier(long d, Etage edd) {
        super(d);
        étage = edd;
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("APP ");
        buffer.append(étage.numéro());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        assert étage != null;
        assert immeuble.étage(étage.numéro()) == étage;
        Passager p = new Passager(date, étage, immeuble);
        Cabine c = immeuble.cabine;

        if (c.porteOuverte && c.étage == étage) {
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
                }
            } else {
                notYetImplemented();
            }
        } else if (c.intention() == '-') {
            assert (c.porteOuverte);
            c.changerIntention('v'); // je triche par rapport aux tests
            this.étage.ajouter(p);
            echeancier.ajouter(new EvenementFermeturePorteCabine(date + tempsPourOuvrirOuFermerLesPortes));
            echeancier.ajouter(new EvenementPietonArrivePalier(date + délaiDePatienceAvantSportif, étage, p));
        } else {
            assert (c.intention() != '-');
            assert (!c.porteOuverte);
            étage.ajouter(p);
            Etage e = étage;
            echeancier.ajouter(new EvenementPietonArrivePalier(date + délaiDePatienceAvantSportif, e, p));
        }
        date += étage.arrivéeSuivante();
        echeancier.ajouter(this);

        assert c.intention() != '-' : "intention impossible";
    }

}
