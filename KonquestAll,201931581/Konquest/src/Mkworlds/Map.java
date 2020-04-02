/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mkworlds;

import Planet.Planets;
/**
 *
 * @author camran
 */
public class Map implements Cloneable{
    
    private Planets[][] map;
    private String player1;
    private String player2;
    
    
    public Map(Planets[][] map, String player1, String player2){
        this.map = map; 
        this.player1 = player1;
        this.player2 = player2;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException { 
        return super.clone(); 
    } 
    
    public Planets[][] getMap(){
        return map;
    }
    
    public String[] getPlayers(){
        String[] player = new String[2];
        player[0] = player1;
        player[1] = player2;
        
        return player;
    }
    public void getData(){
        int counter = 0;
        
        for (int x=0;x<map.length;x++){
            for (int y=0;y<map[0].length;y++){
                if(map[x][y]!= null)
                    counter++;
            }
        }
        System.out.println("Filas: "+ map.length + " Columnas: "+map[0].length+" Planetas:" + counter + " Jugadores: "+ player1 +" y "+player2 );
            
            
    }
    
    /**
     *Retorna el numero de planetas que no sean nulos de un mapa
     * @return
     */
    public int getNumberPlanets(){
        int counter=0;
        Planets[] planets;
        for (int x=0;x<map.length;x++){
            for (int y=0;y<map[0].length;y++){
                if(map[x][y]!= null)
                    counter++;
            }
        }
    
        return counter;
    }
    
    /**
     *Imprimir el Mapa
     * @param jugador1
     * @param jugador2
     */
    public void printMap(String jugador1, String jugador2){
        int espaciosBlancos=0;
        String oracion;
        String size;
        for (int x=0;x<map[0].length;x++){
            if(map.length>=9){
                System.out.print("+XXXX  "+ (char) (65+x) +" XXXXX+ ");
            }else
                System.out.print("+XXXX  "+(char) (65+x)+" XXXXXX+ ");
        }
        
        System.out.println("");
        for(int x=0;x<map.length;x++){
            System.out.print((x+1));
            for(int y=0;y<map[0].length;y++) {
                if(map[x][y]!= null) {        
                    oracion = "Nombre: " + map[x][y].getName();
                    
                    if(x<=9){
                        if(map[x][y].getOwner() != null) {
                            if (map[x][y].getOwner().equalsIgnoreCase(jugador1)){
                                System.out.print("\033[31mNombre:" + map[x][y].getName() + "\033[0m X");                   
                            }
                            else{
                                System.out.print("\033[33mNombre:" + map[x][y].getName() + "\033[0m X");
                            }
                        } else {
                            System.out.print("Nombre:" + map[x][y].getName() + " X");     
                        }
                
                    }
                    
                    else{
                        if(map[x][y].getOwner() != null) {
                            if (map[x][y].getOwner().equalsIgnoreCase(jugador1)){
                                System.out.print("\033[31mNombre:" + map[x][y].getName() + "\033[0mX");                   
                            }
                            else{
                                System.out.print("\033[33mNombre:" + map[x][y].getName() + "\033[0mX");
                            }
                        } else {
                            System.out.print("Nombre:" + map[x][y].getName() + "X");     
                        }
                    }
                }
                else{
                    if(x>=9 && y == 0){
                        System.out.print("               X");
                    }
                    else
                        System.out.print("                X");
                }
                
            }
            System.out.print("\nX");
            for(int y=0;y<map[0].length;y++) {
                if(map[x][y]!= null) {
                    oracion = "Dueño:"+map[x][y].getOwner() + " X";
                    if(oracion.length() > 16){
                        size = map[x][y].getOwner();
                        if(size.length()>8){
                            oracion="Dueño:";
                            for(int i=0;i<8;i++){
                               oracion += size.charAt(i);
                            }
                        }
                    }
                    else if (oracion.length()<16){
                        espaciosBlancos = 16-oracion.length();
                        oracion = "Dueño:"+map[x][y].getOwner();
                        for (int i=0;i<espaciosBlancos;i++){
                            oracion += " ";
                        }
                    }
                    else if (oracion.length()==16)
                        oracion = "Dueño:"+map[x][y].getOwner();
                    if(map[x][y].getOwner() != null) {
                        if (map[x][y].getOwner().equalsIgnoreCase(jugador1)){
                            System.out.print("\033[31m"+oracion+ "\033[0m  X");                   
                        }
                        else{
                           System.out.print("\033[33m"+oracion+ "\033[0m  X");
                        }
                    } else {
                        System.out.print(oracion+"  X");     
                    }
                }
                else{
                    System.out.print("                X");
                }
            }    
            
            System.out.println(" ");
            for (int d=0;d<map[0].length;d++){
            System.out.print("XXXXXXXXXXXXXXXXX");
            }
             System.out.println(" ");
        }
    }
    
    public void restart() {

        for(int i = 0; i < map.length;i++) {
            for(int j = 0; j <map[0].length;j++) {
                if(map[i][j] != null) map[i][j].restart();
            }
        }
    }
}
