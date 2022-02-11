public class Passager {
    /* Dans cette classe, vous pouvez ajouter/enlever/modifier/corriger des méthodes, mais vous ne
       pouvez pas ajouter des attributs (variables d'instance).
       Quand vous serez en mode recherche de vitesse, vous pourrez tout faire, ici et ailleurs.
    */
    
    public long numéroDeCréation;

    private long dateDépart;

    private Etage étageDépart;

    private Etage étageDestination;

    public long dateDépart() {
	return this.dateDépart;
    }

    public Etage étageDépart() {
	return this.étageDépart;
    }

    public int numéroDepart() {
	return this.étageDépart.numéro();
    }

    public Etage étageDestination() {
	return this.étageDestination;
    }

    public int numéroDestination() {
	return this.étageDestination.numéro();
    }

    public Passager(long dateDeDepart, Etage etageDeDepart, Immeuble immeuble) {
	Etage niveauDuSol = immeuble.niveauDuSol();
	int nbEtages = immeuble.nbEtages();
	étageDépart = etageDeDepart;
	dateDépart = dateDeDepart;
	compteurGlobalDeCreationDesPassagers++;
	numéroDeCréation = compteurGlobalDeCreationDesPassagers;
	if (étageDépart == niveauDuSol) {
	    étageDestination = niveauDuSol;
	    while (étageDestination == niveauDuSol) {
		int auPif = randomGenerator.intSuivant(nbEtages);
		étageDestination = immeuble.étage(auPif + immeuble.étageLePlusBas().numéro() - 1);
	    }
	} else {
	    étageDestination = niveauDuSol;
	}
    }

    private static int compteurGlobalDeCreationDesPassagers = 0;

    private static final PressRandomNumberGenerator randomGenerator = new PressRandomNumberGenerator(34);

    public char sens () {
	return (étageDestination.numéro() > étageDépart.numéro() ? '^' : 'v');
    }

    public void afficheDans(StringBuilder buffer) {
	int depa = étageDépart.numéro();
	int dest = étageDestination.numéro();
	buffer.append('#');
	buffer.append(numéroDeCréation);
	buffer.append(':');
	buffer.append(depa);
	buffer.append(dest > depa ? '^' : 'v');
	buffer.append(dest);
	buffer.append(':');
	buffer.append(dateDépart);
    }
    
}
