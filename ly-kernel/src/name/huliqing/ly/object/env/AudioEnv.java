/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.object.env;

import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import name.huliqing.ly.LuoYing;
import name.huliqing.ly.data.EntityData;
import name.huliqing.ly.object.Loader;
import name.huliqing.ly.object.entity.ModelEntity;
import name.huliqing.ly.object.scene.Scene;
import name.huliqing.ly.object.sound.Sound;
import name.huliqing.ly.object.sound.SoundManager;

/**
 * @author huliqing
 */
public class AudioEnv extends ModelEntity {

    private boolean debug;
    private String soundId;
    
    // ---- inner
    private Sound sound;
    private Spatial debugNode;
    private Spatial debugInnerNode;

    @Override
    public void setData(EntityData data) {
        super.setData(data);
        debug = data.getAsBoolean("debug", false);
        soundId = data.getAsString("sound");
    }
    
    @Override
    public void initialize(Scene scene) {
        super.initialize(scene);
        if (debug) {
            debugNode = new Geometry("debugAudioEnvDistance", new Sphere(20, 20, sound.getData().getMaxDistance()));
            debugNode.setMaterial(createDebugMaterial(ColorRGBA.Green));
            sound.attachChild(debugNode);
            
            debugInnerNode = new Geometry("debugAudioEnvRefDistance", new Sphere(20, 20, sound.getData().getRefDistance()));
            debugInnerNode.setMaterial(createDebugMaterial(ColorRGBA.Red));
            sound.attachChild(debugInnerNode);
        }
        SoundManager.getInstance().addAndPlay(sound);
    }

    @Override
    protected Spatial loadModel() {
        sound = Loader.load(soundId);
        return sound;
    }
    
    private Material createDebugMaterial(ColorRGBA color) {
        Material mat = new Material(LuoYing.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.getAdditionalRenderState().setWireframe(true);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        mat.setColor("Color",  color);
        return mat;
    }

    @Override
    public void cleanup() {
        // 停止声音
        SoundManager.getInstance().removeAndStopDirectly(sound);
        if (debugNode != null) {
            sound.detachChild(debugNode);
            debugNode = null;
        }
        if (debugInnerNode != null) {
            sound.detachChild(debugInnerNode);
            debugInnerNode = null;
        }
        super.cleanup(); 
    }

    
}