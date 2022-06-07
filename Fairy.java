import java.security.DigestException;
import java.util.*;

//The fairy goes around reviving zombie cats
//If a fairy lands on a Zombie cat it will set it back to a cat.
//Fairies can't be eaten but they die every 30 rounds
//When a fairy revives a cat it uses all its power and dies
//When a fairy is chasing to revive a cat its color continusouly changes

public class Fairy extends Creature {


public Fairy(int x, int y, City city, Random rand){
        super(x, y, city, rand);
        this.lab = LAB_PINK;
        this.numRounds = 1;

    }

    @Override
    public void step() {
        // TODO Auto-generated method stub
        int x = (this.getX()+ (3*dirX[getDir()]) + City.WIDTH) % City.WIDTH;
        int y = (this.getY()  + (3*dirY[getDir()]) + City.HEIGHT) % City.HEIGHT;

        setPoint(x, y);
    }

    


    @Override
    public void takeAction() {
        // TODO Auto-generated method stub

            if (this.numRounds % 33 == 0){ // after 33 rounds spawn a fairy
                city.addFairy();
            }
    
            if(this.numRounds == 30){  // after 30 rounds fairy dies :( 
                this.die();
            }

            if(rand.nextInt(3) == 0){  //turn 3 percent of time
                this.turn();
            }

            search();

        
        this.numRounds++;
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
        int i = 25; //fairy searches if in 25 distance

        for(Creature c : city.creatures){
            int d = this.dist(c);
            if(d<=i && (c instanceof ZombieCat)){ // if fairy finds a zombie cat
                this.lab = LAB_MAGENTA;
                i=d;
                preyCreature = c;
            }

        }
       if (preyCreature != null){
           chase(preyCreature);
           return true;
       }

       this.lab = LAB_PINK;
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
                this.lab = LAB_PINK;
            }
            if(this.getX() > prey.getX()){
                this.setDir(WEST);
                this.lab = LAB_MAGENTA;
            }
        }
        else {
            if(this.getY() < prey.getY()){
                this.setDir(SOUTH);
                this.lab = LAB_PINK;
            }
            if (this.getY() > prey.getY()){
                this.setDir(NORTH);
                this.lab = LAB_MAGENTA;
            }
        }
        
        if(this.getX() == prey.getX() && this.getY() == prey.getY()){
            revive(prey);
        }
    }



    
    public void die(){
        this.dead = true;
    }

    public void revive(Creature creature){

        city.creaturesToAdd.add(new Cat(this.getX(),this.getY(),this.city,this.rand)); // new cat revived
        creature.dead=true; // zombie cat dies
        this.dead = true; // fairy dies

    }

    

}
