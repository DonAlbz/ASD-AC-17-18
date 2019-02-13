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
public class Import {

    static void primoScenario() {

        Automa c2 = new Automa("c2");
        Stato s20 = new Stato("20");
        Stato s21 = new Stato("21");
        Evento e2 = new Evento("e2");
        Evento e3 = new Evento("e3");
        Evento[] eventi = {e2, e3};
        Evento[] link = new Evento[2];
        Rete.creaRete("primo scenario", link, eventi);

        Vector<Evento> eventiIn = new Vector<>();
        Vector<Evento> eventiOut = new Vector<>();

        Evento[] eventoOut = {null, e3};
        Transizione t2a = new Transizione("t2a", s21, 0, e2, eventoOut);
        s20.addTransazione(t2a);

        Evento[] eventot2b = {null, e3};
        Transizione t2b = new Transizione("t2b", s20, -1, null, eventot2b);
        s21.addTransazione(t2b);

        c2.addStato(s20);
        c2.addStato(s21);

        Rete.addAutoma(c2);

        Automa c3 = new Automa("c3");

        Stato s30 = new Stato("30");
        Stato s31 = new Stato("31");

        Evento[] eventit3a = {e2, null};
        Transizione t3a = new Transizione("t3a", s31, -1, null, eventit3a);
        s30.addTransazione(t3a);

        Transizione t3c = new Transizione("t3c", s31, 1, e3, null);
        Transizione t3b = new Transizione("t3b", s31, 1, e3, null);
        s31.addTransazione(t3b);
        s31.addTransazione(t3c);
        
        c3.addStato(s30);
        c3.addStato(s31);

        Rete.addAutoma(c3);
    }

}
