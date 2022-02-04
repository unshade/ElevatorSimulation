public abstract class Evenement extends Global {
    /* A priori, cette classe ne doit pas être modifiée.
       C'est possible quand même. À vous de juger !
     */
    
    protected long date; // en dixième de secondes
    
    public Evenement(long d) {
	assert d >= 0;
        date = d;
    }
    
    public abstract void traiter(Immeuble immeuble, Echeancier echeancier);
    
    public void affiche(StringBuilder buffer, Immeuble immeuble) {
        buffer.append("[" + date + ",");
        this.afficheDetails(buffer,immeuble);
        buffer.append("]");
    }
    
    public abstract void afficheDetails(StringBuilder buffer, Immeuble immeuble);
    
}
