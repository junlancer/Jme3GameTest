package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.debug.Arrow;

public class LoadModel extends SimpleApplication {
    private Spatial model;

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0.41600543f, 3.2057908f, 6.6927643f));
        cam.setRotation(new Quaternion(-0.00414816f, 0.9817784f, -0.18875499f, -0.021575727f));


        // 创建X、Y、Z方向的箭头，作为参考坐标系。
        //createArrow(new Vector3f(5, 0, 0), ColorRGBA.Green);
        //createArrow(new Vector3f(0, 5, 0), ColorRGBA.Red);
        //createArrow(new Vector3f(0, 0, 5), ColorRGBA.Blue);

        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        // 加载模型
        model = assetManager.loadModel("Models/zhangying/zhangying.obj");
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Red);
        model.setMaterial(mat);
        //model.scale(0.1f);// 按比例缩小
        model.center();// 将模型的中心移到原点

        // #2 创造光源
        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));
        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.6f));
        ambient.setColor(lightColor.mult(0.4f));

        // #3 将模型和光源添加到场景图中
        rootNode.attachChild(model);
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }

    @Override
    public void simpleUpdate(float tpf) {
        // 旋转速度：每秒360°
        float speed = FastMath.TWO_PI;
        // 让方块匀速旋转
        model.rotate(0, tpf, 0);
    }

    public static void main(String[] args) {
        // 启动程序
        LoadModel app = new LoadModel();
        app.start();
    }
    /**
     * 创建一个箭头
     *
     * @param vec3  箭头向量
     * @param color 箭头颜色
     */
    private void createArrow(Vector3f vec3, ColorRGBA color) {
        // 创建材质，设定箭头的颜色
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);

        // 创建几何物体，应用箭头网格。
        Geometry geom = new Geometry("arrow", new Arrow(vec3));
        geom.setMaterial(mat);

        // 添加到场景中
        rootNode.attachChild(geom);
    }
}
