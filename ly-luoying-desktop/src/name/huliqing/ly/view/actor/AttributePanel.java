/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.view.actor;

import com.jme3.font.BitmapFont;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import name.huliqing.luoying.manager.ResourceManager;
import name.huliqing.luoying.object.attribute.Attribute;
import name.huliqing.luoying.object.attribute.GroupAttribute;
import name.huliqing.luoying.object.attribute.LimitAttribute;
import name.huliqing.luoying.object.entity.Entity;
import name.huliqing.luoying.ui.ListView;
import name.huliqing.luoying.ui.Row;
import name.huliqing.luoying.ui.Text;

/**
 * 角色属性面板 
 * @author huliqing
 */
public class AttributePanel extends ListView<Attribute> implements ActorPanel {
    
    // 临时性的用于过滤不想显示的属性名称，后续需要添加到xml配置中去。
    private final List<String> filterAttributeNames;
    
    private Entity actor;
    
    public AttributePanel(float width, float height) {
        super(width, height);
        
        filterAttributeNames = new ArrayList<String>(1);
        filterAttributeNames.add("attributeWeaponSlots");
    }
    
    /**
     * 设置是否显示面板
     * @param visible 
     */
    @Override
    public void setPanelVisible(boolean visible) {
        this.setVisible(visible);
    }
    
    @Override
    public void setPanelUpdate(Entity actor) {
        this.actor = actor;
        getDatas();
        super.refreshPageData();
    }
    
    @Override
    protected Row<Attribute> createEmptyRow() {
        return new AttributeRow(this);
    }
    
    @Override
    protected boolean filter(Attribute data) {
        if (data instanceof GroupAttribute) {
            return true;
        }
        if (filterAttributeNames.contains(data.getName())) {
            return true;
        }
        return super.filter(data); 
    }

    @Override
    public List<Attribute> getDatas() {
        if (actor != null) {
            return actor.getAttributeManager().getAttributes();
        }
        return Collections.EMPTY_LIST;
    }
    
    private class AttributeRow extends Row<Attribute> {

        private final Text label;
        private final Text value;
        
        public AttributeRow(ListView parentView) {
            super(parentView);
            this.setLayout(Layout.horizontal);
            this.label = new Text("");
            this.value = new Text("");
            this.value.setAlignment(BitmapFont.Align.Right);
            this.addView(this.label);
            this.addView(this.value);
        }

        @Override
        protected void updateViewChildren() {
            super.updateViewChildren();
            label.setWidth(width * 0.5f);
            label.setHeight(height);
            value.setWidth(width * 0.5f);
            value.setHeight(height);
        }
        
        @Override
        public void displayRow(Attribute attr) {
            // 这里使用属性"名称"来获取资源文件中的值，与默认使用id不同。
            label.setText(ResourceManager.getObjectName(attr.getName()));
            
            if (attr instanceof LimitAttribute) {
                value.setText(((LimitAttribute)attr).getValue() + "/" + ((LimitAttribute) attr).getMaxLimit());
            } else {
                value.setText(attr.getValue().toString());
            }
        }
    }
}
