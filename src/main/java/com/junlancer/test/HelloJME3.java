package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;

public class HelloJME3 extends SimpleApplication {
    private Geometry geom;
    /**
     * 初始化3D场景，显示一个方块。
     */
    @Override
    public void simpleInitApp() {
        // #1 创建一个方块形状的网格
        Mesh box = new Box(1, 1, 1);

        // #2 加载一个感光材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        //mat.setColor("Color", ColorRGBA.Blue);,发光才能设置颜色
        // #3 创建一个几何体，应用刚才和网格和材质。
        geom = new Geometry("Box");
        geom.setMesh(box);
        geom.setMaterial(mat);

        // #4 创建一束定向光，并让它斜向下照射，好使我们能够看清那个方块。
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // #5 将方块和光源都添加到场景图中
        //jME3是一个基于场景图 Scenegraph的3D引擎，SimpleApplication中整个场景的原点就是rootNode，
        // 3D模型和光源都要添加到rootNode中才会生效，否则是看不到的。
        rootNode.attachChild(geom);
        rootNode.addLight(sun);

        // 初始化相机的位置
        cam.setLocation(new Vector3f(4.4114223f, 3.3620508f, 7.5415998f));
        cam.setRotation(new Quaternion(-0.046265673f, 0.9518722f, -0.1815604f, -0.2425582f));
    }

    /**
     * 主循环
     */
    @Override
    public void simpleUpdate(float deltaTime) {
        // 旋转速度：每秒360°
        float speed = FastMath.TWO_PI;
        // 让方块匀速旋转
        geom.rotate(0, deltaTime * speed, 0);
    }

    public static void main(String[] args) {
        // 配置参数
        AppSettings settings = new AppSettings(true);
        settings.setTitle("一个方块");// 标题
        settings.setResolution(640, 480);// 分辨率

        // 启动jME3程序
        HelloJME3 app = new HelloJME3();
        app.setSettings(settings);// 应用参数
        app.setShowSettings(false);
        app.start();
    }
}
