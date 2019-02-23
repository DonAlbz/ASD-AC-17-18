/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.ArrayList;
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
    //private static Transizione transizioneAbilitata;

    public static void creaRete(String s, Evento[] _link, Evento[] _eventi) {
        descrizione = s;
        automi = new Vector<Automa>();
        eventi = _eventi;
        link = _link;
        cammini = new Vector<Cammino>();
    }

    public static void start() {

        Cammino camminoAttuale = creaNuovoCammino();//il cammino attuale diventa un nuovo cammino con gli stati degli automi e i link azzerati
        int numeroCammino = 0;
        StatoRete statoRadice = creaStatoCorrente();
        scatta(camminoAttuale, statoRadice, (Vector<Automa>) automi.clone(), link.clone());
        stampaCammini();
        
    }

    private static void scatta(Cammino camminoAttuale, StatoRete statoAttuale, Vector<Automa> _automi, Evento[] _link) {
        boolean almenoUnAutomaAbilitato;        
        boolean automaAttualeAbilitato;       

        if (isNuovoStato(camminoAttuale, statoAttuale)) {//fintanto che si incontra un nuovo StatoRete non finale
            ArrayList<Transizione>[] transizioneAbilitata = new ArrayList[_automi.size()];//Array di ArrayList, nella posizione i dell'array sono contenute le transizioni abilitate per l'automa i
            Transizione transizioneEseguita;
            almenoUnAutomaAbilitato = false;
            automaAttualeAbilitato = false;
            for (int i = 0; i < _automi.size() /*&& !scattato*/; i++) {//se nessun automa è già scattato, si itera su tutti gli automi
                //setRete(statoAttuale);
                automaAttualeAbilitato = _automi.get(i).isAbilitato(_link);//true se l'automa attuale ha uno stato con una transizione abilitata
                almenoUnAutomaAbilitato = almenoUnAutomaAbilitato || automaAttualeAbilitato;//true se almeno uno tra gli automi è abilitato
                transizioneAbilitata[i] = _automi.get(i).getTransizioneAbilitata();//transizione abilitata diventa la transizione abilitata allo scatto nell'automa attuale                   

            }
            for (int i = 0; i < _automi.size(); i++) {
                if (_automi.get(i).isAbilitato(_link)) {//se l'automa attuale è abilitato
                    if (transizioneAbilitata[i] != null && transizioneAbilitata[i].size() == 1) {
                        //setRete(statoAttuale);
                        transizioneEseguita = _automi.get(i).scatta(_link);//l'automa attuale viene fatto scattare e transizione eseguita diventa la transizione che è stata eseguita
                        statoAttuale.setTransizioneEseguita(transizioneEseguita);//la transizione eseguita viene aggiunta allo StatoRete attuale
                        camminoAttuale.add(statoAttuale);//Lo StatoRete prima dello scatto viene aggiunto al cammino attuale                    
                        StatoRete statoDopoLoScatto = creaStatoCorrente(_automi, _link);
                        scatta(camminoAttuale, statoDopoLoScatto, _automi, _link);
                    }
                    if (transizioneAbilitata[i] != null && transizioneAbilitata[i].size() > 1) {//Se lo StatoRete attuale ha più di una transizione abilitata
                        for (int j = 0; j < transizioneAbilitata[i].size(); j++) {//vengono fatte scattare tutte, ognuna su un nuovo cammino
                            //setRete(statoAttuale);
                            Transizione transizioneDaEseguire = transizioneAbilitata[i].get(j);
                            Cammino nuovoCamminoAttuale = new Cammino();//viene creato un nuovo cammino
                            nuovoCamminoAttuale.copiaCammino(camminoAttuale);
                            Vector<Automa> automiAttuali = copiaAutomi(_automi);
                            //vengono creati nuovi automi indipendenti da utilizzare su questo nuovo cammino, clonati da _automi
                            Evento[] linkAttuali = _link.clone();//vengono creati nuovi link indipendenti da utilizzare su questo nuovo cammino
                            StatoRete nuovoStatoAttuale;
                            nuovoStatoAttuale = creaStatoCorrente(automiAttuali, linkAttuali);//viene clonato un nuovo stato corrente
                            transizioneEseguita = automiAttuali.get(i).scatta(transizioneDaEseguire, linkAttuali);//viene fatta scattare la transizione da eseguire
                            nuovoStatoAttuale.setTransizioneEseguita(transizioneEseguita);//la transizione eseguita viene aggiunta allo StatoRete attuale
                            nuovoCamminoAttuale.add(nuovoStatoAttuale);//Lo StatoRete attuale viene aggiunto al cammino attuale                    
                            StatoRete statoDopoLoScatto = creaStatoCorrente(automiAttuali, linkAttuali);//creazione dello stato corrente per la verifica nel while
                            scatta(nuovoCamminoAttuale, statoDopoLoScatto, automiAttuali, linkAttuali);
                        }
                    }
                }
            }
            if (!almenoUnAutomaAbilitato) {//se nessuno degli automi è abilitato allo scatto               
                camminoAttuale.add(statoAttuale);//lo StatoRete attuale è uno stato finale
                cammini.add(camminoAttuale);
//                System.out.println("numero cammino: " + cammini.size());
//                System.out.println();
//                System.out.println(camminoAttuale.toString());
//                System.out.println("");
//                System.out.println("");
            }
        } else {//è il caso del LOOP: lo stato corrente viene aggiunto al cammino (è uguale allo ad un altro stato del cammino)
            camminoAttuale.add(statoAttuale);
            cammini.add(camminoAttuale);
//            System.out.println("numero cammino: " + cammini.size());
//            System.out.println();
//            System.out.println(camminoAttuale.toString());
//            System.out.println("");
//            System.out.println("");
        }
//        cammini.add(camminoAttuale);
//        System.out.println(camminoAttuale.toString());
//        System.out.println("");
//        System.out.println("");
    }

    private static void stampaCammini() {
        for (int i=0; i< cammini.size(); i++) {
            int numeroCammino=i+1;
            System.out.println("numero cammino: " + numeroCammino);
            System.out.println();
            System.out.println(cammini.get(i).toString());
            System.out.println("");
            System.out.println("");
        }
    }

    private static Cammino scattaR(Cammino camminoAttuale) {
        return null;
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

    private static StatoRete creaStatoCorrente(Vector<Automa> _automi, Evento[] _link) {
        Stato[] statoAutomi = new Stato[_automi.size()];
        for (int i = 0; i < statoAutomi.length; i++) {
            statoAutomi[i] = _automi.get(i).getStatoCorrente();
        }
        return new StatoRete(_link.clone(), statoAutomi);
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
            automa.setStatoIniziale(link);
        }

        //addStatoAlCammino(numeroCammino, statoRete);
        //       System.out.println(statoRete.getDescrizione());
    }

    private static Cammino creaNuovoCammino() {
        Cammino nuovoCammino = new Cammino();
        impostaStatiIniziali();
        return nuovoCammino;
    }

    private static void setRete(StatoRete statoAttuale) {
        for (int i = 0; i < automi.size(); i++) {
            automi.get(i).setStatoCorrente(statoAttuale.getStati()[i]);
        }
        link = statoAttuale.getLink().clone();
    }

    /**
     * Clona gli automi passati come parametri, tuttavia gli stati destinazione
     * delle transizioni associati agli stati degli automi, non puntano
     * all'indirizzo dei nuovi stati clonati, ma puntano agli stati "vecchi".
     * Ero riuscito a far puntare gli stati nuovi, ma aveva una computazione
     * temporale di tetha(n^3) Il metodo che clona anche gli indirizzi gli stati
     * destinazione delle transizioni è rimasto commentato nel metodo
     * Automa.copia().
     *
     * @param _automi
     * @return
     */
    private static Vector<Automa> copiaAutomi(Vector<Automa> _automi) {
        Vector<Automa> daRitornare = new Vector<>();
        for (int i = 0; i < _automi.size(); i++) {
            daRitornare.add(_automi.get(i).copia());
        }
        return daRitornare;
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

    /**
     * metodo per testare la rete
     */
    public static void test() {
        link[0] = eventi[0];
        for (Automa automa : automi) {
            automa.setStatoIniziale(link);
        }
        StatoRete statoAutoma0 = creaStatoCorrente();
        System.out.println(statoAutoma0.toString());
        automi.get(0).isAbilitato(link);
        Transizione t = automi.get(0).getTransizioneAbilitata().get(0);
        automi.get(0).scatta(t, link);
        statoAutoma0 = creaStatoCorrente();
        System.out.println(statoAutoma0.toString());
        link[0] = null;
        System.out.println("prova");

    }

}
