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
import Warrior.FisionGuy;
import Warrior.Warriors;
import java.util.Random;

/**
 *
 * @author camran
 */
public class Radiation extends Planets {
    Random random = new Random();
    
    public Radiation(){
        name = name + "RD";
        typePlanet = "Radiation";
        ships[0] = new Naboo(this);
        constructorList[0] = new Workman(this);
    }

    /**
     *Create Warriors, the warriors created depends about the Planet class
     *Create Warrior Class FisionGuy
     *return a range of warriors between 3 and 9
     */
    public void addWarrior() {
       Warriors[] warrior = new FisionGuy[random.nextInt(7)+3];
        Warriors[] warriorListAux;
        warriorListAux = warriorList;
        warriorList = new FisionGuy[warriorListAux.length + warrior.length];    
        for (int i=0;i<warrior.length;i++){
            warrior[i] = new FisionGuy(state,deathPercent);
        }
        
        System.arraycopy(warrior, 0, warriorList, 0, warrior.length);
        System.arraycopy(warriorListAux, 0, warriorList, 0, warriorListAux.length);
    }

    /**
     *Create Money, the value returned depends about the planet class
     *Create a value between 90 and 180 Galactus
     * @return
     */
    public void addGalactus() {
       valueGalactus += random.nextInt(91)+90;
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
