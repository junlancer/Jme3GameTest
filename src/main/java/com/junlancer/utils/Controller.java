package com.junlancer.utils;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.junlancer.FlyBattleDemo.Main;

public class Controller implements AnalogListener {
    private final static String AHEAD = "AHEAD";
    private final static String BACK = "BACK";
    private final static String LEFT = "LEFT";
    private final static String RIGHT = "RIGHT";
    private final static String FIGHT = "FIGHT";
    private Geometry geometry;

    @Override
    public void onAnalog(String s, float v, float v1) {
        System.out.println(geometry.getLocalTranslation());
        if (s.equals(AHEAD)) {
            geometry.move((new Vector3f(0, 12 * v, 0)));
        } else if (s.equals(BACK)) {
            geometry.move((new Vector3f(0, -12 * v, 0)));
        } else if (s.equals(LEFT)) {
            geometry.move((new Vector3f(-12 * v, 0, 0)));
        } else if (s.equals(RIGHT)) {
            geometry.move((new Vector3f(12 * v, 0, 0)));
        }
    }

    public Controller(Geometry geometry, InputManager inputManager) {
        this.geometry = geometry;
        // 绑定消息和触发器
        inputManager.addMapping(AHEAD, new KeyTrigger(KeyInput.KEY_NUMPAD8));
        inputManager.addMapping(BACK, new KeyTrigger(KeyInput.KEY_NUMPAD5));
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_NUMPAD6));
        //绑定消息和监听器
        inputManager.addListener(this, AHEAD, BACK, LEFT, RIGHT, FIGHT);
    }
}
