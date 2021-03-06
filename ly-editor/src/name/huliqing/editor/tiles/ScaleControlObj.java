/*
 * LuoYing is a program used to make 3D RPG game.
 * Copyright (c) 2014-2016 Huliqing <31703299@qq.com>
 * 
 * This file is part of LuoYing.
 *
 * LuoYing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LuoYing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with LuoYing.  If not, see <http://www.gnu.org/licenses/>.
 */
package name.huliqing.editor.tiles;

import com.jme3.math.Ray;

/**
 *
 * @author huliqing
 */
public class ScaleControlObj extends ControlObj {

//    private static final Logger LOG = Logger.getLogger(ScaleControlObj.class.getName());
    
    private final ScaleAxis controlAxis; 
    
    public ScaleControlObj() {
        controlAxis = new ScaleAxis();
        controlAxis.addControl(new AutoScaleControl(0.1f));
        attachChild(controlAxis);
    }
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible); 
        // 优化：当物体激活时，强制刷新一次，计算大小。
        controlAxis.getControl(AutoScaleControl.class).forceUpdate();
    }

    @Override
    public void setAxisVisible(boolean visible) {
        super.setAxisVisible(visible); 
        controlAxis.getCenter().setCullHint(visible ? CullHint.Never : CullHint.Always);
    }
    
    @Override
    public AxisNode getAxisX() {
        return controlAxis.getAxisX();
    }

    @Override
    public AxisNode getAxisY() {
        return controlAxis.getAxisY();
    }

    @Override
    public AxisNode getAxisZ() {
        return controlAxis.getAxisZ();
    }
    
    public boolean isPickCenter(Ray ray) {
        return controlAxis.getCenter().getWorldBound().intersects(ray);
    }
    
}
