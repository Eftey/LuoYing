/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.luoying.object.item;

import name.huliqing.luoying.object.entity.Entity;

/**
 * 没有任何属性的物品。
 * @author huliqing
 */
public class SimpleItem extends AbstractItem {

    @Override
    protected void doUse(Entity actor) {
        // do nothing.
    }
    
}
