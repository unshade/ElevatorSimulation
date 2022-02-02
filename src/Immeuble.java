public class Immeuble extends Global {
    /* Dans cette classe, vous pouvez ajouter/enlever/modifier/corriger les methodes, mais vous ne
       pouvez pas ajouter des attributs (variables d'instance).
    */

    private Etage[] tableauDesEtages;
    /* Les etages, dans l'ordre de leur numerotation. Aucun null dans ce tableau.
       Comme toute les collections, il ne faut pas l'exporter.
    */

    public Cabine cabine; // de l'ascenseur.

    private Etage niveauDuSol; // le niveau 0 en general.

    public long cumulDesTempsDeTransport = 0;

    public long nombreTotalDesPassagersSortis = 0;

    public Etage etageLePlusBas() {
        Etage res = tableauDesEtages[0];
        assert res != null;
        return res;
    }

    public Etage etageLePlusHaut() {
        Etage res = tableauDesEtages[tableauDesEtages.length - 1];
        assert res != null;
        return res;
    }

    public Etage niveauDuSol() {
        assert etageLePlusHaut().numero() >= niveauDuSol.numero();
        assert etageLePlusBas().numero() <= niveauDuSol.numero();
        return niveauDuSol;
    }

    public Immeuble(Echeancier echeancier) {
        Etage e = null;
        tableauDesEtages = new Etage[9];
        int n = -1;
        for (int i = 0; i < tableauDesEtages.length; i++) {
            int fa = 41; // Pour le niveau 0 en dixieme de secondes:
            if (n != 0) {
                fa = fa * (tableauDesEtages.length - 1);
            }
            e = new Etage(n, fa, this);
            tableauDesEtages[i] = e;
            if (n == 0) {
                niveauDuSol = e;
            }
            n++;
        }
        for (int i = 0; i < tableauDesEtages.length; i++) {
            Etage etage = tableauDesEtages[i];
            long date = etage.arriveeSuivante();
            echeancier.ajouter(new EvenementArriveePassagerPalier(date, etage));
        }
        e = etageLePlusHaut();
        cabine = new Cabine(niveauDuSol());
        e = etage(e.numero() - 1);
    }

    public void affiche(StringBuilder buffer) {
        buffer.setLength(0);
        buffer.append("Ascenseur en mode ");
        buffer.append(modeParfait ? "parfait" : "infernal");
        while (buffer.length() < 80) {
            buffer.append(' ');
        }
        buffer.append("| Escalier pour sportifs");
        System.out.println(buffer);
        int i = etageLePlusHaut().numero();
        while (i >= etageLePlusBas().numero()) {
            buffer.setLength(0);
            etage(i).afficheDans(buffer);
            System.out.println(buffer);
            i--;
        }
        buffer.setLength(0);
        cabine.afficheDans(buffer);
        buffer.append("\nCumul des temps de transport: ");
        buffer.append(cumulDesTempsDeTransport);
        buffer.append("\nNombre total des passagers sortis: ");
        buffer.append(nombreTotalDesPassagersSortis);
        buffer.append("\nRatio cumul temps / nb passagers : ");
        buffer.append(nombreTotalDesPassagersSortis == 0 ? 0 : cumulDesTempsDeTransport / nombreTotalDesPassagersSortis);
        System.out.println(buffer);
    }

    public Etage etage(int i) {
        assert etageLePlusBas().numero() <= i : "trop bas" + i;
        assert etageLePlusHaut().numero() >= i : "trop haut" + i;
        Etage res = tableauDesEtages[i - etageLePlusBas().numero()];
        assert res.numero() == i;
        return res;
    }

    public int nbEtages() {
        int res = tableauDesEtages.length;
        assert res == (etageLePlusHaut().numero() - etageLePlusBas().numero() + 1);
        return res;
    }

    public void ajouterCumul(long t) {
        cumulDesTempsDeTransport += t;
    }

    public void ajouterPassagerSorti() {
        nombreTotalDesPassagersSortis++;
    }

    public boolean passagerAuDessus(Etage e) {
        assert e != null;
        for (int i = e.numero() + 1; i <= etageLePlusHaut().numero(); i++) {
            Etage et = etage(i);
            if (et.aDesPassagers())
                return true;
        }
        return false;
    }

    public boolean passagerEnDessous(Etage e) {
        assert e != null;
        for (int i = etageLePlusBas().numero(); i < e.numero(); i++) {
            Etage et = etage(i);
            if (et.aDesPassagers())
                return true;
        }
        return false;
    }
}
