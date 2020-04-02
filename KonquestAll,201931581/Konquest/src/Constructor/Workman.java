/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

import Cruise.Naboo;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Workman extends Constructors{
    public Workman(Planets planet){
        super (planet);
        timeTaked = 3;
        price = 50;
        soldPrice = 40;
        shipClass = new Naboo(planet);
        name = "Obrero";
    }
}
