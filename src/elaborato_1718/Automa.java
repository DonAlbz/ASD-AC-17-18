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
    private Transizione transizioneAbilitata;
    
    public String getDescrizione() {
        return descrizione;
    }

    public Automa(String s) {
        this.descrizione = s;
        stati = new Vector<>();
    }

    boolean isAbilitato() {
        boolean resp= statoCorrente.isAbilitato();
        if (resp)
            transizioneAbilitata=statoCorrente.getTransizioneAbilitata();
        return resp;
    }

    Transizione scatta(Transizione t) {
        this.statoCorrente = statoCorrente.scatta(t);
        return transizioneAbilitata;
    }

    Transizione scatta() {
        this.statoCorrente = statoCorrente.scatta();
        return transizioneAbilitata;
    }
    
    void addStato(Stato s){
        stati.add(s);
    }
    
    void setStatoIniziale(){
        statoCorrente=stati.firstElement();
        if(statoCorrente.isAbilitato())
            transizioneAbilitata=statoCorrente.getTransizioneAbilitata();
    }
    
    Stato getStatoCorrente(){
        return statoCorrente;
    }

    Transizione getTransizioneAbilitata() {
        return transizioneAbilitata;
    }
    
    /**
     *
     * @param statoDaCercare e' la stringa che identifica lo stato da cercare nell'array 
     * @return ritorna lo stato che si stava cercando, null se lo stato non esiste
     */
    public Stato getStato(String statoDaCercare){
        Stato cercato = null;
        for(int i = 0; i<stati.size(); i++){
            if(statoDaCercare.equalsIgnoreCase(stati.get(i).getDescrizione())){
                cercato = stati.get(i);
            }
        }
        return cercato;
    }

}
