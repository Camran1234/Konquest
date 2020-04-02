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
public class Warriors {
        private String owner=null;
        String name;
        String specialAttack;
        double deathFactor;
        private int timesConfronted=0;
        int slots;
        
        public Warriors(String owner, double deathFactor){
            this.owner = owner;
            this.deathFactor = deathFactor;
        }
        void ChangeOwner(String owner){
            this.owner = owner;
        }
        
        public double getdeathFactor(){
            return deathFactor;
        }
        
        public void getData(){
            System.out.println("Tipo: " + name + "" );
        }
        public int getSlots(){
            return slots;
        }
        
        public String getName(){
            return name;
        }
        public int getTimesConfronted(){
            return timesConfronted;
        }
        public void addTimesConfronted(){
            this.timesConfronted ++;
        }
        
}
