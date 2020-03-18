package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Zhangying extends SimpleApplication {
    private Spatial model;
    private Spatial model2;
    private int size = 250;
    private int showMe = 0;
    private boolean flag = false;
    private static Player player = null;
    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(0.0f, 0.0f, 10.0f));
        cam.setRotation(new Quaternion(3.219189E-5f, 0.9999101f, -0.0024412838f, 0.01318245f));
        flyCam.setMoveSpeed(10);
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.Yellow);// 在漫射光照射下反射的颜色。
        mat.setColor("Ambient", ColorRGBA.Red);// 在环境光照射下，反射的颜色。
        mat.setColor("Specular", ColorRGBA.White);// 镜面反射时，高光的颜色。
        // 反光度越低，光斑越大，亮度越低。
        mat.setFloat("Shininess", 32);// 反光度
        // 使用上面设置的Diffuse、Ambient、Specular等颜色
        mat.setBoolean("UseMaterialColors", true);
        //Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.setColor("Color", ColorRGBA.Red);
        // 加载模型
        model = assetManager.loadModel("Models/zhangying/zhangying.obj");


        model.setMaterial(mat);
        model.scale(0.1f);// 按比例缩小
        model.center();// 将模型的中心移到原点
        addLight();
        // #3 将模型和光源添加到场景图中
        rootNode.attachChild(model);
    }

    @Override
    public void simpleUpdate(float tpf) {
        showMe++;
        if (showMe >= 700) {
            model.removeFromParent();
            if (model2 == null) {
                addBenjun();
                rootNode.attachChild(model2);
            }
            showMe = 700;
        }

        try {
            Thread.sleep(21);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 让方块匀速旋转
        model.rotate(0, tpf, 0);
        if (size < 250) {
            flag = true;
        }
        if (size > 500) {
            flag = false;
        }
        if (flag) {
            model.scale(1.01f);
            if (model2 != null)
            model2.scale(1.01f);
            size++;
        } else {
            model.scale(0.99f);
            if (model2 != null)
            model2.scale(0.99f);
            size--;
        }

    }

    public static void main(String[] args) {
        // 配置参数
        AppSettings settings = new AppSettings(true);
        settings.setTitle("To张颖");// 标题
        settings.setResolution(1920, 1080);// 分辨率
        settings.setDepthBits(24);
        settings.setSamples(16);//抗锯齿
        settings.setFullscreen(true);
        // 启动jME3程序
        Zhangying app = new Zhangying();
        app.setSettings(settings);// 应用参数
        app.setShowSettings(false);
        app.start();
        try {
            //声明一个File对象
            File mp3 = new File("C:\\haorizi.mp3");

            //创建一个输入流
            FileInputStream fileInputStream = new FileInputStream(mp3);

            //创建一个缓冲流
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            //创建播放器对象，把文件的缓冲流传入进去
            player = new Player(bufferedInputStream);

            //调用播放方法进行播放
            player.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加光源
     */
    private void addLight() {
        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-10, -20, -30));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(1f));
        ambient.setColor(lightColor.mult(0.3f));

        // #3 将模型和光源添加到场景图中
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }

    private void addBenjun() {
        model2 = assetManager.loadModel("Models/benjun/benjun.obj");

        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setColor("Diffuse", ColorRGBA.Pink);// 在漫射光照射下反射的颜色。
        mat.setColor("Ambient", ColorRGBA.Pink);// 在环境光照射下，反射的颜色。
        mat.setColor("Specular", ColorRGBA.White);// 镜面反射时，高光的颜色。
        // 反光度越低，光斑越大，亮度越低。
        mat.setFloat("Shininess", 32);// 反光度
        // 使用上面设置的Diffuse、Ambient、Specular等颜色
        mat.setBoolean("UseMaterialColors", true);
        model.setMaterial(mat);
        model2.setMaterial(mat);
        model.scale(0.1f);// 按比例缩小
        model.center();// 将模型的中心移到原点
        rootNode.attachChild(model2);

    }
}
