/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

import Cruise.MillenialFalcon;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Architect extends Constructors {
    
    public Architect(Planets planet){
        super (planet);
        timeTaked = 1;
        price = 250;
        soldPrice = 175;
        shipClass = new MillenialFalcon(planet);
        name = "Arquitecto";
    }
}
