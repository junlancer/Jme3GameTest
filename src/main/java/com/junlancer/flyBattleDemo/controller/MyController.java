package com.junlancer.flyBattleDemo.controller;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.junlancer.flyBattleDemo.Main;
import com.junlancer.flyBattleDemo.node.plane.MyAPlane;

public class MyController implements AnalogListener {
    private final static String PAUSE = "Pause";
    private final static String AHEAD = "AHEAD";
    private final static String BACK = "BACK";
    private final static String LEFT = "LEFT";
    private final static String RIGHT = "RIGHT";
    private final static String FIGHT = "FIGHT";
    private InputManager inputManager = Main.inputManager;
    private MyAPlane myAPlane;

    @Override
    public void onAnalog(String s, float v, float v1) {
        System.out.println(myAPlane.getLocalTranslation());
        if (s.equals(AHEAD)) {
            myAPlane.move((new Vector3f(0, 12 * v, 0)));
        } else if (s.equals(BACK)) {
            myAPlane.move((new Vector3f(0, -12 * v, 0)));
        } else if (s.equals(LEFT)) {
            myAPlane.move((new Vector3f(-12 * v, 0, 0)));
        } else if (s.equals(RIGHT)) {
            myAPlane.move((new Vector3f(12 * v, 0, 0)));
        } else if (s.equals(FIGHT)) {
            myAPlane.fire();
        }
    }

    public MyController(MyAPlane myAPlane) {
        this.myAPlane = myAPlane;
        // 绑定消息和触发器
        inputManager.addMapping(PAUSE, new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping(AHEAD, new KeyTrigger(KeyInput.KEY_NUMPAD8));
        inputManager.addMapping(BACK, new KeyTrigger(KeyInput.KEY_NUMPAD5));
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_NUMPAD6));
        inputManager.addMapping(FIGHT, new KeyTrigger(KeyInput.KEY_F));
        //绑定消息和监听器
        inputManager.addListener(this, AHEAD, BACK, LEFT, RIGHT,FIGHT);
    }
}
