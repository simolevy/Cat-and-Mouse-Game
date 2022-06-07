import java.util.*;

/*Zombie cats chase both mice and other non-zombie cats
Zombie cats eating a mouse is the same as a normal cat. The mouse dies and is removed from the simulation.
When a zombie cat eats a cat, that cat becomes a zombie cat placed at the same location in the grid square
A zombie cat when not chasing another creature is displayed as red dot.
A zombie cat chasing another creature is displayed as a black dot*/

public class ZombieCat extends Creature {

public ZombieCat(int x, int y, City city, Random rand){
        super(x, y, city, rand);
        this.lab = LAB_RED;
        this.stepLen = 3;
        this.numRounds = 1;

    }

    @Override
    public void takeAction() {
        // TODO Auto-generated method stub

        if (this.numRounds == 100){ // if zombcat gets to 100 rounds without eating. this is set to 0 after eating.
            this.dead = true;
        }

        if(rand.nextInt(5) == 0){  //turn 25 percent of time
            this.turn();
        }

        this.numRounds++;

        search();
    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
        int x = (this.getX()+ (3*dirX[getDir()]) + City.WIDTH) % City.WIDTH;
        int y = (this.getY()  + (3*dirY[getDir()]) + City.HEIGHT) % City.HEIGHT;

        setPoint(x, y);
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
        int i = 40;

        for(Creature c : city.creatures){
            int d = this.dist(c);
            if(d<=i && (c instanceof Mouse || c instanceof Cat) && !(c instanceof ZombieCat)){
                this.lab = LAB_BLACK;
                i=d;
                preyCreature = c;
            }

        }
       if (preyCreature != null){
           chase(preyCreature);
           return true;
       }

       this.lab = LAB_RED;
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
        this.numRounds = 0;

        if(creature instanceof Mouse){
            creature.dead = true;
        } else {
            creature.dead = true;
            city.creaturesToAdd.add(new ZombieCat(this.getX(),this.getY(),this.city,this.rand));
        }

    }

    
}
