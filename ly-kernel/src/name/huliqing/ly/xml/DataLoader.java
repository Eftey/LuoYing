/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.xml;


/**
 * 载入数据接口
 * @author huliqing
 * @param <T>
 */
public interface DataLoader<T extends ProtoData> { 
    
    /**
     * 载入数据，从proto中载入指定数据到store中。
     * @param proto
     * @param data 
     */
    void load(Proto proto, T data);
    
}