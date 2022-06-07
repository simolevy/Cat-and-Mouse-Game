# Cat-and-Mouse-Game
(copyright goes to Adam Aviv at The George Washington University for project creation and Plotter implementation)
A point simulation which displays a cat and mouse chasing game.
Level 0: Mice 

Full implement mice actions such that:

After 20 rounds of simulation time, a mouse produces a new baby mouse at the same location
A mouse dies after 30 rounds simulation time
A mouse randomly turns, changes directions 20% of the time
A mouse is displayed as a blue dot.

Level 1: Mice and Cats (up to 45 points) #

Add a cat to the simulation

A cat eats a mouse if they end up on the same location. That is, the mouse should die and be removed from the simulation.
If a cat doesn’t eat a mouse within 50 moves, the cat dies.
Cats jump two spaces at a time. They do not traverse the grid point they jump over. That is, if they are on space (1,2) they would move to (1,4).
Cats randomly turn, change direction, 5% of the time.
Cats are displayed as a yellow dot.
Additionally, in your simulator, have it such that

Every 100 rounds, a mouse is added to a random location in the city
Every 25 round, a cat is added to a random location in the city
Level 2: Cats chase mice (up to 55 points) #

In this level, cat’s get a bit smarter …

A cat searches up to 20 grid points (as measured by the GridPoint.distance() method) for a mouse to chase.
If the cat finds a mouse, it moves towards the mouse and is displayed using the color cyan. (This is to make it easier for you to debug, and for us to grade).
If the cat cannot find a mouse, it moves normaly and is displayed in yellow.

Level 3: Zombie-Cats chase Cats and Mice (up to 65 points) #

Let’s add zombie cats to the mix!

First a modification to Cats:

If Cat does not eat within 50 rounds, they instead turn into a Zombie Cat.
Now let’s define Zombie Cats:

Zombie cats chase both mice and other non-zombie cats
Zombie cats can search up to 40 gird squares away (as measured by GridPoint.distance()
Zombie cats eating a mouse is the same as a normal cat. The mouse dies and is removed from the simulation.
When a zombie cat eats a cat, that cat becomes a zombie cat placed at the same location in the grid square
A zombie cat when not chasing another creature is displayed as red dot.
A zombie cat chasing another creature is displayed as a black dot
A zombie cat jumps 3 spaces at time. It does not move through the intervening space. That is, if it is at (5,10) it moves directly to (5,13).
A zombie cat that doesn’t eat anything within 100 rounds dies.

Level 4: Create a new creature type (up to 75 points) #

The final level of the simulation is for you to add a new creature of any type or behavior you want to the simulation. It should fit into the general model of creatures we already have and derive from Creature.

You should include this extra creature in the planning stage (Part A of the project), and fully describe its functionality in the README.md file.

Particularly creative creatures may be subject to bonus points (up to 5), at the description of the grader.
