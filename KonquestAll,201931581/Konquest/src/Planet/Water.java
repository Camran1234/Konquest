/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planet;

import Constructor.Workman;
import Cruise.Naboo;
import Mkworlds.Map;
import Warrior.Nemo;
import Warrior.Warriors;
import java.util.Random;
/**
 *
 * @author camran
 */
public class Water extends Planets {
    Random random = new Random();
    
    public Water(){
        name = name + "WA";
        typePlanet = "Water";
        ships[0] = new Naboo(this);
        constructorList[0] = new Workman(this);
    }

    /**
     *Create Warriors, the warriors created depends about the Planet class
     *Create Warrior Class Nemo
     *return a range of warriors between 12 and 23
     */
    public void addWarrior() {
       Warriors[] warrior = new Nemo[random.nextInt(12)+12];
        Warriors[] warriorListAux;
        warriorListAux = warriorList;
        warriorList = new Nemo[warriorListAux.length + warrior.length];    
        for (int i=0;i<warrior.length;i++){
            warrior[i] = new Nemo(state,deathPercent);
        }
        
        System.arraycopy(warrior, 0, warriorList, 0, warrior.length);
        System.arraycopy(warriorListAux, 0, warriorList, 0, warriorListAux.length);
    }

    /**
     *Create Money, the value returned depends about the planet class
     *Create a value between 60 and 120 Galactus
     * @return
     */
    public void addGalactus() {
       valueGalactus += random.nextInt(61)+60;
    }
}
