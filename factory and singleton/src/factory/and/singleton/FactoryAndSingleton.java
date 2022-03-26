/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.and.singleton;

/**
 *
 * @author ddh
 */
public class FactoryAndSingleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        map map = new map();

      Base shape1 = map.getShape("Ball");

      shape1.draw();

      Base shape2 = map.getShape("pinball");

      shape2.draw();

      Base shape3 = map.getShape("bouncers");

      shape3.draw();
    }
    
}
