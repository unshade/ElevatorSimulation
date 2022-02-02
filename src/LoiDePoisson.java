/**
 * Distribution selon la loi de poisson pris dans Donald Knuth,
 * semi-numerical algorithms, pages 117-118.
 * Me faire signe si j'ai fais une erreur (colnet@loria.fr).
 */

public class LoiDePoisson {
    /**
     * Temps moyen entre deux occurrences d'un même événement.
     */
    private long tempsMoyen;

    /**
     * Générateur aléatoire uniforme.
     */
    private PressRandomNumberGenerator random;

    /**
     * Le p de la loi de poisson.
     */
    private double p;

    /**
     * @param germe prendre n'importe quelle valeur plus grande que 0.
     * @param tm    fixe le tempsMoyen entre deux occurrences.
     */
    LoiDePoisson(int germe, long tm) {
        tempsMoyen = tm;
        random = new PressRandomNumberGenerator(germe);
        p = Math.pow(2.7182818284590452353602, (double) tempsMoyen);
        p = 1.0 / p;
    }

    private double next_q(double q) {
        double u;
        u = random.doubleSuivant();
        return q * u;
    }

    /**
     * Donne le temps de l'occurrence suivante.
     */
    public long suivant() {
        long r = 0;
        double q = next_q(1.0);

        while (q > p) {
            r = r + 1;
            q = next_q(q);
        }
        return r;
    }
}
