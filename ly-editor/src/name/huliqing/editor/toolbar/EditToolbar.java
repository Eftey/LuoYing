/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.editor.toolbar;

import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import name.huliqing.editor.events.Event;
import name.huliqing.editor.events.EventListener;
import name.huliqing.editor.events.JmeEvent;
import name.huliqing.editor.forms.EditForm;
import name.huliqing.editor.tools.CameraTool2;
import name.huliqing.editor.tools.GridTool;
import name.huliqing.editor.tools.ModeTool;
import name.huliqing.editor.tools.MoveTool;
import name.huliqing.editor.tools.PickTool;
import name.huliqing.editor.tools.RotationTool;
import name.huliqing.editor.tools.ScaleTool;
import name.huliqing.editor.tools.Tool;
import name.huliqing.editor.tools.UndoRedoTool;

/**
 * @author huliqing
 */
public class EditToolbar extends AbstractToolbar<EditForm> implements EventListener {

    private static final Logger LOG = Logger.getLogger(EditToolbar.class.getName());
    private final Map<Tool, ToggleMappingEvent> toggleMapping = new HashMap<Tool, ToggleMappingEvent>();
    
    private UndoRedoTool undoRedoTool;
    
//    private CameraTool cameraTool;
    private CameraTool2 cameraTool2;
    
    private ModeTool modeTool;
    private GridTool gridTool;
    private PickTool pickTool;
    private MoveTool moveTool;
    private RotationTool rotationTool;
    private ScaleTool scaleTool;
    
    @Override
    public void initialize() {
        super.initialize();
        
        undoRedoTool = new UndoRedoTool("undoRedoTool");
//        cameraTool = new CameraTool("CameraTool");
        modeTool = new ModeTool("modeTool");
        gridTool = new GridTool("gridTool");
        pickTool = new PickTool("pickTool");
        moveTool = new MoveTool("moveTool");
        rotationTool = new RotationTool("rotationTool");
        scaleTool = new ScaleTool("scaleTool");
        
        undoRedoTool.bindUndoEvent().bindKey(KeyInput.KEY_LCONTROL, true).bindKey(KeyInput.KEY_Z, false);
        undoRedoTool.bindRedoEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_Z, false);
        
        cameraTool2 = new CameraTool2("CameraTool");
        cameraTool2.bindDragEvent().bindButton(MouseInput.BUTTON_MIDDLE, true).bindKey(KeyInput.KEY_LSHIFT, true);
        cameraTool2.bindToggleRotateEvent().bindButton(MouseInput.BUTTON_MIDDLE, true);
        cameraTool2.bindZoomInEvent().bindAxis(MouseInput.AXIS_WHEEL, false, false);
        cameraTool2.bindZoomOutEvent().bindAxis(MouseInput.AXIS_WHEEL, true, false);
        cameraTool2.bindRechaseEvent().bindKey(KeyInput.KEY_DECIMAL, true);
        cameraTool2.bindResetEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_C, true);
//        cameraTool2.bindViewBackEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_C, true);
//        cameraTool2.bindViewBottomEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_C, true);
        cameraTool2.bindViewFrontEvent().bindKey(KeyInput.KEY_NUMPAD1, true);
//        cameraTool2.bindViewLeftEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_C, true);
//        cameraTool2.bindViewRightEvent().bindKey(KeyInput.KEY_LSHIFT, true).bindKey(KeyInput.KEY_C, true);
        cameraTool2.bindViewTopEvent().bindKey(KeyInput.KEY_NUMPAD7, true);
        cameraTool2.bindViewOrthoPerspEvent().bindKey(KeyInput.KEY_NUMPAD5, true);

        modeTool.bindModeEvent().bindKey(KeyInput.KEY_TAB, false);
        pickTool.bindPickEvent().bindButton(MouseInput.BUTTON_RIGHT, true);
        moveTool.bindMoveEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        
        scaleTool.bindScaleEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        scaleTool.bindFullScaleStartEvent().bindKey(KeyInput.KEY_S, false);
        scaleTool.bindFullScaleApplyEvent().bindButton(MouseInput.BUTTON_LEFT, false);
        scaleTool.bindFullScaleCancelEvent().bindButton(MouseInput.BUTTON_RIGHT, false);
        
        rotationTool.bindRotationEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        
        add(undoRedoTool);
//        add(cameraTool);
        add(cameraTool2);
        add(modeTool);
        add(gridTool);
        add(pickTool);
        add(moveTool);
        add(rotationTool);
        add(scaleTool);
        
        Tool[] conflicts = new Tool[]{moveTool, scaleTool, rotationTool};
        addToggleMapping(KeyInput.KEY_G, new ToggleMappingEvent(moveTool, conflicts));
        addToggleMapping(KeyInput.KEY_S, new ToggleMappingEvent(scaleTool, conflicts));
        addToggleMapping(KeyInput.KEY_R, new ToggleMappingEvent(rotationTool, conflicts));
        
        setActivated(undoRedoTool, true);
//        setActivated(cameraTool, true); 
        setActivated(cameraTool2, true); 
        setActivated(modeTool, true);
        setActivated(gridTool, true);
        setActivated(pickTool, true);
        setActivated(moveTool, true);
        
        setEnabled(rotationTool, true);
        setEnabled(scaleTool, true);
    }
    
    @Override
    public void cleanup() {
        toggleMapping.values().stream().filter(t -> t.isInitialized()).forEach(t -> {t.cleanup();});
        toggleMapping.clear();
        super.cleanup(); 
    }
    
    private void addToggleMapping(int keyInput, ToggleMappingEvent tme) {
        tme.bindKey(keyInput, true);
        tme.addListener(this);
        tme.initialize();
        toggleMapping.put(tme.tool, tme);
    }
    
    @Override
    public void onEvent(Event e) {
        if (!e.isMatch()) 
            return;
        ToggleMappingEvent te = (ToggleMappingEvent) e;
        setActivated(te.tool, true);
    }
    
    @Override
    public <T extends Toolbar> T setActivated(Tool tool, boolean activated) {
        // 取消冲突的工具
        ToggleMappingEvent tme = toggleMapping.get(tool);
        if (activated && tme != null && tme.conflicts != null) {
            for (Tool conflict : tme.conflicts) {
                if (conflict == tool) {
                    continue; // 不能关闭自身, 很重要: 兼容JFX界面的时候避免递归错误
                }
                setActivated(conflict, false);
            }
        }
        super.setActivated(tool, activated);
        return (T)this;
    }
    
    private class ToggleMappingEvent extends JmeEvent {
        public final Tool tool;
        // 冲突工具列表，当tool打开时，这些工具要关闭
        public Tool[] conflicts;
        
        public ToggleMappingEvent(Tool tool, Tool[] conflicts) {
            super("toggleEvent" + tool.getName());
            this.tool = tool;
            this.conflicts = conflicts;
        }
    }
    
    
    
}