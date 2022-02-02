public class EvenementPietonArrivePalier extends Evenement {
    /* PAP: Pieton Arrive Palier
       L'instant precis ou un passager qui à decide de continuer à pieds arrive sur un palier donne.
    */

    private Etage etage;
    private Passager passager;

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("PAP ");
        buffer.append(etage.numero());
        buffer.append(" #");
        buffer.append(passager.numeroDeCreation);
    }


    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        notYetImplemented();
    }

    public EvenementPietonArrivePalier(long d, Etage edd, Passager pa) {
        super(d);
        etage = edd;
        passager = pa;
    }

}
