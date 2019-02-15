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
        System.out.println("numero cammino: "+ 0);
        inizializza();
        int numeroCammino = 0;
        boolean nuovoCammino;
        boolean almenoUnAutomaAbilitato = false;
        boolean automaAttualeAbilitato = false;
       

        while (true) { //TODO: sostituire il true con una verifica che restituisce true se la rete può scattare
            nuovoCammino = false;
            almenoUnAutomaAbilitato = false;
            for (int i = 0; i < automi.size() && !nuovoCammino; i++) {
                automaAttualeAbilitato = automi.get(i).isAbilitato();
                almenoUnAutomaAbilitato = almenoUnAutomaAbilitato || automaAttualeAbilitato;
                if (automaAttualeAbilitato) {
                    transizioneAbilitata = automi.get(i).getTransizioneAbilitata();
                    //transizioneEseguita = automi.get(i).scatta();
                    transizioneEseguita = automi.get(i).scatta();
                    StatoRete statoRete = creaStatoCorrente();

                    if (isNuovoStato(numeroCammino, statoRete)) {
                        //TODO: non si viene mai generato un nuovo cammino: indagare.
                        addStatoAlCammino(numeroCammino, statoRete);
                        System.out.println(transizioneEseguita.toString());
                        System.out.println(statoRete.getDescrizione());
                    } else {
                        addStatoAlCammino(numeroCammino, statoRete);
                        System.out.println(transizioneEseguita.toString());
                        System.out.println(statoRete.getDescrizione());

                        numeroCammino++;
                        nuovoCammino = true;
                        System.out.println("numero cammino: " + numeroCammino);
                        System.out.println();
                        aggiungiCammino(numeroCammino);
                    }

                }
            }
            if (!almenoUnAutomaAbilitato) {
                numeroCammino++;
                nuovoCammino = true;
                System.out.println();
                System.out.println("numero cammino: " + numeroCammino);
                System.out.println();
                aggiungiCammino(numeroCammino);
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

    /**
     * Aggiunge lo stato corrente della rete, al cammino corrente
     *
     * @param numeroCammino
     */
    private static void addStatoAlCammino(int numeroCammino, StatoRete statoRete) {
        cammini.get(numeroCammino).add(statoRete);
    }

    private static boolean isNuovoStato(int numeroCammino, StatoRete statoRete) {
        return !cammini.get(numeroCammino).contains(statoRete);
    }

    private static void inizializza() {
        cammini.add(new Cammino());
        impostaStatiIniziali(0);
    }

    private static void impostaStatiIniziali(int numeroCammino) {
        Arrays.fill(link, null); // svuota l'array link

        for (Automa automa : automi) {
            automa.setStatoIniziale();
        }

        StatoRete statoRete = creaStatoCorrente();
        addStatoAlCammino(numeroCammino, statoRete);

        System.out.println(statoRete.getDescrizione());
    }

    private static void aggiungiCammino(int numeroCammino) {
        cammini.add(new Cammino());
        impostaStatiIniziali(numeroCammino);
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
