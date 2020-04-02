/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruise;

import Planet.Planets;

/**
 *
 * @author camran
 */
public class MillenialFalcon extends Ship {
    
    public MillenialFalcon(Planets planet){
        super(planet);
        slots = 58;
        cost= 70;
        velocity = 1.50;
        name = "Millenial Falcon";
    }
}
