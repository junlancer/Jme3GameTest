package com.junlancer.test;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;


public class ControlTest extends SimpleApplication {
    private final static String PAUSE = "Pause";
    private final static String GO = "Go";
    private final static String BACK = "Back";
    private final static String LEFT = "Left";
    private final static String RIGHT = "Right";


    private Spatial model;
    private Boolean isRunning = true;

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        showModel();


        // 检测输入设备
        System.out.printf("Mouse: %b\nKeyboard: %b\nJoystick: %b\nTouch: %b\n",
                mouseInput != null, keyInput != null, joyInput != null, touchInput != null);
        // 绑定消息和触发器
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_SPACE),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping(GO, new KeyTrigger(KeyInput.KEY_NUMPAD8));
        inputManager.addMapping(BACK, new KeyTrigger(KeyInput.KEY_NUMPAD2));
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_NUMPAD6));
        //绑定消息和监听器
        inputManager.addListener(new ActionListener() {
            @Override
            public void onAction(String s, boolean b, float v) {
                if (s.equals(PAUSE) && b) {
                    isRunning = !isRunning;
                }
            }
        }, PAUSE);
        inputManager.addListener(new AnalogListener() {
            @Override
            public void onAnalog(String name, float value, float tpf) {
                if (name.equals(GO)&&isRunning) {
                    model.move((new Vector3f(0, 4*value,0)) );
                } else if (name.equals(BACK)&&isRunning) {
                    model.move((new Vector3f(0, -4*value,0)) );
                } else if (name.equals(LEFT)&&isRunning) {
                    model.move((new Vector3f(-4*value, 0,0)) );
                } else if (name.equals(RIGHT)&&isRunning) {
                    model.move((new Vector3f(4*value, 0,0)) );
                } else {
                    System.out.println("!!Paused");
                }
            }
        }, GO, BACK, LEFT, RIGHT);

    }

    //加载模型
    private void showModel() {
        new Thread(){
            @Override
            public void run() {
                model = assetManager.loadModel("Models/plane/plane.obj");
                Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
                mat.setColor("Color", ColorRGBA.Gray);
                model.setMaterial(mat);
                enqueue(new Runnable() {
                    public void run() {
                        rootNode.attachChild(model);
                    }
                });
            }
        }.start();

    }

    public static void main(String[] args) {
        ControlTest app = new ControlTest();
        app.start();
    }
}
