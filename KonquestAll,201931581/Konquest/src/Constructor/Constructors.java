/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constructor;

import Cruise.Ship;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Constructors {
    int timeTaked;
    private int workedDays=0;
    int price;
    int soldPrice;
    private boolean state=false;
    String name;
    Ship shipClass;
    private Planets planet;
    
    //Indicar el planeta es importante para que se copien los valores al constructor
    public Constructors(Planets planet){
        this.planet = planet;
    }
    
    public void newOwner(String owner){
        this.planet.addOwner(owner);
    }
    
    public int getPrice(){
        return price;
    }
    
    public int getSoldPrice(){
        return soldPrice;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean getState(){
        return state;
    }
    /**
     *Start the production of a ship, it requires that the builder is not busy
     */
    public void createShip(){
        if(state != true){
            state = true;
            System.out.println("Empezando a trabajar, me tomara "+ timeTaked + " turnos");
        }
        else 
            System.out.println("Trabajador ocupado");
    }
    
    /**
     *Se retorna el tipo de nave que puede construir este constructor
     * @return
     */
    public Ship getShipClass(){
        return shipClass;
    }
    /**
     * Pass the turn and proves if is possible to build a ship only if the constructor have a state of working = true
     * return a ship when the time has pass
     */
    public Ship turnPass(){
        
        if (state == true){
            workedDays+=1;
            if(workedDays == timeTaked){
                state = false;
                workedDays = 0;
                System.out.println("Trabajo realizado se ha agregado una nave tipo "+ shipClass.getName() + "\n ala direccion planeta "); 
                System.out.println(planet.getName());
                return shipClass;
            }
        }
        return null;
    }

    /**
     *Obtenemos informacion basica del constructor
     */
    public void getData() {
        String working="Sin trabajo";
        
        if(state==true)
            working = "Con trabajo";
        
        System.out.print("Tipo: " + name + " Nave Conocida: "+ shipClass.getName()+" Estado: "+working);    
        if(state == true){
            System.out.println("Turnos para terminar: "+(timeTaked - workedDays));
        }else{
            System.out.println("");
        }
    }


}
