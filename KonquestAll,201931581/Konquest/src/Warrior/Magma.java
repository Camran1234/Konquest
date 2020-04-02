/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warrior;


/**
 *
 * @author camran
 */
public class Magma extends Warriors{
    public Magma(String owner, double deathFactor){
        super(owner,deathFactor);
        deathFactor *=1.75;
        specialAttack = "Bola de lava";
        slots= 2;
        name = "Magma";        
    }
}
