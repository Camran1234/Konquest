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
public class XWing extends Ship{
    public XWing(Planets planet){
        super(planet);
        slots = 42;
        cost= 50;
        velocity = 1.25;
        name = "X-Wing";
    }
}
