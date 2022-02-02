import java.util.ArrayList;

public class Etage extends Global {
    /* Dans cette classe, vous pouvez ajouter/enlever/modifier/corriger les methodes, mais vous ne
       pouvez pas ajouter des attributs (variables d'instance).
    */

    private int numero;
    /* Le numero de l'Etage du point de vue de l'usager (et non pas l'index correspondant
       dans le tableau.
    */

    private Immeuble immeuble;
    /* Back-pointer vers l'immeuble correspondant.
     */

    private LoiDePoisson poissonFrequenceArrivee;
    /* Pour cet etage.
     */

    private ArrayList<Passager> passagers = new ArrayList<Passager>();
    /* Les passagers qui attendent devant la porte et qui espèrent pouvoir monter
       dans la cabine.
       Comme toute les collections, il ne faut pas l'exporter.
    */

    private ArrayList<Passager> pietons = new ArrayList<Passager>();
    /* Les passager qui sont à pieds et qui sont actuellement arrives ici.
       Comme toute les collections, il ne faut pas l'exporter.
    */

    public Etage(int n, int fa, Immeuble im) {
        numero = n;
        immeuble = im;
        int germe = n << 2;
        if (germe <= 0) {
            germe = -germe + 1;
        }
        poissonFrequenceArrivee = new LoiDePoisson(germe, fa);
    }

    public void afficheDans(StringBuilder buffer) {
        if (numero() >= 0) {
            buffer.append(' ');
        }
        buffer.append(numero());
        if (this == immeuble.cabine.etage) {
            buffer.append(" C ");
            if (immeuble.cabine.porteOuverte) {
                buffer.append("[  ]: ");
            } else {
                buffer.append(" [] : ");
            }
        } else {
            buffer.append("   ");
            buffer.append(" [] : ");
        }
        int i = 0;
        while (((buffer.length() < 50) && (i < passagers.size()))) {
            passagers.get(i).afficheDans(buffer);
            i++;
            buffer.append(' ');
        }
        if (i < passagers.size()) {
            buffer.append("...(");
            buffer.append(passagers.size());
            buffer.append(')');
        }
        while (buffer.length() < 80) {
            buffer.append(' ');
        }
        buffer.append("| ");
        i = 0;
        while (((buffer.length() < 130) && (i < pietons.size()))) {
            pietons.get(i).afficheDans(buffer);
            i++;
            buffer.append(' ');
        }
        if (i < pietons.size()) {
            buffer.append("...(");
            buffer.append(pietons.size());
            buffer.append(')');
        }
    }

    public int numero() {
        return this.numero;
    }

    public void ajouter(Passager passager) {
        assert passager != null;
        passagers.add(passager);
    }

    public long arriveeSuivante() {
        return poissonFrequenceArrivee.suivant();
    }

    public boolean aDesPassagersQuiMontent() {
        for (Passager p : passagers) {
            if (p.sens() == '^') {
                return true;
            }
        }
        return false;
    }

    public boolean aDesPassagersQuiDescendent() {
        for (Passager p : passagers) {
            if (p.sens() == 'v') {
                return true;
            }
        }
        return false;
    }

    public boolean aDesPassagers() {
        return (!passagers.isEmpty());
    }

}
