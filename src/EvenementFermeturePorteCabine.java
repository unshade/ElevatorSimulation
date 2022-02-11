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

        //Cabine de l'immeuble
        Cabine cabine = immeuble.cabine;

        //Asserssions
        assert cabine.porteOuverte : "précondition";
        cabine.porteOuverte = false;
        assert (!cabine.porteOuverte) : "postcondition";

        //Etage futur
        Etage etge = null;

        //Faire passer le pallier en fonction de l'intention
        if (cabine.intention() == 'v') {

            //Si on doit descendre, regarder qu'on est pas au premier étage (sinon on va sous terre)
            if (cabine.étage != immeuble.étageLePlusBas()) {
                etge = immeuble.étage(cabine.étage.numéro() - 1);
            }
        }
        if (cabine.intention() == '^') {

            //Si on doit monter, regarder qu'on est pas au dernier étage (Sinon on transperce le plafond)
            if (cabine.étage != immeuble.étageLePlusHaut()) {
                etge = immeuble.étage(cabine.étage.numéro() + 1);
            }
        }

        //Une fois qu'on a sait si on doit monter ou descendre, faire un passage de pallier
        echeancier.ajouter(new EvenementPassageCabinePalier(date + Global.tempsPourBougerLaCabineDUnEtage, etge));
    }


    public void setDate(long d) {
        this.date = d;
    }

}
