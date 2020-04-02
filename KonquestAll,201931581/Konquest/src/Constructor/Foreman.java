/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

import Cruise.XWing;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Foreman extends Constructors {
    public Foreman(Planets planet){
        super (planet);
        timeTaked = 2;
        price = 100;
        soldPrice = 70;
        shipClass = new XWing(planet);
        name = "Maestro de Obra";
    }
}
