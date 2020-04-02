/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mkworlds;

import Constructor.Architect;
import Constructor.Constructors;
import Constructor.Engineer;
import Constructor.Foreman;
import Constructor.Workman;
import Cruise.MillenialFalcon;
import Cruise.Naboo;
import Cruise.Ship;
import Cruise.StarDestroyer;
import Cruise.XWing;
import Handlers.PlanetHandler;
import Handlers.WarriorHandler;
import Planet.Planets;
import Warrior.FisionGuy;
import Warrior.Groot;
import Warrior.Magma;
import Warrior.Mole;
import Warrior.Nemo;
import Warrior.Warriors;
import java.util.Scanner;

/**
 *
 * @author camran
 */
public class User {
    private String player;
    private Ship[] shipList = new Ship[1];
    private Map map;
    public User(String player, Map map){
        this.player = player;
        this.map = map;
    }
    
    public String getPlayer(){
        return player;
    }
    
    public Map showActions(){
    int choice;
    int indexRows1 = 0;
    int indexCols1;
    int indexRows2 = 0;
    int indexCols2;
    int warriorsSent = 0;
    int indexChar = 0;
    String shipUse = null;
    String warriorsUse = null;
    String oracion ="";
    boolean state= true;
    Scanner scanner = new Scanner(System.in);
    WarriorHandler warriorHandler = new WarriorHandler();
    PlanetHandler planetHandler = new PlanetHandler();
    Constructors constructor = null;
    char[] planeta;
    while(state == true){
       try{
           System.out.println("Presiona Cualquier Tecla Para Continuar");
           scanner.nextLine();
           map.printMap(map.getPlayers()[0], map.getPlayers()[1]);
            System.out.println("\nAcciones "+player+" , porfavor elige una");
            System.out.println("1.- Obtener distancia");
            System.out.println("2.- Ver planeta");
            System.out.println("3.- Consulta de Flota");
            System.out.println("4.- Envio de Flota");
            System.out.println("5.- Construir Nave");
            System.out.println("6.- Tienda");
            System.out.println("7.- Ver Posesiones");
            System.out.println("8.- Terminar");
            choice = Integer.parseInt(scanner.nextLine());
        
            System.out.println("\n\n\n\n");
            switch(choice){
                case 1:
                        getDistance();
                    break;
                
                case 2:
                    //Permite ver las caracteristicas del planeta
                    System.out.println("Escribe el planeta que quieres ver de la siguiente manera, A1");
                    planeta = scanner.nextLine().toCharArray();
                    
                     
                    indexCols1 = planeta[0] - 65;
                    oracion ="";
                    for(int i=1;i<planeta.length;i++){
                        
                            if(i<planeta.length){
                                oracion+= planeta[i];
                                indexChar = i+1;
                            }
                                                    
                    }
                    indexRows1 = Integer.parseInt(oracion) -1;
                    if(map.getMap()[indexRows1][indexCols1] != null){
                        if(map.getMap()[indexRows1][indexCols1].getOwner() == null || map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                            map.getMap()[indexRows1][indexCols1].getData();
                        }else
                            System.out.println("No puedes ver el planeta enemigo");
                    }else
                        System.out.println("Es el espacio");
                    break;
                    
                    
                case 3:
                    this.getShipOnGoing();
                    break;
                case 4:
                    System.out.println("Indica el planeta Origen, cantidad de guerreros, tipo de guerreros, tipo de nave a usar, planeta destino; ejemplo A1,35,Mole,Naboo N-1,G4");
                    System.out.println("Tipo de Guerreros: Mole(1 espacio), Nemo(1 espacio), Magma(2 espacios), Groot(3 espacios), Fision Guy(4 espacios)");
                    System.out.println("Tipo de Naves: Naboo N-1(25 Espacios), X-Wing(42 Espacios), Millenial Falcon(58 espacios), StarDestroyer(80 espacios)");
                    System.out.println("");
                    planeta = scanner.nextLine().toCharArray();
                    
                    indexCols1 = planeta[0] - 65;
                           oracion ="";
                            for(int i=1;i<planeta.length;i++){
                                if(planeta[i] != ','){
                                    oracion += planeta[i];
                                }else{
                                    indexRows1 = Integer.parseInt(oracion) -1;
                                    indexChar = i+1;
                                    break;
                                }
                            }
                            
                    oracion="";
                    for(int index=indexChar;index<planeta.length;index++){
                        if(planeta[index] != ','){
                            oracion += planeta[index];
                        }
                        else {
                            warriorsSent = Integer.parseInt(oracion);
                            indexChar = index+1;
                            oracion = "";
                            break;
                        }
                    }
                    for(int index= indexChar;index<planeta.length;index++){
                        if(planeta[index] != ','){
                            oracion += planeta[index];
                        }
                        else {
                            warriorsUse = oracion;
                            indexChar = index+1;
                            oracion = "";
                            break;
                        }
                    }
                    for(int index= indexChar;index<planeta.length;index++){
                        if(planeta[index] != ','){
                            oracion += planeta[index];
                        }
                        else {
                            shipUse = oracion;
                            indexChar = index+1;
                            oracion = "";
                            break;
                        }
                    }
                    indexCols2 = planeta[indexChar]-65;
                    
                    for(int i=indexChar+1;i<planeta.length;i++){
                                    oracion += planeta[i];    
                            }
                        indexRows2 = Integer.parseInt(oracion)-1;
                        
                                
                    
                    
                    if(map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                        System.out.println("planeta origen: "+map.getMap()[indexRows1][indexCols1].getName());
                        System.out.println("Cantidad guerreros: "+ warriorsSent);
                        System.out.println("Tipo Guerrero: "+ warriorsUse );
                        System.out.println("Tipo de Nave: "+shipUse);
                        System.out.println("Destino: " + map.getMap()[indexRows2][indexCols2].getName());
                        
                        map.getMap()[indexRows1][indexCols1] = warriorHandler.GoPlanet(map.getMap()[indexRows1][indexCols1], map.getMap()[indexRows2][indexCols2], this.getShipClass(map.getMap()[indexRows1][indexCols1], shipUse), this.getWarriorsClass(map.getMap()[indexRows1][indexCols1], warriorsUse).getName(), warriorsSent,  warriorHandler.getDistance(map.getMap(), map.getMap()[indexRows1][indexCols1].getName(), map.getMap()[indexRows2][indexCols2].getName()));
                    }

                    break;
                case 5:
                    System.out.println("Se requiere indicar el planeta, y el constructor , ejemplo A1,Obrero");
                    System.out.println("Tipos de constructores: Obrero(40 Galactus), Maestro de Obra(50 Galactus), Arquitecto(70 Galactus), Ingeniero(100 Galactus)");
                    planeta = scanner.nextLine().toCharArray();
                    indexCols1 = planeta[0] - 65;
                           oracion ="";
                            for(int i=1;i<planeta.length;i++){
                                if(planeta[i] != ','){
                                    oracion += planeta[i];
                                }else{
                                    indexRows1 = Integer.parseInt(oracion)-1;
                                    indexChar = i+1;
                                    break;
                                }
                            }
                            
                    if(map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                        oracion ="";
                        for(int i=indexChar;i<planeta.length;i++){
                            oracion += planeta[i];
                        }

                        if(oracion.equalsIgnoreCase("Obrero")){
                            constructor = new Workman(map.getMap()[indexRows1][indexCols1]);
                        }else if(oracion.equalsIgnoreCase("Maestro de Obra")){
                            constructor = new Foreman(map.getMap()[indexRows1][indexCols1]);
                        }else if(oracion.equalsIgnoreCase("Arquitecto")){
                            constructor = new Architect(map.getMap()[indexRows1][indexCols1]);
                        }else if(oracion.equalsIgnoreCase("Ingeniero")){
                            constructor = new Engineer(map.getMap()[indexRows1][indexCols1]);
                        }else
                            System.out.println("Instruccion mal escrita, vuelve a intentar");

                        map.getMap()[indexRows1][indexCols1].createShip(constructor);
                    }else
                        System.out.println("No es tu planeta");
                    break;
                case 6:
                    try{
                    System.out.println("Bienvenido a la tienda");
                    System.out.println("Escoge una opcion");
                    System.out.println("1.- Comprar Constructor");
                    System.out.println("2.- Vender Constructor");
                    System.out.println("3.- Vender nave");
                    choice = Integer.parseInt(scanner.nextLine());
                    
                    switch(choice){
                        case 1:
                            System.out.println("Para comprar un constructor indique el planeta, y el tipo de constructor que desea, ejemplo A1,Obrero");
                            System.out.println("Tipos de constructores: Obrero(50 Galactus), Maestro de Obra(100 Galactus), Arquitecto(250 Galactus), Ingeniero(300 Galactus)");
                            planeta = scanner.nextLine().toCharArray();
                            indexCols1 = planeta[0] - 65;
                           oracion ="";
                            for(int i=1;i<planeta.length;i++){
                                if(planeta[i] != ','){
                                    oracion += planeta[i];
                                }else{
                                    indexRows1 = Integer.parseInt(oracion)-1;
                                    indexChar = i+1;
                                    break;
                                }
                            }
                            if(map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                                oracion ="";
                            for(int i=indexChar;i<planeta.length;i++){
                                oracion += planeta[i];
                            }

                            if(oracion.equalsIgnoreCase("Obrero")){
                                constructor = new Workman(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Maestro de Obra")){
                                constructor = new Foreman(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Arquitecto")){
                                constructor = new Architect(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Ingeniero")){
                                constructor = new Engineer(map.getMap()[indexRows1][indexCols1]);
                            }else
                                System.out.println("Instruccion mal escrita, vuelve a intentar");

                            map.getMap()[indexRows1][indexCols1] = planetHandler.buyConstructor(map.getMap()[indexRows1][indexCols1], constructor);
                            }else
                                System.out.println("No es tu planeta");

                            break;
                            
                        case 2:
                            System.out.println("Para vender un constructor indique el planeta, y el tipo de constructor que desea vender, ejemplo A1,Obrero");
                            System.out.println("Tipos de constructores: Obrero(40 Galactus), Maestro de Obra(70 Galactus), Arquitecto(175 Galactus), Ingeniero(200 Galactus)");
                            planeta = scanner.nextLine().toCharArray();
                            indexCols1 = planeta[0] - 65;
                           oracion ="";
                            for(int i=1;i<planeta.length;i++){
                                if(planeta[i] != ','){
                                    oracion += planeta[i];
                                }else{
                                    indexRows1 = Integer.parseInt(oracion)-1;
                                    indexChar = i+1;
                                    break;
                                }
                            }
                            
                            if(map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                                oracion ="";
                            for(int i=indexChar;i<planeta.length;i++){
                                oracion += planeta[i];
                            }

                            if(oracion.equalsIgnoreCase("Obrero")){
                                constructor = new Workman(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Maestro de Obra")){
                                constructor = new Foreman(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Arquitecto")){
                                constructor = new Architect(map.getMap()[indexRows1][indexCols1]);
                            }else if(oracion.equalsIgnoreCase("Ingeniero")){
                                constructor = new Engineer(map.getMap()[indexRows1][indexCols1]);
                            }else
                                System.out.println("Instruccion mal escrita, vuelve a intentar");

                                constructor.getData();
                            map.getMap()[indexRows1][indexCols1] = planetHandler.sellConstructor(map.getMap()[indexRows1][indexCols1], constructor);
                            }else
                                System.out.println("No es tu planeta");
                            
                            break;
                            
                        case 3:
                            System.out.println("Para vender una nave indica el planeta y el tipo de nave. Ejemplo A1,Naboo N-1");
                            System.out.println("Tipo de Naves: Naboo N-1(40 Galactus), X-Wing(50 Galactus), Millenial Falcon(70 Galactus), StarDestroyer(100 Galactus)");
                            planeta = scanner.nextLine().toCharArray();
                           
                            indexCols1 = planeta[0] - 65;
                           oracion ="";
                            for(int i=1;i<planeta.length;i++){
                                if(planeta[i] != ','){
                                    oracion += planeta[i];
                                }else{
                                    indexRows1 = Integer.parseInt(oracion)-1;
                                    indexChar = i+1;
                                    break;
                                }
                            }
                            
                            if(map.getMap()[indexRows1][indexCols1].getOwner().equalsIgnoreCase(player)){
                            oracion ="";
                                for(int i=indexChar;i<planeta.length;i++){
                                    oracion += planeta[i];
                                }

                            map.getMap()[indexRows1][indexCols1] = planetHandler.sellShip(map.getMap()[indexRows1][indexCols1], this.getShipClass(map.getMap()[indexRows1][indexCols1], oracion));
                            }else
                                System.out.println("No es tu planeta");
                                
                            break;
                        default:
                            System.out.println("Opcion Invalida");
                    }
                    }catch(Throwable e){
                        System.out.println("Error ejecutando comando, vuelve a intentar");
                    }
                    break;
                case 7:
                   this.getPropertys();
                    break;
                case 8:
                    
                    
                   state =false;
                   
                    break;
                    
            }
        
        
        
       }catch(Throwable e){
            System.out.println("Algo salio mal, vuelve a intentar");
       }
    }
        
        
        return map;
        
    }
    
    //Obtenemos un tipo de nave dependiendo del nombre de la nave ingresada
    private Ship getShipClass(Planets planet,String shipName){
        Ship shipSent=null;
        if(shipName.equalsIgnoreCase("Naboo N-1")){
            shipSent = new Naboo(planet);
        }else if (shipName.equalsIgnoreCase("X-Wing")){
            shipSent = new XWing(planet);
        }else if (shipName.equalsIgnoreCase("Millenial Falcon")){
            shipSent = new MillenialFalcon(planet);
        }else if (shipName.equalsIgnoreCase("StarDestroyer")){
            shipSent = new StarDestroyer(planet);
        }else{
            System.out.println("Nave no encontrada");
        }
        return shipSent;
    }
    //Obtenemos Un tipo de guerrero dependiendo del nombre del guerrero ingresado
    private Warriors getWarriorsClass(Planets planet, String warriorName){
        Warriors warriorsSent=null;
        
        if(warriorName.equalsIgnoreCase("Mole")){
            warriorsSent = new Mole(planet.getOwner(),planet.getDeathFactor());
        }else if (warriorName.equalsIgnoreCase("Nemo")){
            warriorsSent = new Nemo(planet.getOwner(),planet.getDeathFactor());
        }else if (warriorName.equalsIgnoreCase("Magma")){
            warriorsSent = new Magma(planet.getOwner(),planet.getDeathFactor());
        }else if (warriorName.equalsIgnoreCase("Groot")){
            warriorsSent = new Groot(planet.getOwner(),planet.getDeathFactor());
        }else if (warriorName.equalsIgnoreCase("Fision Guy")){
            warriorsSent = new FisionGuy(planet.getOwner(),planet.getDeathFactor());
        }else{
            System.out.println("Guerrero no encontrado");
        }
        return warriorsSent;
    }
    
    /**
     *Revisa si el jugador del objeto Usuario ha ganado, por medio de conquistando todos los planetas, verificando cada uno para obtener su dueño
     * @param numberPlanets
     * @return
     */
    public boolean checkVictory(int numberPlanets){
        int counter=0;
        for(int x=0;x<map.getMap().length;x++){
           for(int y=0;y<map.getMap()[0].length;y++){
               if(map.getMap()[x][y] != null){
                 try{
                  if(map.getMap()[x][y].getOwner()!=null){ 
                    if(map.getMap()[x][y].getOwner().equalsIgnoreCase(player)){
                        counter++;
                  }
               }
                  }catch(Throwable e){
                      System.out.println("Ha ocurrido un error");
                      return false;
                   }
                }
           }
        }
        
        if(counter == numberPlanets){
            System.out.println("El jugador "+player+" Ha ganado");
            return true;
        }
        
        return false;
    }
    //Es el menu para indicar la Distancia
    private boolean getDistance(){
        Scanner scanner = new Scanner(System.in);
        char[] planeta;
        int indexCols1;
        int indexRows1;
        int indexCols2;
        int indexRows2;
        WarriorHandler warriorHandler = new WarriorHandler();
        
        System.out.println("Coloca el Planeta de Origen y luego el planeta al dirigirse, como A1,B2");
                    planeta = scanner.nextLine().toCharArray();
                    
                    if(planeta.length!=5){
                        System.out.println("Comando mal escrito");
                        return false;
                    }
                    indexCols1 = planeta[0] - 65;
                    indexRows1 = planeta[1] -49;
                    indexCols2 = planeta[3] -65;
                    indexRows2 = planeta[4] -49;
                    

                    warriorHandler.getDistance(map.getMap(), map.getMap()[indexRows1][indexCols1].getName(), map.getMap()[indexRows2][indexCols2].getName());
        return true;
    }
    //Es el menu para indicar las propiedades del Usuario
    private void getPropertys() {
        for(int x=0;x<map.getMap().length;x++){
            for(int y=0;y<map.getMap()[0].length;y++){
                if(map.getMap()[x][y]!= null){
                    if( map.getMap()[x][y].getOwner()!= null)   { 
                        if(map.getMap()[x][y].getOwner().equalsIgnoreCase(player)){
                            map.getMap()[x][y].getData();
                            map.getMap()[x][y].showShip(0);
                            map.getMap()[x][y].showBuilder();
                        }
                    }
                }
            }
        }
        
    }
    //Obtiene Datos de Naves que se dirigin a algun lugar, esto se logra gracias a la variable esstado de la nave
    private void getShipOnGoing(){
        for(int x=0;x<map.getMap().length;x++){
            for(int y=0;y<map.getMap()[0].length;y++){
                if(map.getMap()[x][y] != null){
                    if(map.getMap()[x][y].getOwner()!= null){    
                        if(map.getMap()[x][y].getOwner().equalsIgnoreCase(player)){
                            map.getMap()[x][y].showShip(1);
                        }
                    }
                }
            }
        }
    }
}
