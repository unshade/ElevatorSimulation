import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
  En principe, pas besoin de modifier cette classe.
*/

public class Main extends Global {

    private static boolean assertFlag = false;

    public static void main(String args[]) {
        assert (assertFlag = true);
        System.out.println("Mode de simulation ? (p) parfait ? (i) infernal ? parfait par defaut ?");
        boolean mode = true;
        modeParfait = !readLine().equals("i");
        Echeancier echeancier = new Echeancier();
        Immeuble immeuble = new Immeuble(echeancier);
        int loop = 1;
        int nbPasSimul = 0;
        // Boucle principale du simulateur:
        while (!echeancier.estVide()) {
            if (loop == 1) {
                buffer.setLength(0);
                buffer.append("----- Etat actuel du simulateur (nombre total de pas = ");
                buffer.append(nbPasSimul);
                buffer.append(assertFlag ? ", assert on" : ", assert OFF");
                buffer.append(") -----");
                System.out.println(buffer);
                immeuble.affiche(buffer);
                echeancier.affiche(buffer, immeuble);
                System.out.println("Taper \"Enter\", ou le nombre de pas, ou q pour quitter:");
                String reponse = readLine();
                if (reponse.equals("q")) {
                    return;
                } else if (reponse.equals("s")) { // Stop / Secret
                    echeancier.supprimeAPPs();
                }
                ;
                loop = parseInt(reponse);
            } else {
                loop--;
            }
            Evenement evenement = echeancier.retourneEtEnlevePremier();
            assert pasDeRetourDansLePasse(evenement.date) : "Retour dans le passe:" + memoDate + "/" + evenement.date;
            evenement.traiter(immeuble, echeancier);
            nbPasSimul++;
        }
        System.out.println("Echeancier vide. Arrêt.");
    }

    private static long memoDate = -1;

    private static boolean pasDeRetourDansLePasse(long nouvelleDate) {
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

    private static int parseInt(String reponse) {
        int result = 1;
        try {
            result = Integer.parseInt(reponse);
        } catch (Exception e) {
        }
        return result;
    }

    private static StringBuilder buffer = new StringBuilder(1024);

}
