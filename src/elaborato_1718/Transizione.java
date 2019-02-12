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
public class Transizione {

    private Evento linkIn; //Link in ingresso
    private Evento eventoRichiesto; //evento richiesto sul link in ingresso
    private Evento[] linkOut; // eventi in uscita, la posizione di ciascun evento indica su quale link viene posizionato
    private Stato statoDestinazione;
    private String descrizione;

    /**
     *
     * @param s
     * @param destinazione
     * @param linkIn
     * @param eventoRichiesto
     * @param linkOut
     *
     */
    public Transizione(String s, Stato destinazione, Evento linkIn,
            Evento eventoRichiesto, Evento[] linkOut) {
        this.descrizione = s;
        this.statoDestinazione = destinazione;
        this.linkIn = linkIn;
        this.eventoRichiesto = eventoRichiesto;
        this.linkOut = linkOut;
    }

    boolean isAbilitato() {
        if (linkIn != eventoRichiesto) {
            return false;
        }

        for (int i = 0; i < linkOut.length; i++) {
            if (linkOut[i] != null) {
                if (Rete.getLink(i) != null) {
                    return false;
                };
            }
        }
        return true;
    }

    Stato scatta() {
        for (int i = 0; i < linkOut.length; i++) {
            if (linkOut[i] != null) {
                Rete.setLink(i, linkOut[i]);
            }
        }
        System.out.print(descrizione);
        return statoDestinazione;
    }
}
