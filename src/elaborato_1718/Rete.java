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

    private Vector<Automa> automi;
    private Vector<Evento> eventi;
    private Vector<Cammino> cammini = new Vector<Cammino>();

    void start() {
        while (true) { //TODO: sostituire il true con una verifica che restituisce true se la rete può scattare
            for (int i = 0; i < automi.size(); i++) {
                if (automi.get(i).isAbilitato()) {
                    automi.get(i).scatta();
                }
            }

        }
    }
}
