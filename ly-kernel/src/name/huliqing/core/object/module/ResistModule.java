/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.core.object.module;

import java.util.List;
import name.huliqing.core.data.ResistData;
import name.huliqing.core.object.Loader;
import name.huliqing.core.object.actor.Actor;
import name.huliqing.core.object.resist.Resist;

/**
 * @author huliqing
 */
public class ResistModule extends AbstractModule {

    private Resist resist;

    @Override
    public void initialize(Actor actor) {
        super.initialize(actor);
        List<ResistData> rds = actor.getData().getObjectDatas(ResistData.class, null);
        if (rds != null && !rds.isEmpty()) {
            setResist((Resist)Loader.load(rds.get(0)));
        }
    }

    @Override
    public void cleanup() {
        resist = null;
        super.cleanup();
    }
    
    public void setResist(Resist resist) {
        if (this.resist != null) {
            actor.getData().removeObjectData(this.resist.getData());
        }
        this.resist = resist;
        this.actor.getData().addObjectData(this.resist.getData());
    }
    
    /**
     * 获取搞性设置，如果没有则返回null.
     * @return 
     */
    public Resist getResist() {
        return resist;
    }
    
}