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
import java.util.Vector;

/**
 *
 * @author Alb
 */
public class Import {
    
    private String path;
    
    public Import(String file_path){
        path=file_path;
    }
    
    public Vector<String> apriFile() throws FileNotFoundException, IOException {
        FileReader file = new FileReader(path);
        BufferedReader textReader = new BufferedReader(file);

        Vector<String> fileToString = new Vector<>();
        String[] input = null;
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
    
//    public String[] getStatiDaAutoma(Vector<String> fileInput, Automa automa){
//        for(int i=0; i<fileInput.size(); i++){
//            if(fileInput.get(i).equalsIgnoreCase(automa.getDescrizione()) && i!=1){
//            }
//        }
//    }
}

