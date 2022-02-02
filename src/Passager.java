public class Passager {
    /* Dans cette classe, vous pouvez ajouter/enlever/modifier/corriger des methodes, mais vous ne
       pouvez pas ajouter des attributs (variables d'instance).
       Quand vous serez en mode recherche de vitesse, vous pourrez tout faire, ici et ailleurs.
    */

    public long numeroDeCreation;

    private long dateDepart;

    private Etage etageDepart;

    private Etage etageDestination;

    public long dateDepart() {
        return this.dateDepart;
    }

    public Etage etageDepart() {
        return this.etageDepart;
    }

    public int numeroDepart() {
        return this.etageDepart.numero();
    }

    public Etage etageDestination() {
        return this.etageDestination;
    }

    public int numeroDestination() {
        return this.etageDestination.numero();
    }

    public Passager(long dateDeDepart, Etage etageDeDepart, Immeuble immeuble) {
        Etage niveauDuSol = immeuble.niveauDuSol();
        int nbEtages = immeuble.nbEtages();
        etageDepart = etageDeDepart;
        dateDepart = dateDeDepart;
        compteurGlobalDeCreationDesPassagers++;
        numeroDeCreation = compteurGlobalDeCreationDesPassagers;
        if (etageDepart == niveauDuSol) {
            etageDestination = niveauDuSol;
            while (etageDestination == niveauDuSol) {
                int auPif = randomGenerator.intSuivant(nbEtages);
                etageDestination = immeuble.etage(auPif + immeuble.etageLePlusBas().numero() - 1);
            }
        } else {
            etageDestination = niveauDuSol;
        }
    }

    private static int compteurGlobalDeCreationDesPassagers = 0;

    private static final PressRandomNumberGenerator randomGenerator = new PressRandomNumberGenerator(34);

    public char sens() {
        return (etageDestination.numero() > etageDepart.numero() ? '^' : 'v');
    }

    public void afficheDans(StringBuilder buffer) {
        int depa = etageDepart.numero();
        int dest = etageDestination.numero();
        buffer.append('#');
        buffer.append(numeroDeCreation);
        buffer.append(':');
        buffer.append(depa);
        buffer.append(dest > depa ? '^' : 'v');
        buffer.append(dest);
        buffer.append(':');
        buffer.append(dateDepart);
    }

}
