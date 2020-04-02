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
public class Groot extends Warriors{
    public Groot(String owner, double deathFactor){
        super(owner,deathFactor);
        deathFactor *=1.85;
        specialAttack = "Enredadera Trampa";
        slots= 3;
        name = "Groot";
    }
}
