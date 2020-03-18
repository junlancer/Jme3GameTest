package com.junlancer.flyBattleDemo.node.bullet;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.junlancer.flyBattleDemo.Main;

public class MyBullet extends Geometry {
    public MyBullet(Vector3f location) {
        // 创建球体
        super("球体", new Sphere(8, 10, 0.1f));
        // 创建材质，并显示网格线
        Material mat = new Material(Main.assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.getAdditionalRenderState().setWireframe(true);
        mat.setColor("Color", ColorRGBA.Red);
        super.setMaterial(mat);
        this.move(location);
        Main.bullets.add(this);
    }

}
