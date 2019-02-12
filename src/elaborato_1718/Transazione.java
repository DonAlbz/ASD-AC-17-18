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
public class Transazione {

    private Vector<Evento> linkIn;
    private Vector<Evento> linkOut;
    private Stato statoDestinazione;
    private String descrizione;     
    
    boolean isAbilitato() {
        for (Evento link : linkIn) {
            if (link == null) {
                return false;
            }
        }
        for (Evento link : linkOut) {
            if (link != null) {
                return false;
            }
        }
        return true;
    }

    Stato scatta() {
        return statoDestinazione;
    }

}
