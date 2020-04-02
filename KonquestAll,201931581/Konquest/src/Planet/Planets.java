/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Planet;
import java.util.Random;

import Constructor.Constructors;
import Constructor.Workman;
import Cruise.Naboo;
import Cruise.Ship;
import Warrior.Warriors;
import java.util.Random;
/**
 *
 * @author camran
 */
public class Planets {
    String name="";
    String state = null;
    protected String typePlanet;
    final double deathPercent = Math.random();
    int valueGalactus;
    Warriors[] warriorList = new Warriors[0];
    Ship[] ships = new Ship[1];
    Constructors[] constructorList = new Constructors[1];
    
    public Planets(){
        Random random = new Random();
        valueGalactus = random.nextInt(401)+100;
        for (int i=0; i<6;i++){
            name = name + (char) (random.nextInt(26)+65);
        }
    }
    
    /**
     * Return planet Name in a String
     * @return
     */
    public String getName(){
        return name;
    }
    
    /**
     *Return the death Factor of the planet
     * @return
     */
    public double getDeathFactor(){
        return this.deathPercent;
    }
    
    /**
     * Return Owner, in a String
     * @return
     */
    public String getOwner(){
        return state;
    }
    
    /**
     *Print some data about the planet
     */
    public void getData(){
        System.out.println("Nombre: "+name+" Propietario: "+state+" Tipo Planeta: "+typePlanet+" Porcentaje de Muerte: "+ deathPercent+ " Numero de Guerreros: " + warriorList.length+" Numero de Naves: "+this.ships.length+" Dinero: "+valueGalactus+" Galactus");
    }
    
    /**
     * Return list of Warriors of the planet
     * @return
     */
    public Warriors[] getWarriors(){
        return warriorList;
    }
    
    public int getMoney(){
        return valueGalactus;
    }
    
    public void newMoney(int galactus){
        this.valueGalactus = galactus;
    }
    
    public Constructors[] getBuilders(){
        return constructorList;
    }
    
    public Ship[] getShips(){
        return ships;
    }
    /**
     *  Eliminate warriors and just keep the number of warriors indicated
     * @param warriorlength
     * @return
     */
    public boolean leftWarriors(int warriorlength){
        if (warriorlength<warriorList.length){
            Warriors[] warriorAux = new Warriors[warriorlength];
            
            for (int i=0;i<warriorlength;i++){ 
                warriorAux[i] = warriorList[i]; 
            }
            
            warriorList = warriorAux;
            return true;
        }
        return false;
    }
    
    /**
     * Unify the warriors array to the array of warriors of the planet
     * @param warriors
     */
    public void welcomeAllies(Warriors[] warriors){
        int contadora=0;
        Warriors[] warriorAux = new Warriors[warriorList.length + warriors.length];
        for (int i=0; i<warriorList.length;i++){
            warriorAux[i] = warriorList[i];
        }
        try{
        if(warriorList.length >0){
            for (int i=warriorList.length-1; i<warriorAux.length-1;i++){
        
                warriorAux[i] = warriors[contadora];
                contadora++;
            }
        }else{
            for (int i=0; i<warriorAux.length-1;i++){
                
                warriorAux[i] = warriors[contadora];
                contadora++;
            }
        }
        }catch(Throwable e){
            System.out.println("Ocurrio un error");
        }
        System.out.println("Guerreros que se unen: "+warriors.length);
        warriorList = warriorAux;
    }
    
    public void addShip(Ship ship){
        Ship[] shipAux = new Ship[1 + ships.length];
        for (int i=0;i<ships.length;i++){
            shipAux[i] = ships[i];
        }
        shipAux[ships.length] = ship;
        ships = shipAux;
    }
    
    public void createShip(Constructors constructor){
        int encontrado=0;
        for(int i=0;i<constructorList.length;i++){
            if(constructorList[i].getName() == constructor.getName() && constructorList[i].getState() != true){
                if((valueGalactus - constructorList[i].getShipClass().getPrice()) > 0){
                valueGalactus -= constructorList[i].getShipClass().getPrice();
                constructorList[i].createShip();
                encontrado=1;
                break;
                }
                else{
                    System.out.println("Dineor insuficiente");
                }
            }
        }
        
        if(encontrado==0){
            System.out.println("No se hallo ningun constructor");
        }else
            System.out.println("Empezando a trabajar");
        
    }
    
    /**
     *Add a builder to the planet
     * @param constructor
     */
    public void addBuilder(Constructors constructor){
        Constructors[] builderAux = new Constructors[1 + constructorList.length];
        for (int i=0;i<constructorList.length;i++){
            builderAux[i] = constructorList[i];
        }
        builderAux[constructorList.length] = constructor;
        constructorList = builderAux;
    }
    
    /**
     *Show data of the list of ships of the planet
     * @param choice
     */
    public void showShip(int choice){
        if(choice==0){
            for(int i=0;i<this.ships.length;i++){
                System.out.print("Nave "+(i+1)+") ");
                ships[i].getData();
            }
        }else{
            for(int i=0;i<this.ships.length;i++){
                System.out.print("Nave "+(i+1)+") ");
                ships[i].getDataOn();
            }
        }
    }
    
    /**
     * Introduce a la lista de constructores los constructores indicados 
     * @param builders
     */
    public void newBuilder(Constructors[] builders){
        constructorList = builders;
    }
    
    /**
     *The list of the ships of the planet will be now the Array ship as parameter
     * @param ship
     */
    public void newShip(Ship[] ship){
        this.ships = ship;
    }
    
    /**
     * Change de owner of the planet
     * @param owner
     */
    public void addOwner(String owner){
        this.state = owner;
    }
    
    public void newName(String name){
        this.name = name;
    }
    
    public void newValueGalactus(int valueGalactus){
        this.valueGalactus = valueGalactus;
    }

    public void showBuilder() {
        for(int i=0;i<this.constructorList.length;i++){
                System.out.print("Constructor "+(i+1)+") ");
                constructorList[i].getData();
            }
    }

    //Dejamos estas dos funciones sin nada que escribir ya que utilizaremos polimorfismo en las clases hijas, clases que se usan en el juego.
    public void addWarrior() {
    }

    public void addGalactus() {
    }
    
    public void restart() {
        warriorList = new Warriors[0];
        ships = new Ship[1];
        constructorList = new Constructors[1];
        state = null;
    }
    
    
    
}
