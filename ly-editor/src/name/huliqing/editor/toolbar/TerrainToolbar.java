/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.huliqing.editor.toolbar;

import com.jme3.input.MouseInput;
import com.jme3.terrain.Terrain;
import name.huliqing.editor.constants.AssetConstants;
import name.huliqing.editor.constants.ResConstants;
import name.huliqing.editor.edit.Mode;
import name.huliqing.editor.edit.SimpleJmeEdit;
import name.huliqing.editor.edit.controls.ControlTile;
import name.huliqing.editor.edit.controls.entity.EntityControlTile;
import name.huliqing.editor.manager.Manager;
import name.huliqing.editor.tools.NumberValueTool;
import name.huliqing.editor.tools.Tool;
import name.huliqing.editor.tools.terrain.LevelTool;
import name.huliqing.editor.tools.terrain.LowerTool;
import name.huliqing.editor.tools.terrain.PaintTool;
import name.huliqing.editor.tools.terrain.RaiseTool;
import name.huliqing.editor.tools.terrain.RoughTool;
import name.huliqing.editor.tools.terrain.SlopeTool;
import name.huliqing.editor.tools.terrain.SmoothTool;
import name.huliqing.editor.tools.terrain.TexLayerTool;
import name.huliqing.editor.edit.SimpleEditListener;
import name.huliqing.editor.tools.terrain.EraseTool;

/**
 * 地形编辑工具栏
 * @author huliqing
 */
public class TerrainToolbar extends EditToolbar<SimpleJmeEdit> implements SimpleEditListener {

//    private static final Logger LOG = Logger.getLogger(TerrainToolbar.class.getName());
    
    private NumberValueTool radiusTool;
    private NumberValueTool weightTool;
    private RaiseTool raiseTool;
    private LowerTool lowerTool;
    private RoughTool roughTool;
    private SmoothTool smoothTool;
    private LevelTool levelTool;
    private SlopeTool slopeTool;
    private PaintTool paintTool;
    private EraseTool eraseTool;
    private TexLayerTool texLayerTool;
    
    public TerrainToolbar(SimpleJmeEdit edit) {
        super(edit);
    }
    
    @Override
    public String getName() {
        return "Terrain Toolbar";
    }
    
    @Override
    public void initialize() {
        super.initialize();
        radiusTool = new NumberValueTool(Manager.getRes(ResConstants.TOOL_TERRAIN_RADIUS), Manager.getRes(ResConstants.TOOL_TERRAIN_RADIUS_TIP), null);
        weightTool = new NumberValueTool(Manager.getRes(ResConstants.TOOL_TERRAIN_WEIGHT), Manager.getRes(ResConstants.TOOL_TERRAIN_WEIGHT_TIP), null);
        raiseTool = new RaiseTool(Manager.getRes(ResConstants.TOOL_TERRAIN_RAISE), Manager.getRes(ResConstants.TOOL_TERRAIN_RAISE_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_RAISE);
        lowerTool = new LowerTool(Manager.getRes(ResConstants.TOOL_TERRAIN_LOWER), Manager.getRes(ResConstants.TOOL_TERRAIN_LOWER_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_LOWER);
        roughTool = new RoughTool(Manager.getRes(ResConstants.TOOL_TERRAIN_ROUGH), Manager.getRes(ResConstants.TOOL_TERRAIN_ROUGH_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_ROUGH);
        smoothTool = new SmoothTool(Manager.getRes(ResConstants.TOOL_TERRAIN_SMOOTH), Manager.getRes(ResConstants.TOOL_TERRAIN_SMOOTH_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_SMOOTH);
        levelTool = new LevelTool(Manager.getRes(ResConstants.TOOL_TERRAIN_LEVEL), Manager.getRes(ResConstants.TOOL_TERRAIN_LEVEL_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_LEVEL); 
        slopeTool = new SlopeTool(Manager.getRes(ResConstants.TOOL_TERRAIN_SLOPE), Manager.getRes(ResConstants.TOOL_TERRAIN_SLOPE_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_SLOPE);
        paintTool = new PaintTool(Manager.getRes(ResConstants.TOOL_TERRAIN_PAINT), Manager.getRes(ResConstants.TOOL_TERRAIN_PAINT_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_PAINT); 
        eraseTool = new EraseTool(Manager.getRes(ResConstants.TOOL_TERRAIN_ERASE), Manager.getRes(ResConstants.TOOL_TERRAIN_ERASE_TIP)
                , AssetConstants.INTERFACE_TOOL_TERRAIN_ERASE);
        texLayerTool = new TexLayerTool(Manager.getRes(ResConstants.TOOL_TERRAIN_TEXLAYER), Manager.getRes(ResConstants.TOOL_TERRAIN_TEXLAYER_TIP), null); 

        radiusTool.setMinValue(-10).setValue(1);
        weightTool.setMinValue(-10).setValue(1);
        raiseTool.bindRaiseEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        lowerTool.bindRaiseEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        roughTool.bindRoughEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        smoothTool.bindSmoothEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        levelTool.bindLevelEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        slopeTool.bindSlopeEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        paintTool.bindPaintEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        eraseTool.bindEraseEvent().bindButton(MouseInput.BUTTON_LEFT, true);
        
        Tool[] conflicts = new Tool[]{raiseTool, lowerTool, roughTool, smoothTool, levelTool, slopeTool, paintTool, eraseTool};
        addToggleMapping(new ToggleMappingEvent(-1, raiseTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, lowerTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, roughTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, smoothTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, levelTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, slopeTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, paintTool, conflicts));
        addToggleMapping(new ToggleMappingEvent(-1, eraseTool, conflicts));
        
        add(radiusTool);
        add(weightTool);
        add(raiseTool);
        add(lowerTool);
        add(roughTool);
        add(smoothTool);
        add(levelTool);
        add(slopeTool);
        add(paintTool);
        add(eraseTool);
        add(texLayerTool);
        
        setEnabled(radiusTool, true);
        setEnabled(weightTool, true);
        setEnabled(raiseTool, true);
        setEnabled(lowerTool, true);
        setEnabled(roughTool, true);
        setEnabled(smoothTool, true);
        setEnabled(levelTool, true);
        setEnabled(slopeTool, true);
        setEnabled(paintTool, true);
        setEnabled(eraseTool, true);
        setEnabled(texLayerTool, true);
        
        setActivated(radiusTool, true);
        setActivated(weightTool, true);
        setActivated(texLayerTool, true);
        
        setEnabled(false);
        edit.addListener(this);
    }

    @Override
    public void cleanup() {
        edit.removeListener(this);
        removeAll();
        clearToggleMappings();
        super.cleanup(); 
    }

    public RaiseTool getRaiseTool() {
        return raiseTool;
    }

    public LowerTool getLowerTool() {
        return lowerTool;
    }

    public NumberValueTool getRadiusTool() {
        return radiusTool;
    }

    public NumberValueTool getWeightTool() {
        return weightTool;
    }

    public RoughTool getRoughTool() {
        return roughTool;
    }

    public SmoothTool getSmoothTool() {
        return smoothTool;
    }

    public LevelTool getLevelTool() {
        return levelTool;
    }

    public SlopeTool getSlopeTool() {
        return slopeTool;
    }
    
    public PaintTool getPaintTool() {
        return paintTool;
    }
    
    public TexLayerTool getTexLayerTool() {
        return texLayerTool;
    }

    @Override
    public void onModeChanged(Mode mode) {
        // ignore
    }
    
    @Override
    public void onSelected(ControlTile selectObj) {
        if (selectObj instanceof EntityControlTile) {
            if (((EntityControlTile) selectObj).getTarget().getSpatial() instanceof Terrain) {
                setEnabled(true);
                return;
            }
        }
        setEnabled(false);
    }
    
}
