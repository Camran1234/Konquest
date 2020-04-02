/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest;

import Handlers.MapHandler;
import Handlers.PlanetHandler;
import Handlers.PlayerHandler;
import Mkworlds.Map;
import Planet.Earth;
import Planet.Planets;

/**
 *
 * @author camran
 */
public class Konquest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice=0;
        PlayerHandler handler = new PlayerHandler();
        //Inicializamos el juego
         while(true){
             //Accede al menu principal del juego
            choice = handler.getMenu();
            //Opcion para comprobar si se sale del programa
            if (choice == 3){
                System.out.println("Hasta luego");
                break;
            }
            handler.choice(choice);
        
         }
    }
    
}
