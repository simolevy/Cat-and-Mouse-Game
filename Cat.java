
import java.util.*;

//A cat eats a mouse if they end up on the same location. That is, the mouse should die and be removed from the simulation.
// Cats jump two spaces at a time. They do not traverse the grid point they jump over. 
//That is, if they are on space (1,2) they would move to (1,4).


public class Cat extends Creature {

    private int numEatenRounds;
    private boolean isChasing = false;


    public Cat(int x, int y, City city, Random rand){
        super(x, y, city, rand);
        this.numRounds = 1;
        int stepLen = 2;
        this.lab = LAB_YELLOW;

    }

    @Override
    public void takeAction() {
        if (this.numEatenRounds == 50){
            this.dead = true;
            city.creaturesToAdd.add(new ZombieCat(this.getX(),this.getY(),this.city,this.rand));
        }

        if(rand.nextInt(5) == 0){  //turn 5 percent of time
            this.turn();
        }

        if (this.numRounds % 25 == 0){
            city.addCat();
        }
        this.numRounds++;
        this.numEatenRounds++;
    

        search();

    }

    public void turn(){
        int i = rand.nextInt(3);

        if (i == 1){
            this.leftTurn(this);
            return;
        }

        if (i == 2){
            this.rightTurn(this);
            return;
        }

        

    }


    public boolean search(){

        Creature preyCreature = null;
        int i = 20;

        for(Creature c : city.creatures){
            int d = this.dist(c);
            if(d<=i && c instanceof Mouse){
                this.lab = LAB_CYAN;
                i=d;
                preyCreature = c;
            }

        }
       if (preyCreature != null){
           chase(preyCreature);
           return true;
       }

       this.lab = LAB_YELLOW;
       return false;


    }

    public void chase(Creature prey){
        // set x,y vals to chase prey
        // if cat on prey, eat.

        //find dir of longest dist to mouse
        //find longest dist and set dir to longest dist (x or y)

        if ((Math.abs(this.getX()-prey.getX()) > Math.abs(this.getY()-prey.getY()))){
            if(this.getX() < prey.getX()){
                this.setDir(EAST);
            }
            if(this.getX() > prey.getX()){
                this.setDir(WEST);
            }
        }
        else {
            if(this.getY() < prey.getY()){
                this.setDir(SOUTH);
            }
            if (this.getY() > prey.getY()){
                this.setDir(NORTH);
            }
        }
        
        if(this.getX() == prey.getX() && this.getY() == prey.getY()){
            eat(prey);
        }
    }

    public void eat(Creature creature){
        this.numEatenRounds = 0;
        creature.dead = true;

    }

    @Override
    public void step() {
        
            int x = (this.getX()+ (2*dirX[getDir()]) + City.WIDTH) % City.WIDTH;
            int y = (this.getY()  + (2*dirY[getDir()]) + City.HEIGHT) % City.HEIGHT;

            setPoint(x, y);
    
    
        
        
    }


    


   
    
}
