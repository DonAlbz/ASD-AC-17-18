/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elaborato_1718;

/**
 *
 * @author alber
 */
public class StatoRete {

    private Evento[] link;
    private Stato[] stati;

    public StatoRete(Evento[] link, Stato[] stati) {
        this.link = link;
        this.stati = stati;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < stati.length; i++) {
            s.append(stati[i].toString());
            s.append(" ");
        }
        for (int i = 0; i < link.length; i++) {
            if (link[i]!=null)
            s.append(link[i].toString());
            else
                s.append(Parametri.EVENTO_NULLO);
            s.append(" ");
        }
        return s.toString().trim();
    }

    public boolean equals(StatoRete stato2) {

        for (int i = 0; i < link.length; i++) {
            if (!this.link[i].equals(stato2.getLink()[i])) {
                return false;
            }
        }
        for (int i = 0; i < stati.length; i++) {
            if (!this.stati[i].equals(stato2.getStati()[i])) {
                return false;
            }
        }
        return true;
    }

    public Evento[] getLink() {
        return link;
    }

    public Stato[] getStati() {
        return stati;
    }

}
