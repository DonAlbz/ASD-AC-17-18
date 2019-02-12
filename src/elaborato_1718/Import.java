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
    
    public Vector<String> apriFile() throws FileNotFoundException,IOException{
        FileReader file = new FileReader(path);
        BufferedReader textReader = new BufferedReader(file);
        
        Vector <String> fileToString = new Vector<>();
        String[] input = null;
        try{
            int i = 0;
            String lineaDaCopiare;
            while((lineaDaCopiare = textReader.readLine()) != null){
                fileToString.add(lineaDaCopiare);
            }
            
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
        
//        int numberOfLines = leggiNumeroRigheFile(textReader);
//        System.out.println(numberOfLines);
//        //int numberOfLines = 26;
//        String[] textData = new String[numberOfLines];
//        
////        for(int i=0; i<numberOfLines; i++){
////            textData[i] = textReader.readLine();
////        }
////        
        textReader.close();
        return fileToString;
    }
    
//    public int leggiNumeroRigheFile(FileReader file) throws IOException{
//        
//        BufferedReader textReader = new BufferedReader(file);
//        
//        
//        textReader.close();
//        System.out.println(numberOfLines);
//        return numberOfLines;
//    }
}
