/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Cruise.Ship;
import Planet.Planets;
import Warrior.Warriors;
import java.lang.Math;

/**
 *
 * @author camran
 */
public class WarriorHandler {
    
    
    /**
     *Requires an origin planet, a target planet, the class of the ship, the class of warrior, number of warriors to sent, and the distance to go
     * and returns the times required for the ship to arrive to the planet and do the action of attack or join warriors
     * @param planetOrigin
     * @param planetTarget
     * @param planet
     * @param ship
     * @param numberWarriors
     * @param distance
     * @return
     */
    
    public Planets GoPlanet(Planets planetOrigin,Planets planetTarget,Ship ship,String warriorClass,int numberWarriors, double distance){
        Planets planetAux=planetOrigin;
        int contadorGuerreros=0;
        int indexShip=0;
        
        if(planetOrigin == null || planetTarget == null){
            System.out.println("Escogiste un planeta Vacio");
            return planetOrigin;
        }
        
        for(int index=0;index<planetAux.getShips().length;index++){
            if(planetAux.getShips()[index].getName()==ship.getName()){
                if(planetAux.getShips()[index].getState() != true){
                    indexShip=index;
                    break;
                }
            }
            if(index == planetAux.getShips().length-1){
                System.out.println("No se encontro la Nave");
                return planetOrigin;
            }
        }
        
        
        
        if (planetAux.getShips()[indexShip].getState() == false){
        
            int time=0;
            if (distance!=0){
                time = (int)  (distance/ship.getVelocity());
                if (distance/ship.getVelocity() % 1 >0.50){
                    time +=1;
                }
            }
            else{
                System.out.println("Ya estas en este planeta");
                return planetOrigin;
            }
            Warriors[] planetWarriors = planetAux.getWarriors();
            Warriors[] shipWarriors = new Warriors[numberWarriors];
            for(int i=0;i<numberWarriors;i++){
                //trycatch para ver que los guerreros son insuficientes
                if(planetWarriors[i].getName().equalsIgnoreCase(warriorClass)){
                    shipWarriors[i] = planetWarriors[i];
                    contadorGuerreros++;
                }
            }
           
            if(contadorGuerreros!= numberWarriors){
                    System.out.println("Cantidad de guerreros solicitados insuficientes");
                    System.out.println("No se ha podido enviar la Nave");
                    return planetOrigin;
            }
            
            if( planetAux.getShips()[indexShip].ArriveWarriors(shipWarriors)){
                
                if(warriorClass != ""){
                planetAux.leftWarriors(planetAux.getWarriors().length - numberWarriors);
                planetAux.getShips()[indexShip].newDestiny(planetTarget);
                planetAux.getShips()[indexShip].addTurns(time);
                 System.out.println("Se ha enviado la nave " + planetAux.getShips()[indexShip].getName() + " de " + planetAux.getShips()[indexShip].getOwner() + " Hacia ");
                 planetTarget.getData();
                return planetAux;
                }
                else{
                    planetAux.leftWarriors(planetAux.getWarriors().length - numberWarriors);
                planetAux.getShips()[indexShip].newDestiny(planetOrigin);
                planetAux.getShips()[indexShip].addTurns(time);
                 System.out.println("Se ha enviado la nave " + planetAux.getShips()[indexShip].getName() + " de " + planetAux.getShips()[indexShip].getOwner() + " Hacia ");
                 planetTarget.getData();
                return planetAux;
                }
                
            }
            
        }
        
        System.out.println("No se ha podido enviar la Nave");
        return planetOrigin;
    }
    
    /**
     * Get the distance from a planet to another planet
     * The distance is measure by the distance formula and
     * From a dimensional of the array planets
     * @return
     */
    public double getDistance(Planets[][] planetList, String origin, String destiny){
        int time;
        int xi=0;
        int yi=0;
        int xf=0;
        int yf=0;
        double distance=0;
        for (int x=0;x<planetList.length;x++){
            for (int y=0;y<planetList[0].length;y++){
                if(planetList[x][y] != null){
                    if (planetList[x][y].getName().equalsIgnoreCase(origin)){
                        xi=y+1;
                        yi=x+1;
                    }
                    else if(planetList[x][y].getName().equalsIgnoreCase(destiny)){
                        xf=y+1;
                        yf=x+1;
                    }
                }
            }
        }
       distance = Math.sqrt(Math.pow((xf-xi),2) + Math.pow((yf-yi),2));
        if (distance <0)
            distance *= -1;
        
        time = (int)  (distance/1);
                if (distance/1 % 1 >0.50){
                    time +=1;
                }
        System.out.println("Turnos requeridos para la nave Naboo N-1 = "+ time );
        time = (int)  (distance/1.25);
                if (distance/1.25 % 1 >0.50){
                    time +=1;
                }
                
        System.out.println("Turnos requeridos para la nave X-Wing = "+ time );
        time = (int)  (distance/1.50);
                if (distance/1.50 % 1 >0.50){
                    time +=1;
                }
                
        System.out.println("Turnos requeridos para la nave Millenial Falcon = "+ time );
        time = (int)  (distance/1.75);
                if (distance/1.75 % 1 >0.50){
                    time +=1;
                }
                
        System.out.println("Turnos requeridos para la nave Star Destroyer = "+ time );
        
        return distance;
    }
   
    /**
     * Requires The planet to invade and the ship invader
     * Use return home after this
     * @param planet
     * @param ship
     * @return
     */
    public Planets closeCombatReturnPlanet(Planets planet, Ship ship){
        
        
        
        if (ship.getOwner() != planet.getOwner()){
            Warriors[] resident= planet.getWarriors();
            Warriors[] visitor = ship.getWarriors();
            int residentBattles = 0;
            int visitorBattles = 0;
            for (int i = 0 ; i < resident.length ; i++){
                if(resident[i] != null) {
                    residentBattles++;
                }
            }
            for (int i = 0 ; i < visitor.length ; i++){
                if(visitor[i] != null) {
                    visitorBattles++;
                }
            }
            residentBattles--;
            visitorBattles--;
             if(resident.length != 0 || visitor.length!=0){
            while(true){

                if(resident[residentBattles].getdeathFactor() > visitor[visitorBattles].getdeathFactor()){
                    visitor[visitorBattles] = null;
                    visitorBattles--;
                    resident[residentBattles].addTimesConfronted();
                } 
                else if (resident[residentBattles].getdeathFactor() < visitor[visitorBattles].getdeathFactor()){
                    resident[residentBattles] = null;
                    residentBattles--;
                    visitor[visitorBattles].addTimesConfronted();
                }

                if (resident[residentBattles].getTimesConfronted() == 2){
                    resident[residentBattles] = null;
                    residentBattles--;
                }
                else if (visitor[visitorBattles].getTimesConfronted() == 2){
                    visitor[visitorBattles] = null;
                    visitorBattles--;
                }

                if (residentBattles == 0){
                    Warriors[] survivours = new Warriors[visitorBattles+1];
                    
                    for (int i=0;i<survivours.length;i++){
                        if(visitor[i]!=null){
                            survivours[i] = visitor[i];
                        }
                    }
                    
                    planet.addOwner(ship.getOwner());
                    planet.leftWarriors(0);
                    planet.welcomeAllies(survivours);
                    ship.deleteWarriors();
                    System.out.println("Se ha conquistado el planeta "+planet.getName());
                    break;
                }
                else if(visitorBattles == 0){
                    ship.deleteWarriors();
                    planet.leftWarriors(residentBattles+1);
                    System.out.println("No se ha podido conquistar el planeta"+planet.getName());
                    break;
                }
                

            }
        }
             if(resident.length == 0){
                if(visitor.length!=0){
             planet.welcomeAllies(visitor);
                    ship.deleteWarriors();
                    System.out.println("Se ha conquistado el planeta "+planet.getName());
            }        
        }else if(visitor.length == 0){
            if(resident.length!=0){
               System.out.println("No se ha podido conquistar el planeta"+planet.getName());
            }else{
                 planet.addOwner(ship.getOwner());
                 System.out.println("Se ha conquistado el planeta "+planet.getName());
            }
        }
        }
        else {
            planet.welcomeAllies(ship.getWarriors());
            ship.deleteWarriors();
        }
        return planet;
    }
    public Ship closeCombatReturnShip(Planets planet, Ship ship){
        
        
        
        if (ship.getOwner() != planet.getOwner()){
            Warriors[] resident= planet.getWarriors();
            Warriors[] visitor = ship.getWarriors();
            int residentBattles=0;
            int visitorBattles=0;
                for (int i = 0 ; i < resident.length ; i++){
                if(resident[i] != null) {
                    residentBattles++;
                }
            }
            for (int i = 0 ; i < visitor.length ; i++){
                if(visitor[i] != null) {
                    visitorBattles++;
                }
            }
            residentBattles--;
            visitorBattles--;
            if(resident.length != 0 || visitor.length!=0){
            while(true){

                if(resident[residentBattles].getdeathFactor() > visitor[visitorBattles].getdeathFactor()){
                    visitor[visitorBattles] = null;
                    visitorBattles--;
                    resident[residentBattles].addTimesConfronted();
                } 
                else if (resident[residentBattles].getdeathFactor() < visitor[visitorBattles].getdeathFactor()){
                    resident[residentBattles] = null;
                    residentBattles--;
                    visitor[visitorBattles].addTimesConfronted();
                }

                if (resident[residentBattles].getTimesConfronted() == 2){
                    resident[residentBattles] = null;
                    residentBattles--;
                }
                else if (visitor[visitorBattles].getTimesConfronted() == 2){
                    visitor[visitorBattles] = null;
                    visitorBattles--;
                }

                if (residentBattles == 0){
                    Warriors[] survivours = new Warriors[visitorBattles+1];
                    
                    for (int i=0;i<survivours.length;i++){
                        if(visitor[i]!=null){
                            survivours[i] = visitor[i];
                        }
                    }
                    
                    planet.addOwner(ship.getOwner());
                    planet.leftWarriors(0);
                    planet.welcomeAllies(survivours);
                    ship.deleteWarriors();
                    System.out.println("Se ha conquistado el planeta "+planet.getName());
                    break;
                }
                else if(visitorBattles == 0){
                    ship.deleteWarriors();
                    planet.leftWarriors(residentBattles+1);
                    System.out.println("No se ha podido conquistar el planeta"+planet.getName());
                    break;
                }
                

            }
        }
            if(resident.length == 0){
                if(visitor.length!=0){
             planet.welcomeAllies(visitor);
                    ship.deleteWarriors();
                    System.out.println("Se ha conquistado el planeta "+planet.getName());
            }        
        }else if(visitor.length == 0){
            if(resident.length!=0){
               System.out.println("No se ha podido conquistar el planeta"+planet.getName());
            }else{
                 planet.addOwner(ship.getOwner());
                 System.out.println("Se ha conquistado el planeta "+planet.getName());
            }
        }
        }
        else {
            planet.welcomeAllies(ship.getWarriors());
            ship.deleteWarriors();
        }
        
        
       return ship; 
    }
}
