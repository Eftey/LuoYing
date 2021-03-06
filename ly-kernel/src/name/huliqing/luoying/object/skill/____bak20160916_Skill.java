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
//package name.huliqing.core.object.skill;
//
//import name.huliqing.core.constants.SkillConstants;
//import name.huliqing.core.data.SkillData;
//import name.huliqing.core.object.actor.Actor;
//import name.huliqing.core.enums.SkillType;
//import name.huliqing.core.object.module.SkillModule;
//import name.huliqing.core.xml.DataProcessor;
//
///**
// * 接能接口
// * @author huliqing
// * @param <T>
// */
//public interface Skill<T extends SkillData> extends DataProcessor<T>{
//
//    @Override
//    public void setData(T data);
//
//    @Override
//    public T getData();
//    
//    /**
//     * 开始执行技能
//     */
//    void start();
//    
//    /**
//     * 更新技能逻辑
//     * @param tpf 
//     */
//    void update(float tpf);
//    
//    /**
//     * 判断技能是否正常结束或未启动
//     * @return 
//     */
//    boolean isEnd();
//    
//    /**
//     * 清理技能产生的数据以释放资源,一般只处理内部数据，不要再去调用service之类
//     * 以避免循环调用。
//     */
//    void cleanup();
//    
//    /**
//     * 获取技能类型
//     * @return 
//     */
//    SkillType getSkillType();
//    
//    /**
//     * 获取发起技能的角色，如果没有，则返回null
//     * @return 
//     */
//    Actor getActor();
//    
//    /**
//     * 设置发起技能的角色。
//     * @param character 
//     */
//    void setActor(Actor character);
//   
//    // remove20160818
////    /**
////     * @deprecated use getData instead
////     * @return 
////     */
////    SkillData getSkillData();
//   
//    // remove20160813
////    /**
////     * 设置动画控制器
////     * @param animChannelProcessor 
////     */
////    void setAnimChannelProcessor(ChannelControl animChannelProcessor);
//    
//    void setSkillControl(SkillModule skillControl);
//    
//    /**
//     * 重新修复被其它技能重置的动画
//     * add20151212
//     */
//    void restoreAnimation();
//    
//    /**
//     * 判断当前技能是否可以执行,该方法返回一个状态码，来判断当前技能是否可以
//     * 执行，比如某些情况下一个技能是不能对角色的当前指定目标执行的。比如：加
//     * 血技能应该不可以对敌军施行，或者攻击技能就不应该对友军施行之类
//     * @return 
//     * @see SkillConstants
//     */
//    int canPlay();
//
//    /**
//     * 获取技能的执行速度,技能的执行速度受角色属性的影响，当技能指定了speedAttribute
//     * 后，角色的这个属性值将影响技能的执行速度。如果技能没有指定这个属性或
//     * 者角色没有这个属性，则这个方法应该返回1.0,即原始速度。
//     * @return 返回的最小值为0.0001f，为避免除0错误，速度不能小于或等于0
//     */
//    float getSpeed();
//    
//    /**
//     * 获取技能的CutTimeEndRate,这个值是对技能执行时间的剪裁，即对技能的结束阶段
//     * 的时间进行剪裁，这个值受角色属性影响，并且不会大于CutTimeEndMax.
//     * 如果技能没有指定影响该值的角色属性，或者角色没有指定的属性值，则这个值应
//     * 返回0.<br >
//     * 注：这个值返回的是一个比率，取值为[0.0,1.0]之间，即表示要剪裁掉的技能总时间
//     * 的比率。例如：当返回值为0.5时，即表示技能的总执行时间要剪裁掉一半（时间的后半部分）
//     * @return 
//     */
//    float getCutTimeEndRate();
//
//    /**
//     * 获取技能的实际执行时间,技能的实际执行时间受：技能总时间、技能执行速度、
//     * 技能的剪裁时间等影响
//     * @return 
//     */
//    float getTrueUseTime();
//}
//
