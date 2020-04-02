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
public class Nemo extends Warriors{
    public Nemo(String owner, double deathFactor){
        super(owner,deathFactor);
        deathFactor *=1.6;
        specialAttack = "Turbo Chorro Venenoso";
        slots= 1;
        name = "Nemo";
    }
}
