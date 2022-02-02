public class Global {

    /*
      Cette classe ne doit pas être modifiee.
      Pour simplifier le code la classe Global est heritee.
      L'unite de temps est en dixième de secondes dans le type long.
      Le codage des fichiers est en UTF8.
      Pour activer assert il faut ajouter l'option -ea avec la commande java.
    */

    static final long tempsPourEntrerOuSortirDeLaCabine = 4;
    /* Il s'agit du temps par personne, si une personne entre et une autre sort,
       il faut compter deux fois cette duree.
    */

    static final long tempsPourOuvrirOuFermerLesPortes = 5;

    static final long tempsPourBougerLaCabineDUnEtage = 8;
    /* La cabine se deplace toujours à vitesse constante. */

    static final int nombreDePlacesDansLaCabine = 4;
    /* Tout est dans le nom. Attention, il faut toujours utiliser les constantes,
       partout dans le reste du code. Sinon, on suppose que la cabine ne dispose
       pas de capteur de poids en cas de surcharge.
    */

    static final int delaiDePatienceAvantSportif = 600;
    /* Temps au bout duquel un passager decide finalement de partir à pieds.
     */

    static final long tempsPourMonterOuDescendreUnEtageAPieds = 20;

    public static boolean modeParfait;
    /* Indique que les passagers on parfaitement compris le système de l'indicateur
       d'intention de la cabine.
    */

    /* Mettre un appel à notYetImplemented dans les parties à programmer plus tard.
       Vous n'avez pas le droit de programmer une partie tant que le code n'est pas
       reellement utilise par les tests.
    */
    public static void notYetImplemented() {
        assert false : "notYetImplemented";
        String s = null;
        s.charAt(0); // Pour forcer le plantage si assert n'est pas en service.
    }

    public static boolean isModeParfait() {
        return modeParfait;
    }

}
