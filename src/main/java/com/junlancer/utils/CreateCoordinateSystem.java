package com.junlancer.utils;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.debug.Arrow;

public class CreateCoordinateSystem {
    /**
     * 创建一个箭头
     */
    public static Geometry[] createArrow(AssetManager assetManager) {
        // 创建材质，设定箭头的颜色
        // 创建几何物体，应用箭头网格。
        Geometry arrowX = new Geometry("arrowX", new Arrow(new Vector3f(10, 0, 0)));
        Material matX = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matX.setColor("Color", ColorRGBA.Green);
        arrowX.setMaterial(matX);
        Geometry arrowY = new Geometry("arrowY", new Arrow(new Vector3f(0, 10, 0)));
        Material matY = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matY.setColor("Color", ColorRGBA.Red);
        arrowY.setMaterial(matY);
        Geometry arrowZ = new Geometry("arrowZ", new Arrow(new Vector3f(0, 0, 10)));
        Material matZ = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matZ.setColor("Color", ColorRGBA.Blue);
        arrowZ.setMaterial(matZ);
        return new Geometry[]{
                arrowX, arrowY, arrowZ
        };
    }
}
