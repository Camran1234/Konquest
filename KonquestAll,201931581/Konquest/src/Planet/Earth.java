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
import Warrior.Mole;
import Warrior.Warriors;
import java.util.Random;
/**
 *
 * @author camran
 */
public class Earth extends Planets {
    
    Random random = new Random();

    
    public Earth(){
        name = name + "EA";
        typePlanet = "Earth";
        ships[0] = new Naboo(this);
        constructorList[0] = new Workman(this);
    }

    /**
     *Create Warriors, the warriors created depends about the Planet class
     *Create Warrior Class Mole
     *return a range of warriors between 15 and 25
     */
    public void addWarrior() {
        Warriors[] warrior = new Mole[random.nextInt(11)+15];
        Warriors[] warriorListAux;
        warriorListAux = warriorList;
        warriorList = new Mole[warriorListAux.length + warrior.length];    
        
        for (int i=0;i<warrior.length;i++){
            warrior[i] = new Mole(state,deathPercent);
        }
        
        System.arraycopy(warrior, 0, warriorList, 0, warrior.length);
        System.arraycopy(warriorListAux, 0, warriorList, 0, warriorListAux.length);
        
    }

    /**
     *Create Money, the value returned depends about the planet class
     *Create a value between 50 and 100 Galactus
     * @return
     */
    public void addGalactus() {     
        valueGalactus += random.nextInt(51)+50;
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
