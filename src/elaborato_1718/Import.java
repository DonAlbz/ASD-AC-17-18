/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

public class Import {
    
    private String path = "/Users/Francesco/Documents/Documenti/Esami Magistrale/Algoritmi e Strutture Dati/Progetto/ASD-AC-17-18/src/FileInput/input.txt";
    Vector<String> file;
    
    public Import() throws IOException{
        file = apriFile();
    }
    
    
    public void primoScenario_nuovo() {
//        String[] automi = getAutomi(file);
//        String[] link = getLink(file);
//        String[] eventi = getEventi(file);
//        Automa automa = new Automa(automi[0]);
//        ArrayList<String> prova = getStatiDaAutoma(automa, file);
//        ArrayList<String> prova2 = getNomiTransizioni(automa, file);
//        ArrayList<String> prova3 = getStatiDestinazioneDiTransizione(automa, file);
//        System.out.println();
//        System.out.println(getStatoDestinazioneDiTransizione(automa, prova2.get(0), file));
//        String eventoRichiesto = getEventoRichiesto(automa, prova2.get(0), file);
//        String[] eventiInUscita = getEventiInUscita(automa, prova2.get(0), file);
        
        Vector<Evento> eventiIn = new Vector<>();
        Vector<Evento> eventiOut = new Vector<>();
        String[] automi = getAutomi(file);
        String[] linkString = getLink(file);
        String[] eventiString = getEventi(file);
        
        // Inizializzazione dei link
        Evento[] link = new Evento[linkString.length];
        
        // Ciclo di inizializzazione degli eventi
        Evento[] eventi = new Evento[eventiString.length];
        for (int j = 0; j < eventi.length; j++) {
            Evento evento = new Evento(eventiString[j]);
            eventi[j] = evento;
        }
        
        Rete.creaRete("primo scenario", link, eventi);

        // Ciclo di inizializzazione degli automi
        for (int i = 0; i < automi.length; i++) {
            // aggiunta dell'automa
            Automa automa = new Automa(automi[i]);
            Rete.addAutoma(automa);

            // aggiunta degli stati di automa
            ArrayList<String> statiAutoma = getStatiDaAutoma(automa, file);
            for (int j = 0; j < statiAutoma.size(); j++) {
                Stato stato = new Stato(statiAutoma.get(j));
                automa.addStato(stato);
            }
            
            // aggiunta delle transizione di automa
            ArrayList<String> nomiTransizioni = getNomiTransizioni(automa, file);
            for(int x=0; x<nomiTransizioni.size(); x++){
                Evento[] eventoScatenato = new Evento[link.length];
                String[] eventoScatenatoString = getEventiInUscita(automa, nomiTransizioni.get(x), file);
                for(int y=0; y<eventoScatenato.length; y++){
                    eventoScatenato[y] = new Evento(eventoScatenatoString[y]);
                }
                String nomeTransizione = nomiTransizioni.get(x);
                String nomeStatoDestinazione = getStatoDestinazioneDiTransizione(automa, nomiTransizioni.get(x), file);
                //Stato statoDestinazione = automa.getStato(nomeStatoDestinazione);
                
                // DA FINIRE ANCORA LA CREAZIONE DELLA TRANSIZIONe
                
                //Transizione transizione = new Transizione(nomiTransizioni.get(x), getStatoDestinazioneDiTransizione(automa, nomiTransizioni.get(x), file));
                
            }
            
            //ArrayList<String> statiDestinazioneDiTransizioni = getStatiDestinazioneDiTransizione(automa, file);

        }
        
        
    }

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
        Transizione t3b = new Transizione("t3b", s30, 1, e3, null);
        s31.addTransazione(t3b);
        s31.addTransazione(t3c);
        
        c3.addStato(s30);
        c3.addStato(s31);

        Rete.addAutoma(c3);
    }
    
    public Vector<String> apriFile() throws FileNotFoundException, IOException {
        FileReader file = new FileReader(path);
        BufferedReader textReader = new BufferedReader(file);

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

        textReader.close();
        return fileToString;
    }
    
    public String[] getAutomi(Vector<String> fileInput){
        // sappiamo che gli automi sono posizionati in riga 0 del file
        String daSplittare = fileInput.get(0);
        String[] automi = daSplittare.split("\t");
        for(String stringa : automi){
            System.out.println(stringa);
        }
        return automi;
    }
    
    public String[] getLink(Vector<String> fileInput){
        // sappiamo che i link sono posizionati in riga 2 del file
        String daSplittare = fileInput.get(2);
        String[] link = daSplittare.split("\t");
        for(String stringa : link){
            System.out.println(stringa);
        }
        return link;
    }
    
    public String[] getEventi(Vector<String> fileInput){
        // sappiamo che gli eventi sono posizionati in riga 4 del file
        String daSplittare = fileInput.get(4);
        String[] eventi = daSplittare.split("\t");
        for(String stringa : eventi){
            System.out.println(stringa);
        }
        return eventi;
    }
    
    public ArrayList<String> getStatiDaAutoma(Automa automa, Vector<String> fileInput){
        ArrayList<String> statiDiAutoma = new ArrayList<>();
        for(int i=0; i<fileInput.size(); i++){
            // non devo accettare la riga 2 perché nel file input è l'elenco degli automi
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                int j = i+1;
                String daSplittare = fileInput.get(j);
                String[] stati = daSplittare.split("\t");
                for (String stringa : stati) {
                    statiDiAutoma.add(stringa);
                    System.out.println(stringa);
                }
            }
        }
        return statiDiAutoma;
    }
    
    public ArrayList<String> getNomiTransizioni(Automa automa, Vector<String> fileInput){
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
                    System.out.println(transizione);
                    j++;
                }
            }
        }
        return nomiTransizioni;
    }
    
    // METODO AL MOMENTO NON UTILIZZATO
    public ArrayList<String> getStatiDestinazioneDiTransizione(Automa automa, Vector<String> fileInput){
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
                    System.out.println(statoDestionazione);
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
    public String getStatoDestinazioneDiTransizione(Automa automa, String nomeTransizione, Vector<String> fileInput){
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
    
    public String getEventoRichiesto(Automa automa, String nomeTransizione, Vector<String> fileInput){
        String eventoRichiesto=null;
        for(int i=0; i<fileInput.size(); i++){
            if(fileInput.get(i).equals(automa.getDescrizione()) && i!=2){
                while(!fileInput.get(i).equals(Parametri.SEPARATORE)){
                    if (fileInput.get(i).length() != 0 && fileInput.get(i).contains("t")) {
                        String transizioneDaConfrontare = fileInput.get(i);
                        transizioneDaConfrontare = transizioneDaConfrontare.substring(0, 3);
                        if(transizioneDaConfrontare.equalsIgnoreCase(nomeTransizione)){
                            // controllo se la stringa ha un evento in ingresso o no
                            eventoRichiesto = fileInput.get(i).substring(5, 6);
                            if(eventoRichiesto.equalsIgnoreCase("/")){
                                System.out.println("null");
                                eventoRichiesto = null;
                                return eventoRichiesto;
                            }
                            else{
                                eventoRichiesto = fileInput.get(i).substring(5, 7);
                                System.out.println(eventoRichiesto);
                                return eventoRichiesto;
                            }
                        }
                    }
                    i++;
                }
            }
        }
        return eventoRichiesto;
    }
    
    public String[] getEventiInUscita(Automa automa, String nomeTransizione, Vector<String> fileInput){
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
                                
                                // ANCORA DA DEFINIRE COME AVVIENE LO SPLIT CON PIU' EVENTI IN USCITA,
                                // AL MOMENTO SPLITTO CON LA VIRGOLA, 
                                
                                eventiInUscita = eventiDaSplittare.split(",");
                                for(int x=0; x<eventiInUscita.length; x++){
                                    eventiInUscita[x] = rimuoviParentesi(eventiInUscita[x]);
                                    System.out.println(eventiInUscita[x]);
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
    
    public String rimuoviParentesi(String stringa){
        String valida = stringa.substring(0, 2);
        return valida;
    }

}
  
