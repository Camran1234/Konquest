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
public class Naboo extends Ship{
   
    public Naboo(Planets planet){
        super(planet);
        slots = 25;
        cost= 40;
        velocity = 1.00;
        name = "Naboo N-1";
    }
}
