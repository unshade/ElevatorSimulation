import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
  En principe, pas besoin de modifier cette classe.
*/

public class Main extends Global { 
    
    private static boolean assertFlag = false;

    public static void main(String args[]) {
	assert (assertFlag = true);
        System.out.println("Mode de simulation ? (p) parfait ? (i) infernal ? parfait par défaut ?");
        boolean mode = true;
        modeParfait = !readLine().equals("i");
        Echeancier échéancier = new Echeancier();
        Immeuble immeuble = new Immeuble(échéancier);
        int loop = 1;
        int nbPasSimul = 0;
        // Boucle principale du simulateur:
        while ( ! échéancier.estVide() ) {
            if (loop == 1) {
		buffer.setLength(0);
		buffer.append("----- Etat actuel du simulateur (nombre total de pas = ");
		buffer.append(nbPasSimul);
		buffer.append( assertFlag ? ", assert on" : ", assert OFF");
                buffer.append(") -----");
		System.out.println(buffer);
                immeuble.affiche(buffer);
                échéancier.affiche(buffer,immeuble);
                System.out.println("Taper \"Enter\", ou le nombre de pas, ou q pour quitter:");
		String réponse = readLine();
		if ( réponse.equals("q") ) {
		    return;
		} else if (réponse.equals("s")) { // Stop / Secret
		    échéancier.supprimeAPPs();
		};
		loop = parseInt(réponse);
            } else {
                loop--;
            }
            Evenement evenement = échéancier.retourneEtEnlevePremier();
            assert pasDeRetourDansLePassé(evenement.date) : "Retour dans le passé:" + memoDate + "/" + evenement.date;
            evenement.traiter(immeuble, échéancier);
            nbPasSimul++;
        }
        System.out.println("Echéancier vide. Arrêt.");
    }

    private static long memoDate = -1;

    private static boolean pasDeRetourDansLePassé(long nouvelleDate) {
        if (nouvelleDate >= memoDate) {
            memoDate = nouvelleDate;
            return true;
        } else {
            return false;
        }
    }

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    private static String readLine() {
	String result = null;
	try {
            result = input.readLine();
        } catch (Exception e) {
        }
	return result;
    }

    private static int parseInt(String réponse) {
	int result = 1;
	try {
	    result = Integer.parseInt(réponse);
	} catch (Exception e) {
	}
	return result;
    }

    private static StringBuilder buffer = new StringBuilder(1024);
    
}
