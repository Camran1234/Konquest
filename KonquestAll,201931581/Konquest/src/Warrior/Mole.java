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
public class Mole extends Warriors{
    public Mole(String owner, double deathFactor){
        super(owner,deathFactor);
        deathFactor *=1.5;
        specialAttack = "Enterrar";
        slots= 1;
        name = "Mole";
    }
}
