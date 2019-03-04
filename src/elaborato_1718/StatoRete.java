/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author alber
 */
public class StatoRete extends StatoReteAbstract {
    
    private Transizione transizioneEseguita; 
    private Transizione transizionePrecedente;

    public StatoRete(Evento[] link, Stato[] stati, int _numero) {
        super(link, stati, _numero);
    }

    public StatoRete(StatoRete statoDaClonare) {
        super(statoDaClonare.getLink().clone(), statoDaClonare.getStati().clone(), statoDaClonare.getNumero());
    }
    
    public void setTransizioneEseguita(Transizione transizioneEseguita) {
        this.transizioneEseguita = transizioneEseguita;
    }

    public Transizione getTransizioneEseguita() {
        return transizioneEseguita;
    }

    public boolean isAbilitato(Vector<Automa> automi){
        for(int i=0;i<automi.size();i++){
            if (automi.get(i).isAbilitato(this.getLink())){
                return true;
            }
        }
        return false;
    }

    public Transizione getTransizionePrecedente() {
        return transizionePrecedente;
    }

    public void setTransizionePrecedente(Transizione transizionePrecedente) {
        this.transizionePrecedente = transizionePrecedente;
    }

    
    
    
    
}
