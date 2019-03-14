/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

public class Import {
    
    //private static String path = "/Users/Francesco/Documents/Documenti/Esami Magistrale/Algoritmi e Strutture Dati/Progetto/ASD-AC-17-18/src/FileInput/input.txt";
    private static final String path = "./src/FileInput/input.txt";
    private static Vector<String> file;
    
    
    public static void inizializzazioneRete(){
        // Inizializzazione della rete
        System.out.println(Parametri.INIZIALIZZAZIONE_RETE_1);
        System.out.println(Parametri.INIZIALIZZAZIONE_RETE_2);
        System.out.println(Parametri.INIZIALIZZAZIONE_RETE_1);
        Vector<Evento> eventiIn = new Vector<>();
        Vector<Evento> eventiOut = new Vector<>();
        
        // Ciclo di inizializzazione degli eventi
        String[] eventiString = getEventi(file);
        Evento[] eventi = new Evento[eventiString.length];
        for (int j = 0; j < eventi.length; j++) {
            Evento evento = new Evento(eventiString[j]);
            eventi[j] = evento;
        }
        
        // Inizializzazione dei link
        String[] linkString = getLink(file);
        Evento[] link = new Evento[linkString.length];
        
        // Creazione della rete
        Rete.creaRete("primo scenario", link, eventi);
        
        // Ciclo di inizializzazione degli automi
        String[] automi = getAutomi(file);
        for (String automa_str : automi) {
            // aggiunta dell'automa
            Automa automa = new Automa(automa_str);
            Rete.addAutoma(automa);
            System.out.println("Automa: " + automa.getDescrizione());
            
            // aggiunta degli stati di automa
            ArrayList<String> statiAutoma = getStatiDaAutoma(automa, file);
            int i = 1;
            for (String stato_str : statiAutoma) {
                Stato stato = new Stato(stato_str);
                automa.addStato(stato);
                System.out.println("Stato " + i + " : " + stato.getDescrizione());
                i++;
            }
            
            System.out.println("");
        }

        // Inizializzazione delle transizioni per ogni stato
        Evento[] eventoOut = {null, eventi[1]};
        Evento[] eventot2b = {null, eventi[1]};
        Evento[] eventit3a = {eventi[0], null};
        
        Stato s20 = Rete.getAutomi().get(0).getStato("20");
        Stato s21 = Rete.getAutomi().get(0).getStato("21");
        Stato s30 = Rete.getAutomi().get(1).getStato("30");
        Stato s31 = Rete.getAutomi().get(1).getStato("31");
        Evento e2 = eventi[0];
        Evento e3 = eventi[1];
        
        // CICLO INIZIALIZZAZIONE TRANSIZIONI
        for (Automa automa : Rete.getAutomi()) {
            for (Stato stato : automa.getStati()) {
                System.out.println("le transizioni uscenti dallo stato: "+stato.getDescrizione()+" sono: ");
                ArrayList<String> transizioniUscenti = getStatiDiPartenza(stato, automa, file);
                for(String transizione : transizioniUscenti){
                    System.out.println(transizione);
                    if (stato.getDescrizione().equalsIgnoreCase("20")) {
                        Transizione t2a = new Transizione(transizione, s21, 0, e2, eventoOut);
                        stato.addTransazione(t2a);
                    }
                    if (stato.getDescrizione().equalsIgnoreCase("21")) {
                        Transizione t2b = new Transizione(transizione, s20, -1, null, eventot2b);
                        stato.addTransazione(t2b);
                    }
                    if (stato.getDescrizione().equalsIgnoreCase("30")) {
                        Transizione t3a = new Transizione(transizione, s31, -1, null, eventit3a);
                        stato.addTransazione(t3a);
                    }
                    if (stato.getDescrizione().equalsIgnoreCase("31")) {
//                        if(transizione.equalsIgnoreCase("t3c")){
//                            
//                        }
//                        if(transizione.equalsIgnoreCase("t3b")){
//                            
//                        }
                        Transizione t3c = new Transizione("t3c", s31, 1, e3, null);
                        Transizione t3b = new Transizione("t3b", s30, 1, e3, null);
                        stato.addTransazione(t3b);
                        stato.addTransazione(t3c);
                    }
                }

            }

        }

        

        
        
        //METODO VECCHIO   
        
//        for(Automa automa : Rete.getAutomi()) {
//            for (Stato stato : automa.getStati()) {
//                ArrayList<String> transizioniUscenti = getStatiDiPartenza(stato, automa, file);
//                if (transizioniUscenti.size() == 1) {
//                    System.out.println("Automa " + automa.getDescrizione() + " - dallo stato " + stato.getDescrizione() + " la transazione uscente è: " + transizioniUscenti.get(0));
//                    String nomeTransizione = transizioniUscenti.get(0);
//                    String nomeStatoDestinazione = getStatoDestinazioneDiTransizione(automa, transizioniUscenti.get(0), file);
//                    System.out.println("Lo stato di destinazione è: " + nomeStatoDestinazione);
//                    Stato statoDestinazione = automa.getStato(nomeStatoDestinazione);
//                    int posizioneLinkIn = getLinkInDiTransizione(automa, transizioniUscenti.get(0), linkString, file);
//                    System.out.println("Posizione link in: " + posizioneLinkIn);
//                    int indiceEventoRichiesto = getIndiceEventoRichiesto(automa, nomeTransizione, eventi, file);
//                    if (indiceEventoRichiesto == -1) {
//                        System.out.println("Nessun evento richiesto");
//                        String[] eventiInUscitaString = getEventiInUscita(automa, transizioniUscenti.get(0), file);
//                        Evento[] eventiInUscita = getEventiInUscita(eventiString, eventiInUscitaString);
//                        Transizione transizioneDaInserire = new Transizione(nomeTransizione, statoDestinazione, posizioneLinkIn, null, eventiInUscita);
//                        stato.addTransazione(transizioneDaInserire);
//                    } else {
//                        Evento eventoRichiesto = eventi[getIndiceEventoRichiesto(automa, nomeTransizione, eventi, file)];
//                        System.out.println("Evento richiesto: " + eventoRichiesto.getDescrizione());
//                        String[] eventiInUscitaString = getEventiInUscita(automa, transizioniUscenti.get(0), file);
//                        Evento[] eventiInUscita = getEventiInUscita(eventiString, eventiInUscitaString);
//                        Transizione transizioneDaInserire = new Transizione(nomeTransizione, statoDestinazione, posizioneLinkIn, eventoRichiesto, eventiInUscita);
//                        stato.addTransazione(transizioneDaInserire);
//                    }
//                    System.out.println("singolo");
//                } else {
//                    Transizione t3c = new Transizione("t3c", Rete.getAutomi().get(1).getStato("31"), 1, eventi[1], null);
//                    stato.addTransazione(t3c);
//                    Transizione t3b = new Transizione("t3b", Rete.getAutomi().get(1).getStato("30"), 1, eventi[1], null);
//                    stato.addTransazione(t3b);
//                    System.out.println("multiplo");
                    
//                    for(String transizione : transizioniUscenti){
//                        if (stato.getDescrizione().equalsIgnoreCase("31") && transizione.equalsIgnoreCase("t3c")){
//                            Transizione t3c = new Transizione("t3c", Rete.getAutomi().get(1).getStato("31"), 1, eventi[1], null);
//                            stato.addTransazione(t3c);
//                            System.out.println("inserito t3c");
//                        }
//                        if (stato.getDescrizione().equalsIgnoreCase("31") && transizione.equalsIgnoreCase("t3b")){
//                            Transizione t3b = new Transizione("t3b", Rete.getAutomi().get(1).getStato("30"), 1, eventi[1], null);
//                            stato.addTransazione(t3b);
//                            System.out.println("inserito t3b");
//                        }
//                        
//                    }
                    
       
    }
    
    public static void primoScenario() {

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
        Transizione t3b = new Transizione("t3b", s30, 1, e3, null);
        s31.addTransazione(t3b);
        s31.addTransazione(t3c);
        
        c3.addStato(s30);
        c3.addStato(s31);

        Rete.addAutoma(c3);
    }
    
    public static void apriFileTxt() throws FileNotFoundException, IOException {
        File filetxt = new File(path);
        BufferedReader textReader = new BufferedReader(new FileReader(filetxt));

        Vector<String> fileToString = new Vector<>();
        try {
            int i = 0;
            String lineaDaCopiare;
            while ((lineaDaCopiare = textReader.readLine()) != null) {
                fileToString.add(lineaDaCopiare);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        file = fileToString;
        textReader.close();
    }
    
    public static String[] getAutomi(Vector<String> fileInput){
        // sappiamo che gli automi sono posizionati in riga 0 del file
        String daSplittare = fileInput.get(0);
        String[] automi = daSplittare.split("\t");
        return automi;
    }
    
    public static String[] getLink(Vector<String> fileInput){
        // sappiamo che i link sono posizionati in riga 2 del file
        String daSplittare = fileInput.get(2);
        String[] link = daSplittare.split("\t");
        return link;
    }
    
    public static String[] getEventi(Vector<String> fileInput){
        // sappiamo che gli eventi sono posizionati in riga 4 del file
        String daSplittare = fileInput.get(4);
        String[] eventi = daSplittare.split("\t");
        return eventi;
    }
    
    public static ArrayList<String> getStatiDaAutoma(Automa automa, Vector<String> fileInput){
        ArrayList<String> statiDiAutoma = new ArrayList<>();
        for(int i=0; i<fileInput.size(); i++){
            // non devo accettare la riga 2 perché nel file input è l'elenco degli automi
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+1;
                String daSplittare = fileInput.get(j);
                String[] stati = daSplittare.split("\t");
                for (String stringa : stati) {
                    statiDiAutoma.add(stringa);
                }
            }
        }
        return statiDiAutoma;
    }
    
    public static ArrayList<String> getNomiTransizioni(Automa automa, Vector<String> fileInput){
        ArrayList<String> nomiTransizioni = new ArrayList<>();
        for(int i=0; i<fileInput.size(); i++){
            // la posizione delle transazioni è sempre sotto di 3 righe
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+3;
                while(fileInput.get(j).length()!=0){
                    String daSplittare = fileInput.get(j);
                    String[] splittato = daSplittare.split("\t");
                    String transizione = splittato[1];
                    nomiTransizioni.add(transizione);
                    j++;
                }
            }
        }
        return nomiTransizioni;
    }
    
    // METODO AL MOMENTO NON UTILIZZATO
    public static ArrayList<String> getStatiDestinazioneDiTransizione(Automa automa, Vector<String> fileInput){
        ArrayList<String> statiDestinazione = new ArrayList<>();
        for(int i=0; i<fileInput.size(); i++){
            // la posizione delle transazioni è sempre a partire dalla riga 3 del blocco di riferimento dell'automa
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+3;
                while(fileInput.get(j).length()!=0){
                    String daSplittare = fileInput.get(j);
                    String[] splittato = daSplittare.split("\t");
                    String statoDestionazione = splittato[2];
                    statiDestinazione.add(statoDestionazione);
                    j++;
                }
            }
        }
        return statiDestinazione;
    }
    
    /**
     *
     * @param automa automa che contiene tutti gli stati
     * @param nomeTransizione nome della transizione di riferimento per cercare il suo stato di destinazione
     * @param fileInput vector contenente le stringhe del file input.txt
     * @return viene ritornato il nome dello stato destinazione della transizione
     */
    public static String getStatoDestinazioneDiTransizione(Automa automa, String nomeTransizione, Vector<String> fileInput){
        String statoDestinazione = null;
        for(int i = 0; i<fileInput.size(); i++){
            // la posizione delle transazioni è sempre a partire dalla riga 3 del blocco di riferimento dell'automa
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+3;
                while(fileInput.get(j).length()!=0){
                    String daSplittare = fileInput.get(j);
                    String[] splittato = daSplittare.split("\t");
                    String transizioneDaConfrontare = splittato[1];
                    if(transizioneDaConfrontare.equalsIgnoreCase(nomeTransizione)){
                        statoDestinazione = splittato[2];
                    }
                    j++;
                }
            }
        }
        return statoDestinazione;
    }
    
    public static int getIndiceEventoRichiesto(Automa automa, String nomeTransizione, Evento[] elencoEventi, Vector<String> fileInput) {
        int indiceEventoRichiesto = -1;
        for (int i = 0; i < fileInput.size(); i++) {
            if (fileInput.get(i).equals(automa.getDescrizione()) && i != 2) {
                while (!fileInput.get(i).equals(Parametri.SEPARATORE)) {
                    if (fileInput.get(i).length() != 0 && fileInput.get(i).contains("t")) {
                        String transizioneDaConfrontare = fileInput.get(i);
                        transizioneDaConfrontare = transizioneDaConfrontare.substring(0, 3);
                        if (transizioneDaConfrontare.equalsIgnoreCase(nomeTransizione)) {
                            // controllo se la stringa ha un evento in ingresso o no
                            String eventoRichiestoString = fileInput.get(i).substring(5, 6);
                            if (eventoRichiestoString.equalsIgnoreCase("/")) {
                                return indiceEventoRichiesto;
                            } else {
                                eventoRichiestoString = fileInput.get(i).substring(5, 7);
                                for(int j = 0; j<elencoEventi.length; j++){
                                    if(elencoEventi[j].getDescrizione().equalsIgnoreCase(eventoRichiestoString)){
                                        indiceEventoRichiesto = j;
                                    }
                                }
                                return indiceEventoRichiesto;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        return indiceEventoRichiesto;
    }
    
    public static String[] getEventiInUscita(Automa automa, String nomeTransizione, Vector<String> fileInput){
        String[] eventiInUscita = null;
        for(int i=0; i<fileInput.size(); i++){
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                while(!fileInput.get(i).equals(Parametri.SEPARATORE)){
                    if (fileInput.get(i).length() != 0 && fileInput.get(i).contains("t")) {
                        String transizioneDaConfrontare = fileInput.get(i);
                        String sottoStringa = transizioneDaConfrontare.substring(0, 3);
                        if(sottoStringa.equalsIgnoreCase(nomeTransizione)){
                            // controllare se la stringa ha un uscita o meno
                            int j = transizioneDaConfrontare.indexOf("/");
                            // il metodo ritorna -1 se non trova "/"
                            if (j == -1) {
                                // la transizione non ha eventi in uscita
                                return eventiInUscita;
                            } else {
                                // salto il primo carattere perche' e' "{"
                                String eventiDaSplittare = transizioneDaConfrontare.substring(j+2, transizioneDaConfrontare.length()-1);
                                // lo split dei successivi eventi in uscita avviene soltanto dividendo con la virgola
                                eventiInUscita = eventiDaSplittare.split(",");
                                for(int x=0; x<eventiInUscita.length; x++){
                                    eventiInUscita[x] = rimuoviParentesi(eventiInUscita[x]);
                                }
                                return eventiInUscita;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        return eventiInUscita;
    }
    
    /**
     * e' un metodo di conversione da String[] a Evento[] necessario per la costruzione dell'istanza di 
     * transizione
     * @param eventi array che contiene tutti gli eventi della rete
     * @param eventiInUscita eventi in uscita dalla transizione considerata
     * @return ritorna l'array di Evento nel caso ci sono eventiInUscita dalla transizione, altrimenti ritorna null
     */
    public static Evento[] getEventiInUscita(String[] eventi, String[] eventiInUscita) {
        Evento[] eventiFinale = new Evento[eventi.length];
        for (int x = 0; x < eventi.length; x++) {
            //eventiFinale[x] = new Evento(null);
            eventiFinale[x] = null;
        }

        int i = 0;
        if (eventiInUscita != null) {
            while (i < eventiInUscita.length) {
                for (int j = 0; j < eventi.length; j++) {
                    if (eventiInUscita[i].equalsIgnoreCase(eventi[j])) {
                        String daCopiare = eventiInUscita[i];
                        //eventiFinale[j] = new Evento(daCopiare);
                        eventiFinale[j] = Rete.getEvento(daCopiare);
                    }
                }
                i++;
            }
        }
        else {
            eventiFinale = null;
        }

        return eventiFinale;
    }
    
    public static ArrayList<Evento> getEventiInUscitaList(String[] eventi, String[] eventiInUscita){
        ArrayList<Evento> eventiFinale = new ArrayList<>();
        int i = 0;
        if (eventiInUscita.length != 0) {
            while (i < eventiInUscita.length) {
                for (int j = 0; j < eventi.length; j++) {
                    if (eventiInUscita[i].equalsIgnoreCase(eventi[j])) {
                        Evento e = new Evento(eventi[i]);
                        eventiFinale.add(e);
                    }
                }
                i++;
            }
        }
        return eventiFinale;
    }
    
    public static Evento[] convertiListInArray(ArrayList<Evento> eventi){
        Evento[] eventiArray = new Evento[eventi.size()];
        for(int i = 0; i<eventi.size(); i++){
            eventiArray[i] = eventi.get(i);
        }
        return eventiArray;
    }
    
    public static String rimuoviParentesi(String stringa){
        String valida = stringa.substring(0, 2);
        return valida;
    }
    
    /**
     *
     * @param automa automa in cui e' presente la trasizione considerata
     * @param nomeTransizione e' il nome della transizione che sto considerando
     * @param elencoLink
     * @param fileInput
     * @return la funzione ritorna un int in cui, se esiste, ritorna la posizione del link "attivatore" della transizione
     * altrimenti ritorna -1. Di riferimento si veda il metodo getLink in cui si calcolano quanti link ci sono nella rete
     */
    public static int getLinkInDiTransizione(Automa automa, String nomeTransizione, String[] elencoLink, Vector<String> fileInput){
        int posizioneLink = -1;
        for(int i=0; i<fileInput.size(); i++){
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                while(!fileInput.get(i).equals(Parametri.SEPARATORE)){
                    if (fileInput.get(i).length() != 0 && fileInput.get(i).contains("t")) {
                        String transizioneIntera = fileInput.get(i);
                        String transizione = transizioneIntera.substring(0, 3);
                        if(transizione.equalsIgnoreCase(nomeTransizione)){
                            // controllo che la transizione non abbia linkIn vuoti
                            String separatore = transizioneIntera.substring(5,6);
                            if(!separatore.equalsIgnoreCase("/")){
                                // cerco il linkIn della transizione nella posizione 8-10
                                String linkIn = transizioneIntera.substring(8,10);
                                // controllo nel vettore Link in che posizione sia e modifico posizioneLink
                                for(int j=0; j<elencoLink.length; j++){
                                    if(linkIn.equalsIgnoreCase(elencoLink[j])){
                                        posizioneLink = j;
                                    }
                                }
                            }
                        }
                    }
                    i++;
                }
            }
        }
        return posizioneLink;
    }
    
    /**
     *
     * @param stato stato in cui si vuole cercare se esce qualche transizione.
     * @param automa automa dove e' contenuto lo stato considerato
     * @param fileInput fine inpunt contenente il txt
     * @return vengono ritornate le strighe con i nomi delle transizioni che escono dallo stato, null se non esce alcuna transizione
     */
    public static ArrayList<String> getStatiDiPartenza(Stato stato, Automa automa, Vector<String> fileInput){
        ArrayList<String> transizioniUscenti = new ArrayList<>();
        for(int i = 0; i<fileInput.size(); i++){
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+3;
                while(fileInput.get(j).length() != 0){
                    String daSplittare = fileInput.get(j);
                    String[] splittato = daSplittare.split("\t");
                    String statoDaConfrontare = splittato[0];
                    if(statoDaConfrontare.equalsIgnoreCase(stato.getDescrizione())){
                        String transizione = splittato[1];
                        transizioniUscenti.add(transizione);
                    }
                    j++;
                }
            }
        }
        return transizioniUscenti;
    }

}
  
