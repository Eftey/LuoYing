/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.loader;

import name.huliqing.ly.data.AttributeData;
import name.huliqing.ly.xml.Proto;
import name.huliqing.ly.xml.DataLoader;

/**
 *
 * @author huliqing
 */
public class AttributeDataLoader implements DataLoader<AttributeData> {

    @Override
    public void load(Proto proto, AttributeData store) {
        
        // remove20160826
//        store.setEl(proto.getAsString("el")); 

    }
    
}