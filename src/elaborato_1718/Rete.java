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
public class Rete {

    private static Vector<Automa> automi;
    private static Evento[] eventi;
    private static Vector<Cammino> cammini;
    private static String descrizione;
    private static Evento[] link;

    public static void creaRete(String s, Evento[] _link, Evento[] _eventi) {
        descrizione = s;
        automi = new Vector<>();
        eventi = _eventi;
        link = _link;
        cammini = new Vector<>();
    }

   public static void start() {
        impostaStatiIniziali();
        while (true) { //TODO: sostituire il true con una verifica che restituisce true se la rete può scattare
            for (int i = 0; i < automi.size(); i++) {
                if (automi.get(i).isAbilitato()) {
                    automi.get(i).scatta();
                }
            }
        }
    }

    public static void addAutoma(Automa a) {
        automi.add(a);
    }

    public void setEventi(Evento[] eventi) {
        this.eventi = eventi;
    }

    public static Evento getLink(int i) {
        return link[i];
    }
    
    public static void setLink(int i, Evento evento) {
        link[i]=evento;
    }
    

    private static void impostaStatiIniziali() {
        for (Automa automa : automi) {
            automa.setStatoIniziale();
        }
    }
}
