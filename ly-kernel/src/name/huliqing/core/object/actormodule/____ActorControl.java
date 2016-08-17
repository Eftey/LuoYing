///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package name.huliqing.core.object.actormodule;
//
//import com.jme3.renderer.RenderManager;
//import com.jme3.renderer.ViewPort;
//import com.jme3.scene.control.AbstractControl;
//import name.huliqing.core.data.ModuleData;
//import name.huliqing.core.object.actor.Actor;
//import name.huliqing.core.xml.DataProcessor;
//
///**
// *
// * @author huliqing
// * @param <T>
// */
//public abstract class ActorControl<T extends ModuleData> extends AbstractControl implements DataProcessor<T> {
//    
//    protected T data;
//    protected boolean initialized;
//
//    @Override
//    public void setData(T data) {
//        this.data = data;
//    }
//
//    @Override
//    public T getData() {
//        return data;
//    }
//    
//    public void initialize(Actor actor) {
//        initialized = true;
//    }
//
//    public boolean isInitialized() {
//        return initialized;
//    }
//
//    public void cleanup() {
//        initialized = false;
//    }
//
//    @Override
//    protected final void controlUpdate(float tpf) {
//        if (!initialized) 
//            return;
//        
//        actorUpdate(tpf);
//    }
//
//    @Override
//    protected final void controlRender(RenderManager rm, ViewPort vp) {
//        if (!initialized)
//            return;
//        
//        actorRender(rm, vp);
//    }
//
//    /**
//     * 实现逻辑
//     * @param tpf 
//     */
//    public abstract void actorUpdate(float tpf);
//
//    public abstract void actorRender(RenderManager rm, ViewPort vp);
//  
//}
