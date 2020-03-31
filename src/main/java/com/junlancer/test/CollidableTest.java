package com.junlancer.test;

import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.collision.UnsupportedCollisionException;

public class CollidableTest implements Collidable {
    @Override
    public int collideWith(Collidable collidable, CollisionResults collisionResults) throws UnsupportedCollisionException {
        return 0;
    }
}
