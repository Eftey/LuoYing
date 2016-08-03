/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.core.view.system;

import com.jme3.font.BitmapFont;
import name.huliqing.core.manager.ResourceManager;
import name.huliqing.core.ui.LinearLayout;
import name.huliqing.core.ui.LinearLayout.Layout;
import name.huliqing.core.ui.Text;
import name.huliqing.core.ui.UIFactory;
import name.huliqing.core.ui.UI;
import name.huliqing.core.ui.Window;
import name.huliqing.core.ui.tiles.Tab;
import name.huliqing.core.view.system.SystemSoundPanel;
import name.huliqing.core.view.system.SystemUIPanel;

/**
 *
 * @author huliqing
 */
public class SystemMainPanel extends Window {
//    private final static Logger logger = Logger.getLogger(SystemMainPanel.class.getName());
    
    private Tab tab;
    
    // ==== tab
    // 声音设置
    private TabButton tabSound;
    // 快捷方式设置
    private TabButton tabUI;
    // 图形设置
    private TabButton tabPerformance;
    // Other:暂不开放，debug功能会导致关卡列表在debug模式下被自动打开。
    private TabButton tabOther;
    
    // ==== tab panel
    private SystemSoundPanel soundPanel;
    private SystemUIPanel shortcutPanel;
    private SystemOtherPanel otherPanel;
    private SystemPerformancePanel performancePanel;
    
    // ==== other
    // 当前激活的tab
    private int index = 0;
    // 每个tab的默认可显示行数
    private int globalSize = 5;

    public SystemMainPanel(float width, float height) {
        super(width, height);
        setTitle(ResourceManager.get("system.main"));
        setLayout(Layout.horizontal);
        
        init();
    }
    
    private void init() {
        // ==== Panels 
        soundPanel = new SystemSoundPanel(getContentWidth(), getContentHeight());
        soundPanel.setVisible(false);
        soundPanel.setPageSize(globalSize);
        
        shortcutPanel = new SystemUIPanel(getContentWidth(), getContentHeight());
        shortcutPanel.setVisible(false);
        shortcutPanel.setPageSize(globalSize);
        
        performancePanel = new SystemPerformancePanel(getContentWidth(), getContentHeight());
        performancePanel.setVisible(false);
        performancePanel.setPageSize(globalSize);
        
        // 暂不开放，debug功能会导致关卡列表在debug模式下被自动打开。
//        otherPanel = new SystemOtherPanel(getContentWidth(), getContentHeight());
//        otherPanel.setToCorner(Corner.CC);
//        otherPanel.setVisible(false);
//        otherPanel.setPageSize(globalSize);
//        bodyPanel.addView(otherPanel);
        
        // ==== tabs
        tabSound = new TabButton(ResourceManager.get("system.sound"), soundPanel);
        tabUI = new TabButton(ResourceManager.get("system.ui"), shortcutPanel);
        tabPerformance = new TabButton(ResourceManager.get("system.performance"), performancePanel);
//        tabOther = new TabButton(ResourceManager.get("system.other"), otherPanel);
        
        tab = new Tab(getContentWidth(), getContentHeight());
        tab.setLayout(Layout.horizontal);
        tab.addTab(tabSound, soundPanel);
        tab.addTab(tabUI, shortcutPanel);
        tab.addTab(tabPerformance, performancePanel);
        addView(tab);
    }
    
    @Override
    public void updateViewChildren() {
        super.updateViewChildren();
        tab.setWidth(getContentWidth());
        tab.setHeight(getContentHeight());
    }
    
    private class TabButton extends LinearLayout {
        
        private Text tabName;
        
        public TabButton(String text, UI contentPanel) {
            super();
            this.tabName = new Text(text);
            this.tabName.setAlignment(BitmapFont.Align.Center);
            this.tabName.setVerticalAlignment(BitmapFont.VAlign.Center);
            setBackground(UIFactory.getUIConfig().getBackground(), true);
            setBackgroundColor(UIFactory.getUIConfig().getBodyBgColor(), true);
            addView(tabName);
        }

        @Override
        public void updateViewChildren() {
            super.updateViewChildren();
            tabName.setWidth(width);
            tabName.setHeight(height);
        }
    }
}