public class EvenementOuverturePorteCabine extends Evenement {
    /* OPC: Ouverture Porte Cabine
       L'instant précis ou la porte termine de s'ouvrir.
    */

    public EvenementOuverturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("OPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {

        //Cabine de l'immeuble
        Cabine cabine = immeuble.cabine;
        int c;
        assert !cabine.porteOuverte;
        assert (cabine.intention() != '-');
        cabine.porteOuverte = true;
        c = cabine.faireDescendrePassagers(immeuble, date);
        cabine.recalculeIntention(immeuble);
        c += cabine.étage.faireEntrerPassagers(cabine, echeancier);
        if (cabine.intention() != '-') {
            echeancier.ajouter(new EvenementFermeturePorteCabine(date + c * Global.tempsPourEntrerOuSortirDeLaCabine + tempsPourOuvrirOuFermerLesPortes));
        }
        //notYetImplemented();
        // OPC selon c

    }

}
