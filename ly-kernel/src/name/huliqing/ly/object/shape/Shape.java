/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.object.shape;

import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import name.huliqing.ly.data.ShapeData;
import name.huliqing.ly.xml.DataProcessor;

/**
 * @author huliqing
 * @param <T>
 */ 
public interface Shape<T extends ShapeData> extends DataProcessor<T> {
    
    /**
     * 获取网格
     * @return 
     */
    Mesh getMesh();
    
    Geometry getGeometry();
}
