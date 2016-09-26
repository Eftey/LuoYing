/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.core.object.drop;

import name.huliqing.core.data.DropData;
import name.huliqing.core.object.actor.Actor;
import name.huliqing.core.xml.DataProcessor;

/**
 *
 * @author huliqing
 */
public interface Drop extends DataProcessor<DropData> {
    
    /**
     * 处理掉落物品，物品从source掉落到target身上。如果有任何物品成功掉落则返回true,否则返回false.
     * @param source 掉落源
     * @param target 接受掉落物品的角色
     * @return 
     */
    boolean doDrop(Actor source, Actor target);
}