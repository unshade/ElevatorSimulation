public class PressRandomNumberGenerator {
    /*
      Press' standard generator, from the SmartEiffel library. 
    */
    private int germe;

    private int[] iv;

    private int iy;

    PressRandomNumberGenerator(int valeurDuGerme) {
    /*
      Prendre n'importe quelle valeur plus grande que 0 pour initialiser le germe.
	*/
        int i = 1;
        germe = valeurDuGerme;
        minimalNext();
        iv = new int[33];

        while (i <= 7) {
            minimalNext();
            i++;
        }

        i = 32;

        while (i > 0) {
            iv[i] = germe;
            minimalNext();
            i--;
        }

        iy = iv[1];
        next();
    }

    private void next() {
    /*
      Fais avancer sur la valeur aléatoire suivante.
	*/
        int tmp = (iy % 32) + 1;
        minimalNext();
        iy = iv[tmp];
        iv[tmp] = germe;
    }

    public double doubleSuivant() {
    /*
	  Donne le double aléatoire suivant.
	*/
        next();
        return ((double) iy) / 2147483647;
    }

    public int intSuivant(int maxi) {
	/*
	  Donne le int aléatoire suivant dans l'intervalle [1 .. maxi].
	*/
        next();
        return (iy % maxi) + 1;
    }

    private void minimalNext() {
        int k = germe / 127773;
        germe = 16807 * (germe - k * 127773) - 2836 * k;
        if (germe < 0) {
            germe = germe + 2147483647;
        }
    }

}
