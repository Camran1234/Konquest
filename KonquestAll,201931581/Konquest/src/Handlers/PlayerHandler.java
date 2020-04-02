/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Mkworlds.Map;
import Mkworlds.User;
import Planet.Planets;
import java.util.Scanner;


/**
 *
 * @author camran
 */
public class PlayerHandler {
   Map[] originMap = new Map[1];
   Map playableMap;
   MapHandler mapHandler = new MapHandler();
   Scanner scanner = new Scanner(System.in);
   boolean starterMap = false;

    /**
     * Obtenemos el Menu General de KONQUEST, Donde seleccionaremos una de las opciones
     * @return
     */
    public int getMenu(){
       int menu=0;
       System.out.println("Bienvenido a KONQUEST, selecciona una opcion colocando el numero(1)");
       System.out.println("1.- Diseño de Mapas");
       System.out.println("2.- Jugar");
       System.out.println("3.- Salir");
       try{
       menu = Integer.parseInt(scanner.nextLine());
       }catch(Throwable e){
           System.out.println("Opcion incorrecta se debe de escoger solo un numero,\n ejemplo 1\n");
       }
       return menu;
   }
   
    /**
     * Dependiendo del parametro menu, escoge si ir por el diseñador de mapas o el menu jugar
     * @param menu
     */
    public void choice(int menu){
       boolean state=true;

       while(state == true){
            switch(menu){
                case 1:
                    state = mapDesign(state);
                break;
                case 2:
                    state = play(state);
                break;
            }
       }
   }
   
   private boolean statusPlaying(User[] user){
    PlanetHandler planetHandler = new PlanetHandler();
    WarriorHandler warriorHandler = new WarriorHandler();
    int turno=0;
       while(true){
           
 
           
           System.out.println("\nTurno de " + user[turno].getPlayer());
           playableMap = user[turno].showActions();
           

           if(turno==0)
               turno=1;
           else{
               turno=0;
               
               
               
               for(int x=0;x<playableMap.getMap().length;x++){
                   for(int y=0;y<playableMap.getMap()[0].length;y++){
                       if(playableMap.getMap()[x][y]!= null){
                           playableMap.getMap()[x][y] = planetHandler.turnPass(playableMap.getMap()[x][y]);
                       }
                   }
               }
        
               
               for(int x=0;x<playableMap.getMap().length;x++){
                   for(int y=0;y<playableMap.getMap()[0].length;y++){
                        if(playableMap.getMap()[x][y] != null){
                            for(int index=0;index<playableMap.getMap()[x][y].getShips().length;index++){
                                 for(int x1=0;x1<playableMap.getMap().length;x1++){
                                     for(int y1=0;y1<playableMap.getMap()[0].length;y1++){
                                        if(playableMap.getMap()[x1][y1]!=null && playableMap.getMap()[x1][y1]!=playableMap.getMap()[x][y]){
                                            if(playableMap.getMap()[x1][y1].getName() == playableMap.getMap()[x][y].getShips()[index].getOrbit().getName()){
                                               if(playableMap.getMap()[x][y].getShips()[index].turnPass()){
                                                   Planets planeta = playableMap.getMap()[x1][y1];
                                                   playableMap.getMap()[x1][y1] = warriorHandler.closeCombatReturnPlanet(planeta,playableMap.getMap()[x][y].getShips()[index]);
                                                   playableMap.getMap()[x][y].getShips()[index] = warriorHandler.closeCombatReturnShip(planeta,playableMap.getMap()[x][y].getShips()[index]);
                                                   playableMap.getMap()[x][y] = playableMap.getMap()[x][y].getShips()[index].returnHome(playableMap.getMap());

                                               }
                                            }
                                        } 
                                     }
                                 }
                             }
                        }
                   }
               }
               
               if( (user[0].checkVictory(playableMap.getNumberPlanets()))){
                   break;
               }
               else if((user[1].checkVictory(playableMap.getNumberPlanets()))){
                   break;
               }
           }
       }
       return false;
   }
   
   private boolean play(boolean state){
       int menuX = 0;
       int mapSelected;
       Map originMap = null;
       playableMap = null;
       String[] player = new String[2];
       System.out.println("Bienvenido al Menu Jugar, selecciona una opcion colocando el numero (1)");
       System.out.println("1.- Iniciar Juego");
       System.out.println("2.- Salir");
     try{
            menuX = Integer.parseInt(scanner.nextLine());
            if (menuX == 1){
                System.out.println("Selecciona un Mapa para jugar");
                
                if(starterMap == true){
                    for (int index=0;index<this.originMap.length;index++){
                        System.out.print("Mapa "+(index+1)+" = ");
                        this.originMap[index].getData();
                    }  
                    
                }else {
                    System.out.println("Sin mapas disponibles para jugar, Crea Uno");
                    return true;
                }
                
                mapSelected = Integer.parseInt(scanner.nextLine());
                try {
                    originMap = (Map) this.originMap[mapSelected-1].clone();
                } catch (CloneNotSupportedException ex) {
                    System.out.println("error al clonar");
                }
                System.out.println(originMap.toString());
                player = originMap.getPlayers();
                this.playableMap = mapHandler.getPlayersPlanets(originMap, player[0], player[1]);
                User[] user = new User[2];
                user[0] = new User(player[0],playableMap);
                user[1] = new User(player[1],playableMap);
                statusPlaying(user);
            }else{
                state = false;
            }
      }catch(Throwable e){
            System.out.println("Error Escogiendo la opcion");
            
      }
       return state;
   }
   private boolean mapDesign(boolean state){
       int menu1=0;
       int rows;
       int cols;
       int numberPlanets;
       int menuX = 0;
       String player1;
       String player2;
       System.out.println("Bienvenido Al diseñador de Mapas escoge una opción");
                    System.out.println("1.- Crear Mapa Aleatorio");
                    System.out.println("2.- Crear Mapa Personalizado");
                    System.out.println("3.- Mostrar Mapas Existentes");
                    System.out.println("4.- Ver un Mapa");
                    System.out.println("5.- Regresar Menu");
                    try{
                         menu1 = Integer.parseInt(scanner.nextLine());
                    }catch(Throwable e){
                         System.out.println("Opcion incorrepta se debe de escoger solo un numero,\n ejemplo 1\n");
                    }

                    switch(menu1){
                        case 1:
                            this.originMap =  concatenate(originMap,mapHandler.createRandomMap());
                            System.out.println("Mapa creado");
                        break;

                        case 2:
                            try{
                                System.out.println("Escribe el numero de Filas");
                                rows = Integer.parseInt(scanner.nextLine());
                                System.out.println("Escribe el numero de Columnas");
                                cols = Integer.parseInt(scanner.nextLine());
                                System.out.println("Escribe el numero de Planetas");
                                numberPlanets = Integer.parseInt(scanner.nextLine());
                                if(numberPlanets > rows * cols){
                                    System.out.println("Error Creando el Mapa");
                                    break;
                                }
                                System.out.println("Escribe el nombre de un jugador");
                                player1 = scanner.nextLine();
                                System.out.println("Escribe el nombre de otro jugador");
                                player2 = scanner.nextLine();

                                this.originMap =  concatenate(originMap,mapHandler.createMap(rows, cols,numberPlanets, player1, player2));
                                System.out.println("Mapa creado");
                            }catch(Throwable e){
                                System.out.println("Error creando el Mapa");
                            }
                        break;

                        case 3:
                            if(starterMap == true){
                                for (int index=0;index<originMap.length;index++){
                                    System.out.print("Mapa "+(index+1)+" = ");
                                    originMap[index].getData();
                                }   
                            }
                            else 
                                System.out.println("Sin mapas disponibles, Crea Uno");
                        break;
                        
                        case 4:
                            if(this.starterMap == true){
                                System.out.println("Escribe el numero de mapa que quieres ver");

                                try{
                                menuX = Integer.parseInt(scanner.nextLine());
                                originMap[menuX-1].printMap("", "");
                                originMap[menuX-1].getData();
                                }catch(Throwable e){
                                    System.out.println("Mapa no encontrado");
                                }
                            }else
                                System.out.println("No hay mapas para mostrar, Crea uno");
                            
                        break;
                        default:
                            state=false;
                    }
        return state;
   }
    private Map[] concatenate(Map[] originMap, Map map) {
        Map[] mapAux = new Map[originMap.length+1];
        if( starterMap == true){
            for (int i=0;i<originMap.length;i++){
                mapAux[i] = originMap[i];
            }
            mapAux[originMap.length] = map;
        }
        else{
            originMap[0] = map;
            starterMap =true;
            return originMap;
        }
        return mapAux;
    }
}
