import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
	
    LinkedList<EntityObject> objectList = new LinkedList();
    private final double GRAVITY = 2;
    
    public void tick(){

        for (EntityObject tempObject : objectList) {
            tempObject.tick();
            
            tempObject.VelY += GRAVITY;
            
            if(tempObject.isOnGround()){
                tempObject.setY(Game.FRAME_HEIGHT - tempObject.getHeight());
                tempObject.setVelY(tempObject.getVelY() - GRAVITY);
            }
            
        }

    }


    public void render(Graphics g){
        for (EntityObject tempObject : objectList) {
            tempObject.render(g);
        }
    }


    /**
    * Adds the specified EntityObject.
    * @param entityObject EntityObject to add.
    * @see removeObject(EntityObject entityObject)
    */
    public void addObject(EntityObject entityObject){
        objectList.add(entityObject);
    }
	
    /**
     * Removes the specified EntityObject.
     * @param entityObject EntityObject to remove.
     * @see addObject(EntityObject entityObject)
     */
    public void removeObject(EntityObject entityObject){
            objectList.remove(entityObject);
	}
	
	
            
}
