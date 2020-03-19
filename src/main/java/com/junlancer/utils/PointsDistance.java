package com.junlancer.utils;

import com.jme3.math.Vector3f;

public class PointsDistance {
    public static float getPointsDistance(Vector3f a, Vector3f b) {
        return (float) Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y) + (a.z - b.z) * (a.z - b.z));

    }
}
