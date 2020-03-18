package com.junlancer.FlyBattleDemo.Node.plane;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.junlancer.FlyBattleDemo.Main;
import com.junlancer.FlyBattleDemo.Node.bullet.MyBullet;

public class MyAPlane extends Geometry {
    private int vlaue = 100;

    private static volatile MyAPlane myAPlane;

    public static MyAPlane getMyAPlane() {
        if (myAPlane == null) {
            synchronized (MyAPlane.class) {
                if (myAPlane == null) {
                    myAPlane = new MyAPlane();
                }
            }
        }
        return myAPlane;
    }

    private MyAPlane() {
        // 创建球体
        super("球体", new Sphere(10, 20, 0.5f));
        // 创建材质，并显示网格线
        Material mat = new Material(Main.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", ColorRGBA.Green);
        super.setMaterial(mat);
    }

    public void fire() {
        new MyBullet(myAPlane.getLocalTranslation());
    }

}
