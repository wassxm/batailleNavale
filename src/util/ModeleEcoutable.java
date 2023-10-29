package src.util;


public interface ModeleEcoutable {
   // void modeleMisAJour(AbstractModeleEcoutable model);
    void ajoutEcouteur(EcouteurModele listener);
    void retraitEcouteur(EcouteurModele listener);
}
