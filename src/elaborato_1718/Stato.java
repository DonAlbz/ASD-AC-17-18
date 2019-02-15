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

    private Vector<Transizione> transizioni;
    private Transizione transizioneAbilitata;
    private String descrizione;

    public Stato(String s) {
        this.descrizione = s;
        transizioni = new Vector<>();
    }

    boolean isAbilitato() {
        for (int i = 0; i < transizioni.size(); i++) {
            if (transizioni.get(i).isAbilitato()) {
                transizioneAbilitata = transizioni.get(i);
                return true;
            }
        }
        return false;
    }

    Transizione getTransizioneAbilitata() {
        return transizioneAbilitata;
    }

    public Stato scatta(Transizione t) {
        return t.scatta();
    }

    public Stato scatta() {
        return transizioneAbilitata.scatta();
    }

    public void addTransazione(Transizione t) {
        transizioni.add(t);
    }

    public boolean equals(Stato s2) {
        return descrizione.equals(s2.getDescrizione());
    }

    public String toString() {
        return descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

}
