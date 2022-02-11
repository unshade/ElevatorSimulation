public class EvenementFermeturePorteCabine extends Evenement {
    /* FPC: Fermeture Porte Cabine
       L'instant précis ou la porte termine de se fermer.
       Tant que la porte n'est pas complètement fermée, il est possible pour un passager
       de provoquer la réouverture de la porte. Dans ce cas le FPC est décalé dans le temps
       selon la méthode decalerFPC qui est dans l'échéancier.
    */

    public EvenementFermeturePorteCabine(long d) {
        super(d);
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("FPC");
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        assert cabine.porteOuverte : "précondition";

        cabine.porteOuverte = false;


        assert (!cabine.porteOuverte) : "postcondition";
        Etage etage = null;

        if (cabine.intention() == 'v') {
            if (cabine.étage != immeuble.étageLePlusBas()) {
                etage = immeuble.étage(cabine.étage.numéro() - 1);
            }
        }
        if (cabine.intention() == '^') {
            if (cabine.étage != immeuble.étageLePlusHaut()) {
                etage = immeuble.étage(cabine.étage.numéro() + 1);
            }

        }

        long dte = date;
        dte += Global.tempsPourBougerLaCabineDUnEtage;
        echeancier.ajouter(new EvenementPassageCabinePalier(dte, etage));
    }


    public void setDate(long d) {
        this.date = d;
    }

}
