package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.junlancer.utils.CreateCoordinateSystem;

public class ImpactChecking extends SimpleApplication {
    private Geometry geometry;
    private Geometry geometry2;
    private Geometry[] coordinateSystem;

    @Override
    public void simpleInitApp() {
        //球体的范围
        geometry = new Geometry("sphere", new Sphere(16, 1000, 1f));
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Green);
        geometry.setMaterial(mat);
        rootNode.attachChild(geometry);

        geometry2 = new Geometry("sphere", new Sphere(16, 1000, 1f));
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Blue);
        geometry2.setMaterial(mat2);
        geometry2.move(1, 0, 0);
        rootNode.attachChild(geometry2);


        //建立坐标系
        coordinateSystem = CreateCoordinateSystem.createArrow(assetManager);
        for (int i = 0; i < coordinateSystem.length; i++) {
            rootNode.attachChild(coordinateSystem[i]);
        }
    }


    public static void main(String[] s) {
        ImpactChecking lancher = new ImpactChecking();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("FlyBattleDemo");// 标题
        settings.setResolution(1600, 900);// 分辨率
        settings.setDepthBits(24);
        settings.setSamples(16);//抗锯齿
        //settings.setFullscreen(true);
        lancher.setSettings(settings);
        lancher.setShowSettings(false);
        lancher.start();
    }
}
