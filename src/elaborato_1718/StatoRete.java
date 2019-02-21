/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author alber
 */
public class StatoRete {

    private Evento[] link;
    private Stato[] stati;
    private String descrizione;
    private Transizione transizioneEseguita;

    public StatoRete(Evento[] link, Stato[] stati) {
        this.link = link;
        this.stati = stati;
        this.descrizione = toString();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < stati.length; i++) {
            s.append(stati[i].toString());
            s.append(" ");
        }
        for (int i = 0; i < link.length; i++) {
            if (link[i] != null) {
                s.append(link[i].toString());
            } else {
                s.append(Parametri.EVENTO_NULLO);
            }
            s.append(" ");
        }
        return s.toString().trim();
    }

    /**
     * Override del metodo per poter implementare il metodo contains() in un
     * arrayList di cammini
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StatoRete)) {
            return false;
        }
        StatoRete stato2 = (StatoRete) o;
        /*
        for (int i = 0; i < link.length; i++) {
            if (!this.link[i].equals(stato2.getLink()[i])) {
                return false;
            }
        }
        for (int i = 0; i < stati.length; i++) {
            if (!this.stati[i].equals(stato2.getStati()[i])) {
                return false;
            }
        }*/
        if (descrizione.equals(stato2.getDescrizione())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(descrizione);
        /*
        int hash = 7;        
        hash = 97 * hash + Arrays.deepHashCode(this.link);
        hash = 97 * hash + Arrays.deepHashCode(this.stati);*/
        return hash;
    }

    public Evento[] getLink() {
        return link;
    }

    public Stato[] getStati() {
        return stati;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setTransizioneEseguita(Transizione transizioneEseguita) {
        this.transizioneEseguita = transizioneEseguita;
    }

    public Transizione getTransizioneEseguita() {
        return transizioneEseguita;
    }
    
    

}
