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
public class map {
    public Base getShape(String shapeType){
      if(shapeType == null){
         return null;
      }		
      if(shapeType.equalsIgnoreCase("Ball")){
         return new ball();
         
      } else if(shapeType.equalsIgnoreCase("bouncers")){
         return new bouncers();
         
      } else if(shapeType.equalsIgnoreCase("pinball")){
         return new pinball();
      }
      
      return null;
   }
}
