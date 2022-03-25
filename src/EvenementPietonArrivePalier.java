public class EvenementPietonArrivePalier extends Evenement {
    /* PAP: Pieton Arrive Palier
       L'instant précis ou un passager qui à décidé de continuer à pieds arrive sur un palier donné.
    */

    private Etage étage;
    private Passager passager;

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("PAP ");
        buffer.append(étage.numéro());
        buffer.append(" #");
        buffer.append(passager.numéroDeCréation);
    }


    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        if (étage != passager.étageDestination()) {
            étage.ajouterPieton(passager);
            étage.supprimerPassager(passager);
            // ajouter le pieton a l etage actuel
            if (passager.sens() == '^') {
                if (!(immeuble.étageLePlusHaut() == immeuble.étage(étage.numéro()))) {
                    étage.supprimerPietonDessous(passager);
                    étage = immeuble.étage(étage.numéro() + 1);
                    echeancier.ajouter(new EvenementPietonArrivePalier(date + tempsPourMonterOuDescendreUnEtageAPieds, étage, passager));
                }
            } else if (passager.sens() == 'v') {
                if (!(immeuble.étageLePlusBas() == immeuble.étage(étage.numéro()))) {
                    étage.supprimerPietonDessus(passager);
                    étage = immeuble.étage(étage.numéro() - 1);
                    echeancier.ajouter(new EvenementPietonArrivePalier(date + tempsPourMonterOuDescendreUnEtageAPieds, étage, passager));
                }
            }
        } else {
            if (passager.sens() == '^') {
                étage.supprimerPietonDessous(passager);
            } else if (passager.sens() == 'v') {
                étage.supprimerPietonDessus(passager);
            }
            étage.supprimerPieton(passager);
        }
    }

    public EvenementPietonArrivePalier(long d, Etage edd, Passager pa) {
        super(d);
        étage = edd;
        passager = pa;
    }

    public Passager getPassager() {
        return this.passager;
    }

}
