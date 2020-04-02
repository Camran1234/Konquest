/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cruise;

import Handlers.WarriorHandler;
import Mkworlds.Map;
import Planet.Planets;
import Warrior.Warriors;

/**
 *
 * @author camran
 */
public class Ship{
    int slots;
    String name;
    private int warriorSlots=0;
    int cost;
    double velocity;
    private boolean state = false;
    private int turns=0;
    private Planets home;
    private Planets orbit;
    private double distanceTraveled;
    Warriors[] warriorsAboard;
    String owner;
    
    public Ship(Planets planet){
        this.owner = planet.getOwner();
        orbit = planet;
        home= planet;
    }
    
    public void newOwner(String owner){
        this.owner = owner;
    }
    
    /**
     *Ingresa nuevos guerreros a la nave
     * @param warriors
     * @return
     */
    public boolean ArriveWarriors(Warriors[] warriors){
        warriorSlots =0;
        
        for (int i=0;i<warriors.length;i++){
            warriorSlots += warriors[i].getSlots();
        }
        
        if (warriorSlots<=slots){
            warriorsAboard = warriors;
            return true;
        }
        else 
            System.out.println("La cantidad de guerreros enviados supera el limite de "+slots +" espacios de la nave");
    warriorsAboard = warriors;
        return false;
    }
    
    public void deleteWarriors(){
        warriorsAboard = new Warriors[0];
    }
    
    /**
     *Da la orden de regreso a casa, Y se usara la funcion goPlanet previamente usada
     * @param planetList
     * @return
     */
    public Planets returnHome(Planets[][] planetList){        
        WarriorHandler handler = new WarriorHandler();
        return handler.GoPlanet(home, orbit, this, "", 0, handler.getDistance(planetList, home.getName(), orbit.getName()));       
    }
    
    /**
     *Se ingresa cuantos turnos le tomara andar deshabilitada
     * @param turns
     */
    public void addTurns(int turns){
        this.turns = turns;
    }

    /**
     *Obtenemos informacion basica de la nave disponible
     */
    public void getData(){
        System.out.println("Propietario: "+owner+" Tipo: "+name+" Planeta Origen: " + home.getName()) ;
    }
    //Obtenemos informacion b[asica de la nave en curso
    public void getDataOn(){
        if (state == true)
        System.out.println("Propietario: "+owner+" Tipo: "+name+" Planeta Origen: " + home.getName() + " Planeta Destino: "+ orbit.getName()+" Guerreros Abordo: "+this.warriorsAboard.length+" Turnos Restantes: "+ turns);
    }
    public int getTurns(){
        return this.turns;
    }
    
    public Warriors[] getWarriors(){
        return warriorsAboard;
    }
    public boolean getState(){
        return state;
    }
    
    public int getPrice(){
        return cost;
    }
    
    
    public String getName(){
        return name;
    }
    public String getOwner(){
        return owner;
    }
    
    public double getVelocity(){
        return velocity;
    }
    
    public Planets getOrbit(){
        return orbit;
    }
    public Planets getHome(){
        return this.home;
    }
    
    /**
     *Establece un nuevo destino cambiando su planeta orbita y su estado de libre a en movimiento
     * @param planet
     */
    public void newDestiny(Planets planet){
        this.orbit = planet;
        this.state=true;
    }

    /**
     *Pasa el turno 
     * @return
     */
    public boolean turnPass(){
        if (state){
            this.turns -=1;
            if (turns == 0){
                state =false;
                return true;
            }
        }
        return false;
    } 
    
    
}
