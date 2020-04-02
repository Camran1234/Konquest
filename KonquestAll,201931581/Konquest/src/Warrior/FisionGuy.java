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
public class FisionGuy extends Warriors{
    public FisionGuy(String owner, double deathFactor){
        super(owner,deathFactor);
        deathFactor *=1.95;
        specialAttack = "Rayo Gamma";
        slots= 4;
        name = "Fision Guy";
    }
}
