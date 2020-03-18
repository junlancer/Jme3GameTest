package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.*;
import com.jme3.scene.shape.Sphere;

/**
 * 场景图、节点
 */
public class HelloNode extends SimpleApplication {
    private Node node;
    private int size = 50;
    private boolean flag = false;
    private  int index;
    @Override
    public void simpleInitApp() {
        // 球体网格
        Mesh mesh = new Sphere(16, 24, 1);

        // 创建2个球体
        Geometry geomA = new Geometry("红色气球", mesh);
        geomA.setMaterial(newLightingMaterial(ColorRGBA.Red));

        Geometry geomB = new Geometry("青色气球", mesh);
        geomB.setMaterial(newLightingMaterial(ColorRGBA.Cyan));

        // 将两个球体添加到一个Node节点中
        node = new Node("原点");
        node.attachChild(geomA);
        node.attachChild(geomB);

        // 设置两个球体的相对位置
        geomA.setLocalTranslation(-1, 3, 0);
        geomB.setLocalTranslation(1.5f, 2, 0);
        node.scale(0.1f);
        // 将这个节点添加到场景图中
        rootNode.attachChild(node);

        // 添加光源
        addLight();
    }

    /**
     * 创建一个感光材质
     *
     * @param color
     * @return
     */
    private Material newLightingMaterial(ColorRGBA color) {
        // 创建材质
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");

        mat.setColor("Diffuse", color);
        mat.setColor("Ambient", color);
        mat.setColor("Specular", ColorRGBA.White);
        mat.setFloat("Shininess", 24);
        mat.setBoolean("UseMaterialColors", true);

        return mat;
    }

    /**
     * 添加光源
     */
    private void addLight() {
        // 定向光
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-1, -2, -3));

        // 环境光
        AmbientLight ambient = new AmbientLight();

        // 调整光照亮度
        ColorRGBA lightColor = new ColorRGBA();
        sun.setColor(lightColor.mult(0.8f));
        ambient.setColor(lightColor.mult(0.2f));

        // #3 将模型和光源添加到场景图中
        rootNode.addLight(sun);
        rootNode.addLight(ambient);
    }

    @Override
    public void simpleUpdate(float tpf) {
        node.rotate(0, tpf, 0);
        try {
            Thread.sleep(21);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (size < 50) {
            flag = true;
        }
        if (size > 100) {
            flag = false;
        }
        if (flag) {
            node.scale(1.01f);
            size++;
        } else {
            node.scale(0.99f);
            size--;
        }
        index++;
        if (index >=300) {
            toggleWireframe();
        }

        System.out.println(size);
    }
    private void toggleWireframe() {
        rootNode.depthFirstTraversal(new SceneGraphVisitor() {
            @Override
            public void visit(Spatial spatial) {
                spatial.removeFromParent();
            }
        });
    }


    public static void main(String[] args) {
        HelloNode app = new HelloNode();
        app.start();

    }

}
