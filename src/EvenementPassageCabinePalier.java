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
        Cabine cabine = immeuble.cabine;
        assert !cabine.porteOuverte;
        assert étage.numéro() != cabine.étage.numéro();
        cabine.étage = this.étage;
        Etage etg = null;
        if (cabine.passagersVeulentDescendre()) {
            echeancier.ajouter(new EvenementOuverturePorteCabine(date + Global.tempsPourOuvrirOuFermerLesPortes));

        } else {
            long dte = date;
            if (cabine.intention() == 'v') {
                if (cabine.étage != immeuble.étageLePlusBas()) {
                    etg = immeuble.étage(cabine.étage.numéro() - 1);
                }
            }
            else if (cabine.intention() == '^') {
                if (cabine.étage != immeuble.étageLePlusHaut()) {
                    etg = immeuble.étage(cabine.étage.numéro() + 1);
                }
            }
            dte += Global.tempsPourBougerLaCabineDUnEtage;
            echeancier.ajouter(new EvenementPassageCabinePalier(dte, etg));
        }
    }
}
