/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.ly.view.system;

import com.jme3.font.BitmapFont;
import java.text.DecimalFormat;
import name.huliqing.luoying.Factory;
import name.huliqing.ly.constants.InterfaceConstants;
import name.huliqing.luoying.layer.service.ConfigService;
import name.huliqing.ly.manager.ResourceManager;
import name.huliqing.luoying.ui.FrameLayout;
import name.huliqing.luoying.ui.Icon;
import name.huliqing.luoying.ui.Text;
import name.huliqing.luoying.ui.UIFactory;
import name.huliqing.luoying.ui.UI;
import name.huliqing.luoying.ui.Window;
import name.huliqing.ly.LyConfig;

/**
 * 游戏音量大小调整界面
 * @author huliqing
 * @since 1.3
 */
public class ShortcutSizeOper extends Window {
    private DecimalFormat decimalFormat;
    private RowSimple shortcutSize;
    
    private float minSize = 0.5f;
    private float maxSize = 2f;
    private float size;
    private float step = 0.1f;
    
    private Text sizeText;
    private WrapIcon add;
    private WrapIcon subtract;
    
    public ShortcutSizeOper(float width, float height, RowSimple shortcutSize) {
        super(width, height);
        
        setTitle(ResourceManager.get("system.ui.shortcutSize")); 
        setLayout(Layout.horizontal);
        
        this.shortcutSize = shortcutSize;
        add = new WrapIcon(InterfaceConstants.UI_ADD);
        subtract = new WrapIcon(InterfaceConstants.UI_SUBTRACT);
        sizeText = new Text(size + "");
        
        addView(subtract);
        addView(sizeText);
        addView(add);
        
        this.add.addClickListener(new Listener() {
            @Override
            public void onClick(UI ui, boolean isPress) {
                if (isPress) return;
                add();
            }
        });
        
        this.subtract.addClickListener(new Listener() {
            @Override
            public void onClick(UI ui, boolean isPress) {
                if (isPress) return;
                subtract();
            }
        });
        
        this.setToCorner(Corner.CC);
        this.setCloseable(true);
        this.setDragEnabled(true);
        
        // 初始化
        this.setSize(LyConfig.getShortcutSize());
    } 
    
    @Override
    public void updateViewChildren() {
        super.updateViewChildren();
        
        float w = getContentWidth();
        float h = getContentHeight();
        
        subtract.setWidth(w * 0.4f);
        subtract.setHeight(h);
        
        sizeText.setWidth(w * 0.2f);
        sizeText.setHeight(h);
        sizeText.setAlignment(BitmapFont.Align.Center);
        sizeText.setVerticalAlignment(BitmapFont.VAlign.Center);
        
        add.setWidth(w * 0.4f);
        add.setHeight(h);
        
    }
    
    public final void setSize(float size) {
        if (size < minSize) size = minSize;
        if (size > maxSize) size = maxSize;
        this.size = size;
        this.sizeText.setText(format(this.size));
        LyConfig.setShortcutSize(this.size);
        shortcutSize.setRowDes(ResourceManager.get("system.ui.shortcutSize.des", new Object[] {sizeText.getText()}));
    }
    
    private void add() {
        size += step;
        setSize(size);
    }
    
    private void subtract() {
        size -= step;
        setSize(size);
    }
    
    private String format(Object size) {
        if (decimalFormat == null) {
            decimalFormat = new DecimalFormat("0.0");
        }
        return decimalFormat.format(size);
    }
    
    private class WrapIcon extends FrameLayout {
        private Icon icon;
        
        public WrapIcon(String file) {
            icon = new Icon(file);
           
            setBackground(UIFactory.getUIConfig().getBackground(), true);
            setBackgroundColor(UIFactory.getUIConfig().getActiveColor(), true);
            setBackgroundVisible(false);
            addView(icon);
        }

        @Override
        protected void clickEffect(boolean isPress) {
            setBackgroundVisible(isPress);
        }

        @Override
        protected void updateViewLayout() {
            super.updateViewLayout();
            icon.setToCorner(Corner.CC);
        }

        @Override
        public void onRelease() {
            super.onRelease();
            setBackgroundVisible(false);
        }
        
        
    }
}
