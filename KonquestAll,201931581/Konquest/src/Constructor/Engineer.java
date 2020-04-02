/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

import Cruise.StarDestroyer;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Engineer extends Constructors {
    public Engineer(Planets planet){
        super (planet);
        timeTaked = 1;
        price = 300;
        soldPrice = 200;
        shipClass = new StarDestroyer(planet);
        name= "Ingeniero";
    }
}
