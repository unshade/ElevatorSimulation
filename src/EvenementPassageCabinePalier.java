public class EvenementPassageCabinePalier extends Evenement {
    /* PCP: Passage Cabine Palier
       L'instant precis où la cabine passe juste en face d'un etage precis.
       Vous pouvez modifier cette classe comme vous voulez (ajouter/modifier des methodes etc.).
    */

    private Etage etage;

    public EvenementPassageCabinePalier(long d, Etage e) {
        super(d);
        etage = e;
    }

    public void afficheDetails(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("PCP ");
        buffer.append(etage.numero());
    }

    public void traiter(Immeuble immeuble, Echeancier echeancier) {
        Cabine cabine = immeuble.cabine;
        assert !cabine.porteOuverte;
        assert etage.numero() != cabine.etage.numero();

        notYetImplemented();

    }
}
