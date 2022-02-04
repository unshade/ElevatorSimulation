public class Cabine extends Global {
    /* Dans cette classe, vous pouvez ajouter/enlever/modifier/corriger les méthodes, mais vous ne
       pouvez pas ajouter des attributs (variables d'instance).
    */
    
    public Etage étage; // actuel, là ou se trouve la Cabine, jamais null.

    public boolean porteOuverte;

    private char intention; // '-' ou 'v' ou '^'

    private Passager[] tableauPassager;
    /* Ceux qui sont actuellement dans la Cabine. On ne décale jamais les élements.
       Comme toute les collections, il ne faut pas l'exporter.
       Quand on cherche une place libre, on fait le parcours de la gauche vers la droite.
     */

    public Cabine(Etage e) {
	assert e != null;
	étage = e;
	tableauPassager = new Passager[nombreDePlacesDansLaCabine];
	porteOuverte = true;
	intention = '-';
    }

    public void afficheDans(StringBuilder buffer) {
	buffer.append("Contenu de la cabine: ");
	for (Passager p: tableauPassager) {
	    if (p == null) {
		buffer.append("null");		
	    } else {
		p.afficheDans(buffer);
	    }
	    buffer.append(' ');
	}
	assert (intention == '-') || (intention == 'v') || (intention == '^');
	buffer.append("\nIntention de la cabine: " + intention);
    }

    /* Pour savoir si le passager p est bien dans la Cabine.
       Attention, c'est assez lent et c'est plutôt une méthode destinée à être 
       utilisée dans les asserts.
    */
    public boolean transporte(Passager p) {
	assert p != null;
	for (int i = tableauPassager.length - 1 ; i >= 0  ; i --) {
	    if (tableauPassager[i] == p) {
		return true;
	    }
	}
	return false;
    }

    public char intention() {
	assert (intention == '-') || (intention == 'v') || (intention == '^');
	return intention;
    }

    public void changerIntention(char s){
	assert (s == '-') || (s == 'v') || (s == '^');
	intention = s;
    }

    public char faireMonterPassager(Passager p) {
	assert p != null;
	assert ! transporte(p);
	if (modeParfait) {
	    if (intention != p.sens()) {
		return 'I'; // Intention
	    }
	}
	for (int i=0 ; i<tableauPassager.length ; i++) {
	    if(tableauPassager[i]==null){
		tableauPassager[i]=p;
		return 'O'; // comme Ok
	    }
	}
	return 'P'; // Plein
    }

    public int faireDescendrePassagers(Immeuble immeuble,long d){
	int c=0;
	int i=tableauPassager.length-1;
	while(i>=0){
	    Passager p = tableauPassager[i];
	    if(p != null){
		assert transporte(p);
		if(p.étageDestination() == étage){
		    immeuble.ajouterCumul(d-p.dateDépart());
		    immeuble.nombreTotalDesPassagersSortis++;
		    tableauPassager[i]=null; 
		    c++;
		}
	    }
	    i--;
	}
	return c;
    }

    public boolean passagersVeulentDescendre(){
	int i=tableauPassager.length-1;
	while(i>=0){
	    Passager p = tableauPassager[i];
	    if(p!=null){
		assert transporte(p);
		if(p.étageDestination() == étage){
		    return true;
		}
	    }
	    i--;
	}
	return false;
    }

}
