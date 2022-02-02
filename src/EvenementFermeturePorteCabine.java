public class EvenementFermeturePorteCabine extends Evenement {
    /* FPC: Fermeture Porte Cabine
       L'instant precis ou la porte termine de se fermer.
       Tant que la porte n'est pas complètement fermee, il est possible pour un passager
       de provoquer la reouverture de la porte. Dans ce cas le FPC est decale dans le temps
       selon la methode decalerFPC qui est dans l'echeancier.
    */

    public EvenementFermeturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("FPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        assert cabine.porteOuverte : "precondition";

        //cabine.porteOuverte = false;
        notYetImplemented();

        assert (!cabine.porteOuverte) : "postcondition";
    }


    public void setDate(long d) {
        this.date = d;
    }

}
