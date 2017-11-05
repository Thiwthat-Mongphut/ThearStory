package Main.Entities.Creatures;

import Main.Entities.Entity;

public abstract class Creature extends Entity{
    public static int DEFAULT_SPEED = 2;
    public static int DEFAULT_HEALTH = 10;
    
    protected int health;
    protected float speed;
    
    public Creature(float x, float y) {
        super(x, y);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
    }
    
}
