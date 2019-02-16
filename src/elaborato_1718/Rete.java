/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.Arrays;
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
    private static Transizione transizioneEseguita;
    private static Transizione transizioneAbilitata;

    public static void creaRete(String s, Evento[] _link, Evento[] _eventi) {
        descrizione = s;
        automi = new Vector<>();
        eventi = _eventi;
        link = _link;
        cammini = new Vector<Cammino>();
    }

    public static void start() {
        System.out.println("numero cammino: " + 0);
        Cammino camminoAttuale = creaNuovoCammino();//il cammino attuale diventa un nuovo cammino con gli stati degli automi e i link azzerati
        int numeroCammino = 0;
        // boolean camminoAttuale;
        boolean almenoUnAutomaAbilitato;
        boolean scattato;
        boolean automaAttualeAbilitato;
        boolean statoFinale=false;

        while (true) { //TODO: sostituire il true con una verifica che restituisce false se sono esauriti i cammini percorribili dalla rete
            // camminoAttuale = false;
            almenoUnAutomaAbilitato = false;
            scattato = false;
            automaAttualeAbilitato = false;            
            StatoRete statoAttuale = creaStatoCorrente();//Stato corrente della rete

            if (!isNuovoStato(camminoAttuale, statoAttuale)|| statoFinale) { //Se all'interno del cammino attuale esiste uno stato uguale a quello corrente
                camminoAttuale.add(statoAttuale);//Lo stato corrente viene aggiunto al cammino (per segnalare il LOOP)
                System.out.println(statoAttuale.getDescrizione());//si stampa lo stato attuale
                
                cammini.add(camminoAttuale);//Il cammino attuale viene aggiunto ai cammini dello spazio comportamentale

                numeroCammino++;//il numero di cammini viene incrementato
                camminoAttuale = creaNuovoCammino();//il cammino attuale diventa un nuovo cammino con tutti gli stati e i link azzerati
                statoFinale=false;//si resetta la variabile che controlla se lo stato attuale è uno stato finale
                //nuovoCammino = true;
                System.out.println();
                System.out.println();
                System.out.println("numero cammino: " + numeroCammino);
                System.out.println();
            } else {
                for (int i = 0; i < automi.size() /*&& !camminoAttuale*/ && !scattato; i++) {//se nessun automa è già scattato, si itera su tutti gli automi
                    automaAttualeAbilitato = automi.get(i).isAbilitato();//true se l'automa attuale ha uno stato con una transizione abilitata
                    almenoUnAutomaAbilitato = almenoUnAutomaAbilitato || automaAttualeAbilitato;//true se almeno uno tra gli automi è abilitato
                    if (automaAttualeAbilitato) {//se l'automa attuale è abilitato
                        transizioneAbilitata = automi.get(i).getTransizioneAbilitata();//transizione abilitata diventa la transizione abilitata allo scatto nell'automa attuale                   
                        transizioneEseguita = automi.get(i).scatta();//l'automa attuale viene fatto scattare e transizione eseguita diventa la transizione che è stata eseguita
                        scattato = true;//true se un automa è scattato
                        statoAttuale.setTransizioneEseguita(transizioneEseguita);//la transizione eseguita viene aggiunta allo StatoRete attuale
                        camminoAttuale.add(statoAttuale);//Lo StatoRete attuale viene aggiunto al cammino attuale
                        System.out.println(statoAttuale.getDescrizione());
                        System.out.println(transizioneEseguita.toString());
                        //StatoRete statoReteDopoLoScatto = creaStatoCorrente();

                    }
                }
                if (!almenoUnAutomaAbilitato) {//se nessuno degli automi è abilitato allo scatto
                    statoFinale=true;//lo StatoRete attuale è uno stato finale
                }
            }
        }
    }

    public static void addAutoma(Automa a) {
        automi.add(a);
    }

    private static StatoRete creaStatoCorrente() {
        Stato[] statoAutomi = new Stato[automi.size()];
        for (int i = 0; i < statoAutomi.length; i++) {
            statoAutomi[i] = automi.get(i).getStatoCorrente();
        }
        return new StatoRete(link.clone(), statoAutomi);
    }

    
    /*private static void addStatoAlCammino(int numeroCammino, StatoRete statoRete) {
        cammini.get(numeroCammino).add(statoRete);
    }*/

    private static boolean isNuovoStato(Cammino camminoAttuale, StatoRete statoRete) {
        return !camminoAttuale.contains(statoRete);
    }

    private static void inizializza() {
        cammini.add(new Cammino());
        impostaStatiIniziali();
    }

    private static void impostaStatiIniziali() {
        Arrays.fill(link, null); // svuota l'array link

        for (Automa automa : automi) {
            automa.setStatoIniziale();
        }

        //addStatoAlCammino(numeroCammino, statoRete);
        //       System.out.println(statoRete.getDescrizione());
    }

    private static Cammino creaNuovoCammino() {
        Cammino nuovoCammino = new Cammino();
        impostaStatiIniziali();
        return nuovoCammino;
    }

    public void setEventi(Evento[] eventi) {
        this.eventi = eventi;
    }

    public static Evento getLink(int i) {
        return link[i];
    }

    public static void setLink(int i, Evento evento) {
        link[i] = evento;
    }

}
