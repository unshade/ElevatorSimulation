public class EvenementPassageCabinePalier extends Evenement {
    /* PCP: Passage Cabine Palier
       L'instant précis où la cabine passe juste en face d'un étage précis.
       Vous pouvez modifier cette classe comme vous voulez (ajouter/modifier des méthodes etc.).
    */

    private Etage étage;

    public EvenementPassageCabinePalier(long d, Etage e) {
        super(d);
        étage = e;
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("PCP ");
        buffer.append(étage.numéro());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {

        //Cabine de l'immeuble
        Cabine cabine = immeuble.cabine;

        //Asserssions
        assert !cabine.porteOuverte;
        assert étage.numéro() != cabine.étage.numéro();

        //Actualiser l'étage courrant
        cabine.étage = this.étage;

        //Etage futur
        Etage etg = null;

        //Si les passagers veulent descendre, dans ce cas faire une ouverture de porte
        if (cabine.passagersVeulentDescendre()) {
            echeancier.ajouter(new EvenementOuverturePorteCabine(date + Global.tempsPourOuvrirOuFermerLesPortes));
        }
        //Sinon regarder si la cabine doit monter / descendre et faire un passage de palier
        else {
            if (cabine.intention() == 'v') {

                //Si on doit descendre, regarder qu'on est pas au premier étage (sinon on va sous terre)
                if (cabine.étage != immeuble.étageLePlusBas()) {
                    etg = immeuble.étage(cabine.étage.numéro() - 1);
                }
            } else if (cabine.intention() == '^') {

                //Si on doit monter, regarder qu'on est pas au dernier étage (Sinon on transperce le plafond)
                if (cabine.étage != immeuble.étageLePlusHaut()) {
                    etg = immeuble.étage(cabine.étage.numéro() + 1);
                }
            }

            //Une fois qu'on a sait si on doit monter ou descendre, faire un passage de pallier
            echeancier.ajouter(new EvenementPassageCabinePalier(date + Global.tempsPourBougerLaCabineDUnEtage, etg));
        }
    }
}
