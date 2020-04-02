/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Mkworlds.Map;
import Planet.Planets;
import java.util.Random;

/**
 *
 * @author camran
 */
public class MapHandler {
    Random random = new Random();

    /**
     *Crea un mapa aleatorio con un rango de 5 a 25 tanto en columnas como en filas
     * @return
     */
    public Map createRandomMap(){
       
        PlanetHandler mkr = new PlanetHandler();
        Planets[][] world = new Planets[random.nextInt(21)+5][random.nextInt(21)+5];
        
        for (int row=0; row<world.length;row++){
            for (int col=0;col<world[0].length;col++){
                if (random.nextInt(2) == 1)
                    world[row][col] = mkr.mkWorld(world[row][col]);
            }
        }
        
        return new Map(world,"Jugador1","Jugador2");
    }
    
    /**
     *Crea un mapa con valores especificados, los numeros de planetas ingresados pueden varias del valor colocado en la funcion
     * @param rows
     * @param cols
     * @param numberPlanets
     * @param player1
     * @param player2
     * @return
     */
    public Map createMap(int rows, int cols, int numberPlanets, String player1, String player2){
        PlanetHandler mkr = new PlanetHandler();
        Planets[][] world = new Planets[rows][cols];
        int tries= numberPlanets;
        while(tries != 0){
            for (int row=0; row<world.length;row++){
                for (int col=0;col<world[0].length;col++){
                    if (random.nextInt(3) == 1){
                        world[row][col] = mkr.mkWorld(world[row][col]);
                        tries--;
                    }
                    if (tries == 0)
                       return new Map(world,player1, player2); 
                }
            }
        }
        
        return new Map(world,player1, player2);
    }
    
    /**
     *Retorna los planetas del mapa
     * @param map
     * @return
     */
    public Planets[] returnPlanets(Map map){
        Planets[] planets;
        int counter = 0;
        
        for (int x=0;x<map.getMap().length;x++){
            for (int y=0;y<map.getMap()[0].length;y++){
                if(map.getMap()[x][y]!= null)
                    counter++;
            }
        }
        planets=new Planets[counter];
        for (int x=0;x<map.getMap().length;x++){
            for (int y=0;y<map.getMap()[0].length;y++){
                if(map.getMap()[x][y]!= null){
                    planets[counter-1] = map.getMap()[x][y];
                    counter--;
                }
            }
        }
        
       
        
        return planets;
    }
    
   
    
    /**
     *Se asigna planetas neutrales a los jugadores
     * @param map
     * @param Player1
     * @param Player2
     * @return
     */
    public Map getPlayersPlanets(Map map,String Player1, String Player2){
        Map mapAux=map;
        
        int planetsTerraformedPlayer1=2;
        int planetsTerraformedPlayer2=2;
        int x;
        int y;
        while(true){
            mapAux=map;
            x=random.nextInt(map.getMap().length);
            y=random.nextInt(map.getMap()[0].length);
            
            if(mapAux.getMap()[x][y] != null){
                if(mapAux.getMap()[x][y].getOwner() == null && planetsTerraformedPlayer1 != 0){
                    mapAux.getMap()[x][y].addOwner(Player1);
                    mapAux.getMap()[x][y].getShips()[0].newOwner(Player1);
                    mapAux.getMap()[x][y].getBuilders()[0].newOwner(Player1);
                    planetsTerraformedPlayer1 -=1;
                }
                else if(mapAux.getMap()[x][y].getOwner() == null && planetsTerraformedPlayer2 != 0){
                    mapAux.getMap()[x][y].addOwner(Player2);
                    mapAux.getMap()[x][y].getShips()[0].newOwner(Player2);
                    mapAux.getMap()[x][y].getBuilders()[0].newOwner(Player2);
                    planetsTerraformedPlayer2 -=1;
                }
            }
        
            if(planetsTerraformedPlayer1 ==0 && planetsTerraformedPlayer2 ==0)
                break;
        }
        
        return mapAux;
    }
    
    
}
