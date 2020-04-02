/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planet;

import Constructor.Constructors;
import Constructor.Workman;
import Cruise.Naboo;
import Cruise.Ship;
import Warrior.Magma;
import Warrior.Warriors;
import java.util.Random;

/**
 *
 * @author camran
 */
public class Fire extends Planets {
    Random random = new Random();
    
    public Fire(){
        name = name + "FI";
        typePlanet = "Fire";
        ships[0] = new Naboo(this);
        constructorList[0] = new Workman(this);
    }

    /**
     *Create Warriors, the warriors created depends about the Planet class
     *Create Warrior Class Magma
     *return a range of warriors between 10 and 20
     */
    public void addWarrior() {
       Warriors[] warrior = new Magma[random.nextInt(11)+10];
        Warriors[] warriorListAux;
        warriorListAux = warriorList;
        warriorList = new Magma[warriorListAux.length + warrior.length];    
        for (int i=0;i<warrior.length;i++){
            warrior[i] = new Magma(state,deathPercent);
        }
        
        System.arraycopy(warrior, 0, warriorList, 0, warrior.length);
        System.arraycopy(warriorListAux, 0, warriorList, 0, warriorListAux.length);
    }

    /**
     *Create Money, the value returned depends about the planet class
     *Create a value between 70 and 140 Galactus
     * @return
     */
    public void addGalactus() {
       valueGalactus += random.nextInt(71)+70;
    }
    
    public void restart() {
        warriorList = new Warriors[0];
        ships = new Ship[1];
        ships[0] = new Naboo(this);
        constructorList = new Constructors[1];
        constructorList[0] = new Workman(this);
        state = null;
    }
}
