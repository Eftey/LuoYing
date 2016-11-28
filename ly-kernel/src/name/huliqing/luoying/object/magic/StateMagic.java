/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.luoying.object.magic;

import java.util.ArrayList;
import java.util.List;
import name.huliqing.luoying.data.MagicData;
import name.huliqing.luoying.data.StateData;
import name.huliqing.luoying.object.Loader;
import name.huliqing.luoying.object.actoranim.ActorAnim;
import name.huliqing.luoying.object.entity.Entity;
import name.huliqing.luoying.utils.ConvertUtils;
import name.huliqing.luoying.utils.MathUtils;

/**
 * 状态类的魔法,这类魔法会给进入魔法范围内的角色添加一些状态。
 * @deprecated 可能以后不再使用这类魔法
 * @author huliqing
 */
public class StateMagic extends AbstractMagic {
    
    // 格式:stateId|timePoint,stateId|timePoint,stateId|timePoint...
    private List<StateWrap> states;
    
    // 角色动画,格式:actorAnimId|timePoint,actorAnimId|timePoint,actorAnimId|timePoint...
    private List<ActorAnimWrap> actorAnims;

    @Override
    public void setData(MagicData data) {
        super.setData(data);
        // 状态格式:stateId|timePoint,stateId|timePoint,stateId|timePoint...
        String[] tempStates = data.getAsArray("states");
        if (tempStates != null) {
            states = new ArrayList<StateWrap>(tempStates.length);
            for (int i = 0; i < tempStates.length; i++) {
                String[] stateArr = tempStates[i].split("\\|");
                StateWrap sw = new StateWrap();
                sw.stateId = stateArr[0];
                if (stateArr.length >= 2) {
                    sw.timePoint = MathUtils.clamp(ConvertUtils.toFloat(stateArr[1], 0f), 0, 1);
                }
                states.add(sw);
            }
        }
        
        // 角色动画,格式:actorAnimId|timePoint,actorAnimId|timePoint,actorAnimId|timePoint...
        String[] tempActorAnims = data.getAsArray("actorAnims");
        if (tempActorAnims != null) {
            actorAnims = new ArrayList<ActorAnimWrap>(tempActorAnims.length);
            for (int i = 0; i < tempActorAnims.length; i++) {
                String[] actorAnimArr = tempActorAnims[i].split("\\|");
                ActorAnimWrap aaw = new ActorAnimWrap();
                aaw.actorAnimId = actorAnimArr[0];
                if (actorAnimArr.length >= 2) {
                    aaw.timePoint = MathUtils.clamp(ConvertUtils.toFloat(actorAnimArr[1], 0f), 0, 1);
                }
                actorAnims.add(aaw);
            }
        }
    }

    @Override
    public void updateMagicLogic(float tpf) {
        super.updateMagicLogic(tpf); 
        
        float inter = timeUsed / useTime;
        
        // update states
        doUpdateStates(inter);
        
        // update actorAnims
        doUpdateActorAnims(tpf, inter);
    }
    
    @Override
    public void cleanup() {
        if (states != null) {
            for (StateWrap sw : states) {
                sw.cleanup();
            }
        }
        if (actorAnims != null) {
            for (ActorAnimWrap aaw : actorAnims) {
                aaw.cleanup();
            }
        }
        super.cleanup(); 
    }
    
    protected void doUpdateStates(float inter) {
        if (states != null) {
            for (int i = 0; i < states.size(); i++) {
                states.get(i).update(inter);
            }
        }
    }
    
    protected void doUpdateActorAnims(float tpf, float inter) {
        if (actorAnims != null) {
            for (int i = 0; i < actorAnims.size(); i++) {
                actorAnims.get(i).update(tpf, inter);
            }
        }
    }
    
    // 状态更新控制
    protected class StateWrap {
        String stateId;
        // 效果的开始播放时间点
        float timePoint;
        // 标记效果是否已经开始
        boolean started;
        
        void update(float interpolation) {
            if (started) return;
            if (interpolation >= timePoint) {
                if (targets != null) {
                    if (source != null) {
                        hitCheckEl.setSource(source.getAttributeManager());
                    }
                    for (Entity target : targets) {
                        if (hitCheckEl.setTarget(target.getAttributeManager()).getValue()) {
                            StateData od = Loader.loadData(stateId);
                            target.addObjectData(od, 1);
                        }
                    }
                }
                started = true;
            }
        }
        
        void cleanup() {
            started = false;
        }
    }
    
    public class ActorAnimWrap {
        // 原始的时间开始点和结束点
        String actorAnimId;
        float timePoint;
        boolean started;
        ActorAnim actorAnim;
        
        void update(float tpf, float interpolation) {
            if (started) {
                if (actorAnim != null) {
                    actorAnim.update(tpf);
                }
                return;
            }
            if (interpolation >= timePoint) {
                if (targets != null) {
                    hitCheckEl.setSource(source.getAttributeManager());
                    for (Entity target : targets) {
                        if (hitCheckEl.setTarget(target.getAttributeManager()).getValue()) {
                            actorAnim = Loader.load(actorAnimId);
                            actorAnim.setTarget(target);
                            actorAnim.start();
                        }
                    }
                }
                started = true;
            }
        }
      
        void cleanup() {
            if (actorAnim != null) {
                actorAnim.cleanup();
                actorAnim = null;
            }
            started = false;
        }
    }
}
