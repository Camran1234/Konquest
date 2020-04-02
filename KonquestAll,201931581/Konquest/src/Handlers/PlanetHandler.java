/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Constructor.Constructors;
import Cruise.Ship;
import Planet.Earth;
import Planet.Fire;
import Planet.Organic;
import Planet.Planets;
import Planet.Radiation;
import Planet.Water;
import java.util.Random;
/**
 *
 * @author camran
 */
public class PlanetHandler {
    
   
    
    public Planets mkWorld(Planets planet){
        final double radiation = 0.05;
        final double organic=0.15;
        final double fire=0.30;
        final double water=0.55;
        
        
        Random random = new Random();
        double probability = random.nextDouble();
        
        if (probability <= radiation)
            planet = new Radiation();
        else if (probability >radiation && probability<= organic)
            planet = new Organic();
        else if (probability >organic && probability <= fire)
            planet = new Fire();
        else if (probability >fire &&probability <= water)
            planet = new Water();
        else 
            planet = new Earth();
        
        return planet;
    }
    
    public Planets buyConstructor(Planets planet,Constructors builder){
        Planets planetAux=planet;
        int money = planetAux.getMoney();
        if(money >= builder.getPrice()){
            money -= builder.getPrice();
            planetAux.addBuilder(builder);
            System.out.println("Transaccion completada, gracias por comprar un " + builder.getName());
            planetAux.newMoney(money);
        }
        else{
            System.out.println("Transaccion incompleta, se requieren más fondos");
            return planet;
        }
        
        return planetAux;
    }
    
    public Planets sellConstructor(Planets planet,Constructors builder){
        Planets planetAux=planet;
        int money = planet.getMoney();
        boolean findBuilder=false;
        Constructors[] builders = planet.getBuilders();
        
        //Se toma en cuenta si se encuentra el constructor
        for (int i=0;i<builders.length;i++){
            if (builder.getName().equalsIgnoreCase(builders[i].getName()) ){
                if(builders[i].getState() == false){
                    findBuilder=true;
                    builders[i] =null;
                    break;
                }
            }
        }
        
        //Si se hallo empieza el proceso de vender el obrero
        if(findBuilder){
            Constructors[] buildersAux = new Constructors[builders.length-1];
            for (int i=0; i<builders.length;i++){
                if (builders[i]!= null){
                    buildersAux[i] = builders[i];
                }
            }
            money += builder.getSoldPrice();
            planetAux.newMoney(money);
            planetAux.newBuilder(buildersAux);
            System.out.println("Vendido");
        }
        else{
            System.out.println("No se hallo el constructor mencionado");
            return planet;
        }
        
        return planetAux;
    }
    
    public Planets sellShip(Planets planet, Ship ship){
        Planets planetAux=planet;
        int money = planet.getMoney();
        int index;
        boolean findShip=false;
        Ship[] ships = planet.getShips();
        
        //Trata de hallar la nave en la lista de naves del planeta
        for (int i=0;i<ships.length;i++){
            if (ship.getName().equalsIgnoreCase(ships[i].getName()) ){
                if(ships[i].getState() == false){
                    findShip = true;
                    ships[i] =null;
                    break;
                }
            }
        }
        
        if(findShip){
            Ship[] shipsAux = new Ship[ships.length-1];
            for (int i=0; i<shipsAux.length;i++){
                if (ships[i]!= null){
                    shipsAux[i] = ships[i];
                }
            }
            money += ship.getPrice();
            planetAux.newMoney(money);
            planetAux.newShip(shipsAux);
            System.out.println("Vendido");
        }
        else{
            System.out.println("No se hallo la nave mencionado");
            return planet;
        }
        return planetAux;
    }
    
    public Planets turnPass(Planets planet){
        planet.addWarrior();
        planet.addGalactus();
        Ship shipBuild;
        for(int index=0;index<planet.getBuilders().length;index++){
            shipBuild = planet.getBuilders()[index].turnPass();
            if(shipBuild != null){
                planet.addShip(shipBuild);
            }
        }
        
        
        
        return planet;
    }
        
    
    
}
