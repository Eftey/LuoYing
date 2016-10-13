/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.ui.tiles;

import name.huliqing.ly.ui.UIFactory;
import name.huliqing.ly.ui.FrameLayout;
import name.huliqing.ly.ui.Icon;

/**
 *
 * @author huliqing
 */
public class ColumnIcon extends FrameLayout {
    
    private Icon icon;
    
    public ColumnIcon(float width, float height, String icon) {
        super(width, height);
        this.icon = new Icon(icon);
        this.addView(this.icon);
    }
    
    public void setIcon(String pic) {
        if (pic != null) {
            icon.setImage(pic);
        } else {
            icon.setImage(UIFactory.getUIConfig().getMissIcon());
        }
    }
    
    @Override
    public void updateViewChildren() {
        super.updateViewChildren();
        this.icon.setWidth(width * 0.75f);
        this.icon.setHeight(height * 0.75f);
    }

    @Override
    protected void updateViewLayout() {
        super.updateViewLayout();
        icon.setToCorner(Corner.CC);
    }
    
}