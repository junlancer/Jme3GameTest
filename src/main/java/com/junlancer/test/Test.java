package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.scene.debug.Arrow;
import com.jme3.scene.shape.Sphere;
import com.jme3.system.AppSettings;
import com.jme3.util.BufferUtils;

public class Test extends SimpleApplication {
    private Geometry spatial0;
    private Geometry spatial1;
    //顶点坐标
    private float[] vertex0 = {
            0f, 0f, 0f, // 零
            2f, 0f, 0f, // 壹
            1f, 1f, 0f // 贰
    };
    //顶点索引
    private int[] indices0 = {
            0, 1, 2
    };

    //顶点坐标
    private float[] vertex1 = {
            0f, 0f, 0f, // 0
            2f, 0f, 0f, // 1
            1f, 1f, 0f, // 2
            0.5f, 0.5f, 0.7f //3
    };
    //顶点索引
    private int[] indices1 = {
            0, 1, 2,
            0, 3, 2,
            2, 3, 1,
            0, 1, 3
    };

    private Geometry prepareModelByMesh(float[] vertex, int[] indices) {
        Geometry spatial;
        //创建网格
        Mesh mesh = new Mesh();
        //保存顶点位置和索引
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertex));
        mesh.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indices));
        //更新边界
        mesh.updateBound();
        //设为静态模型
        mesh.setStatic();
        //创建材质
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //设置附加渲染状态，线框
        mat.getAdditionalRenderState().setWireframe(true);
        //使用网格和材质创建物体
        spatial = new Geometry("triangle");
        spatial.setMesh(mesh);
        spatial.setMaterial(mat);
        return spatial;

    }

    @Override
    public void simpleInitApp() {
        cam.setLocation(new Vector3f(4.893791f, 4.5420675f, 9.626116f));
        cam.setRotation(new Quaternion(-0.031222044f, 0.9664778f, -0.14307737f, -0.21089031f));

        // 创建球体
        Geometry geom = new Geometry("球体", new Sphere(10, 16, 2));

        // 创建材质，并显示网格线
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //mat.getAdditionalRenderState().setWireframe(true);
        geom.setMaterial(mat);

        // 将物体添加到场景图中
        spatial0 = prepareModelByMesh(vertex1, indices1);
       // rootNode.attachChild(spatial0);
        rootNode.attachChild(geom);
        // 创建X、Y、Z方向的箭头，作为参考坐标系。
        createArrow(new Vector3f(5, 0, 0), ColorRGBA.Green);
        createArrow(new Vector3f(0, 5, 0), ColorRGBA.Red);
        createArrow(new Vector3f(0, 0, 5), ColorRGBA.Blue);

    }

    @Override
    public void simpleUpdate(float tpf) {
        super.simpleUpdate(tpf);
        // 旋转速度：每秒360°
        float speed = FastMath.TWO_PI;
        // 让方块匀速旋转
        //spatial0.rotate(0, 0, tpf * speed);

    }

    public static void main(String[] s) {
        // 配置参数
        AppSettings settings = new AppSettings(true);
        settings.setTitle(Test.class.getName());// 标题
        settings.setResolution(640, 480);// 分辨率

        // 启动jME3程序
        Test app = new Test();
        app.setSettings(settings);// 应用参数
        app.setShowSettings(false);
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
