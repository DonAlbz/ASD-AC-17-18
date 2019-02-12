/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.Vector;

/**
 *
 * @author Alb
 */
public class Stato {

    private Vector<Transazione> transazioni;
    private Transazione transazioneAbilitata;
    private String descrizione;

    boolean isAbilitato() {
        for (int i = 0; i < transazioni.size(); i++) {
            if (transazioni.get(i).isAbilitato()) {
                transazioneAbilitata = transazioni.get(i);
                return true;
            }
        }
        return false;
    }

    private Transazione getTransazioneAbilitata() {
        return transazioneAbilitata;
    }

    public Stato scatta(Transazione t) {
        return t.scatta();
    }

    public Stato scatta() {
        return transazioneAbilitata.scatta();
    }
}
