import java.security.DigestException;
import java.util.*;

//After 20 rounds of simulation time, a mouse produces a new baby mouse at the same location
//A mouse dies after 30 rounds simulation time
//A mouse randomly turns, changes directions 20% of the time
//A mouse is displayed as a blue dot.

public class Mouse extends Creature {


public Mouse(int x, int y, City city, Random rand){
        super(x, y, city, rand);
        this.lab = LAB_BLUE;
        this.numRounds = 1;
    }

    


    @Override
    public void takeAction() {
        // TODO Auto-generated method stub
        super.takeAction();

            if (this.numRounds % 100 == 0){
                city.addMouse();
            }
    
            if(this.numRounds == 30){
                this.die();
            }

            if(this.numRounds % 20 == 0){
                // add a new mouse every 20 rounds
                city.creaturesToAdd.add(new Mouse(this.getX(),this.getY(),this.city,this.rand));
            }

            if(rand.nextInt(20) == 0){  //turn 25 percent of time
                this.turn();
            }

        
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

    
    public void die(){
        this.dead = true;
    }

    

}
