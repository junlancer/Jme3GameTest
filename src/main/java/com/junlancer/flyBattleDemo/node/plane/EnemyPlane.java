package com.junlancer.flyBattleDemo.node.plane;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.junlancer.flyBattleDemo.Main;

import java.util.Random;

public class EnemyPlane extends Geometry {
    public EnemyPlane(Vector3f location) {
        // 创建球体
        super("球体", new Sphere(8, 15, 0.25f));
        // 创建材质，并显示网格线
        Material mat = new Material(Main.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", ColorRGBA.Blue);
        super.setMaterial(mat);
        this.move(createLocation(location));
    }

    //随机生存出生地
    private Vector3f createLocation(Vector3f location) {
        Random random = new Random();
        float x = 0f;
        if (random.nextBoolean()) {
            x = location.getX() + random.nextInt(4);
        } else {
            x = location.getX() - random.nextInt(4);
        }
        float y = location.getY() + 7.2f;
        return new Vector3f(x, y, 0);
    }
}
