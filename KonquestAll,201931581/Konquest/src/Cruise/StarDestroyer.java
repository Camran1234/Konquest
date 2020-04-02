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
public class StarDestroyer extends Ship{
    public StarDestroyer(Planets planet){
        super(planet);
        slots = 80;
        cost= 100;
        velocity = 1.75;
        name = "StarDestroyer";
    }
}
