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
	notYetImplemented();
    }

    public EvenementPietonArrivePalier(long d, Etage edd, Passager pa) {
	super(d);
	étage = edd;
	passager = pa;
    }
    
}
