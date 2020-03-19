package com.junlancer.flyBattleDemo;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.junlancer.flyBattleDemo.node.plane.*;
import com.junlancer.flyBattleDemo.controller.MyController;
import com.junlancer.utils.PointsDistance;

import java.util.ArrayList;

public class Main extends SimpleApplication {
    private static MyAPlane myAPlane;
    private APlane enemyPlane;
    public static AssetManager assetManager;
    public static InputManager inputManager;
    public static Node rootNode;
    public static ArrayList<Geometry> bullets = new ArrayList<>();
    public static ArrayList<Geometry> enemyPlanes = new ArrayList<>();

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0f, 3f, 10));
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        assetManager = super.assetManager;
        inputManager = super.inputManager;
        rootNode = super.rootNode;
        myAPlane = MyAPlane.getMyAPlane();
        new MyController(myAPlane);
        //加载主场景

        rootNode.attachChild(myAPlane);
        //rootNode.attachChild(bulletNode);
    }

    @Override
    public void simpleUpdate(float tpf) {
        try {
            //设置帧率
            Thread.sleep(21);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //bulletNode.move(myAPlane.getLocalTranslation());
        //控制生成的子弹自动移动
        //bulletNode.move(new Vector3f(0, tpf, 0));
        /*bulletNode.depthFirstTraversal(new SceneGraphVisitor() {
            @Override
            public void visit(Spatial spatial) {

                spatial.move(new Vector3f(0, 0.0001f, 0));

            }
        });*/
        for (Geometry geometry : bullets) {
            if (!rootNode.hasChild(geometry)) {
                rootNode.attachChild(geometry);
            }
            geometry.move(new Vector3f(0, 2 * tpf, 0));
        }
        if (enemyPlanes.size() < 5) {
            for (int i = 0; i < 5; i++) {
                enemyPlanes.add(new EnemyPlane(myAPlane.getLocalTranslation()));
            }
        }
        //敌机移动
        for (int i = 0; i < enemyPlanes.size(); i++) {
            rootNode.attachChild(enemyPlanes.get(i));
            if (PointsDistance.getPointsDistance(enemyPlanes.get(i).getLocalTranslation(), myAPlane.getLocalTranslation()) > 0.75f) {
                //未发生碰撞，移动
                enemyPlanes.get(i).move((myAPlane.getLocalTranslation().x - enemyPlanes.get(i).getLocalTranslation().x) * tpf, (myAPlane.getLocalTranslation().y - enemyPlanes.get(i).getLocalTranslation().y) * tpf, 0);
            }
        }
        //锁定视角
        //cam.setLocation(new Vector3f(myAPlane.getLocalTranslation().getX(),myAPlane.getLocalTranslation().getY(),10f));
    }

    //碰撞检测和伤害判断


    public static void main(String[] s) {
        Main lancher = new Main();
        AppSettings settings = new AppSettings(true);
        settings.setTitle("flyBattleDemo");// 标题
        settings.setResolution(480, 600);// 分辨率
        settings.setDepthBits(24);
        settings.setSamples(16);//抗锯齿
        //settings.setFullscreen(true);
        lancher.setSettings(settings);
        lancher.setShowSettings(false);
        lancher.start();
    }
}
