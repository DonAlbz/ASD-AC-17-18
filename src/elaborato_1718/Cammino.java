/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.ArrayList;

/**
 *
 * @author Alb
 */
public class Cammino {
    
    private ArrayList<StatoRete> cammino;

    public Cammino() {
        cammino = new ArrayList<>();
    }
    
    public void add(StatoRete statoRete){
        cammino.add(statoRete);
    }
    
    public boolean contains(StatoRete s){
        return cammino.contains(s);
    }
    
}
