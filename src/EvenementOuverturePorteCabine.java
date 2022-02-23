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


        if(cabine.passagersVeulentDescendre()) cabine.changerIntention('-');
        //Asserssions
        assert !cabine.porteOuverte;

        cabine.porteOuverte = true;

        cabine.faireDescendrePassagers(immeuble, date);


    }

}
