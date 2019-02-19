/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Alb
 */
public class Automa {

    private Vector<Stato> stati;
    private Stato statoCorrente;
    private String descrizione;
    private ArrayList<Transizione> transizioniAbilitate;
    private Transizione transizioneEseguita;

    public String getDescrizione() {
        return descrizione;
    }

    public Automa(String s) {
        this.descrizione = s;
        stati = new Vector<>();
    }

    boolean isAbilitato(Evento[] _link) {
        boolean resp = statoCorrente.isAbilitato(_link);
        if (resp) {
            transizioniAbilitate = statoCorrente.getTransizioniAbilitate();
        }
        return resp;
    }

    Transizione scatta(Transizione t, Evento[] _link) {
        transizioneEseguita = t;
        this.statoCorrente = statoCorrente.scatta(t, _link);
        return t;
    }

    Transizione scatta(Evento[] _link) {
        transizioneEseguita = statoCorrente.getTransizioniAbilitate().get(0);
        this.statoCorrente = statoCorrente.scatta(_link);
        return transizioneEseguita;
    }

    Transizione scatta(int i, Evento[] _link) {
        transizioneEseguita = statoCorrente.getTransizioniAbilitate().get(i);
        this.statoCorrente = statoCorrente.scatta(_link);
        return transizioneEseguita;
    }

    void addStato(Stato s) {
        stati.add(s);
    }

    void setStatoIniziale(Evento[] _link) {
        statoCorrente = stati.firstElement();
        if (statoCorrente.isAbilitato(_link)) {
            transizioniAbilitate = statoCorrente.getTransizioniAbilitate();
        }
    }

    Stato getStatoCorrente() {
        return statoCorrente;
    }

    ArrayList<Transizione> getTransizioneAbilitata() {
        return transizioniAbilitate;
    }

    public void setStatoCorrente(Stato statoCorrente) {
        this.statoCorrente = statoCorrente;
    }

    public Automa copia(){
        Automa daRitornare = new Automa(this.getDescrizione());
        for (Stato s : stati) {
            daRitornare.addStato(new Stato(s.getDescrizione(), s.getTransizioni()));
        }
        daRitornare.setStatoCorrente(this.getStatoCorrente());
        return daRitornare;
    }
}
