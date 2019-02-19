/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.ArrayList;

/**
 *
 * @author Alb
 */
public class Cammino {

    private ArrayList<StatoRete> cammino;

    public Cammino() {
        cammino = new ArrayList<>();
    }

    public void add(StatoRete statoRete) {
        cammino.add(statoRete);
    }

    public boolean contains(StatoRete s) {
        return cammino.contains(s);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (StatoRete stato : cammino) {
            s.append(stato.getDescrizione());
            s.append(System.getProperty("line.separator"));
            if (stato.getTransizioneEseguita() != null) {
                s.append(stato.getTransizioneEseguita().toString());
                s.append(System.getProperty("line.separator"));
            }
        }
        return s.toString();
    }

    /*
    public StatoRete duplicaUltimoStato() {
        StatoRete ultimoStatoDuplicato = new StatoRete(cammino.get(cammino.size() - 1).getLink(), cammino.get(cammino.size() - 1).getStati());
        return cammino.set(cammino.size() - 1, ultimoStatoDuplicato);
    }
     */

    /**
     * this ha tutti gli n-1 elementi uguali a camminoAttuale (con stesso
     * indirizzo di memoria, l'ultimo elemento è ancora uguale, ma ha un
     * indirizzo di memoria diverso
     *
     * @param camminoAttuale
     *//*
    void copiaCammino(Cammino camminoAttuale) {
        ArrayList<StatoRete> vecchioCammino = camminoAttuale.getCammino();
        for (int i = 0; i < vecchioCammino.size() - 1; i++) {//copia tutti gli elementi fino al penultimo
            this.cammino.add(vecchioCammino.get(i));
        }
        StatoRete ultimoStato = new StatoRete(vecchioCammino.get(vecchioCammino.size() - 1).getLink(), vecchioCammino.get(vecchioCammino.size() - 1).getStati());
        this.cammino.add(ultimoStato);
    }*/

    void copiaCammino(Cammino camminoAttuale) {
        ArrayList<StatoRete> vecchioCammino = camminoAttuale.getCammino();
        for (int i = 0; i < vecchioCammino.size(); i++) {//copia tutti gli elementi fino al penultimo
            this.cammino.add(vecchioCammino.get(i));
        }        
    }
    
    public StatoRete getUltimoStato() {
        return this.cammino.get(this.cammino.size() - 1);
    }

    public ArrayList<StatoRete> getCammino() {
        return cammino;
    }
}
