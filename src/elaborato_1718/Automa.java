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
public class Automa {

    private Vector<Stato> stati;
    private Stato statoCorrente;
    private String descrizione;
    
    public Automa(String s){
        this.descrizione=s;
        stati=new Vector<>();
    }

    boolean isAbilitato() {
        return statoCorrente.isAbilitato();
    }

    void scatta(Transizione t) {
        this.statoCorrente = statoCorrente.scatta(t);
    }

    Transizione scatta() {
        this.statoCorrente = statoCorrente.scatta();
        return statoCorrente.getTransizioneAbilitata();
    }
    
    void addStato(Stato s){
        stati.add(s);
    }
    
    void setStatoIniziale(){
        statoCorrente=stati.firstElement();
    }
    
    Stato getStatoCorrente(){
        return statoCorrente;
    }

    Transizione getTransizioneEseguita() {
        return statoCorrente.getTransizioneAbilitata();
    }
}
