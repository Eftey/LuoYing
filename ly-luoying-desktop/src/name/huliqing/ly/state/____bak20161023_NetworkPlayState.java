///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package name.huliqing.ly.state;
//
//import com.jme3.app.Application;
//import com.jme3.app.SimpleApplication;
//import com.jme3.app.state.AppStateManager;
//import com.jme3.math.Vector3f;
//import java.util.List;
//import name.huliqing.luoying.Factory;
//import name.huliqing.luoying.LuoYing;
//import name.huliqing.luoying.data.GameData;
//import name.huliqing.luoying.layer.service.PlayService;
//import name.huliqing.luoying.network.Network;
//import name.huliqing.luoying.network.LanGame;
//import name.huliqing.ly.view.ActorSelectView;
//import name.huliqing.ly.view.ClientsWin;
//import name.huliqing.luoying.object.anim.Anim;
//import name.huliqing.luoying.object.anim.Listener;
//import name.huliqing.luoying.object.anim.ScaleAnim;
//import name.huliqing.luoying.ui.Icon;
//import name.huliqing.luoying.ui.UI;
//import name.huliqing.luoying.ui.state.UIState;
//
///**
// * 联网游戏的基类,可用于服务端也可用于客户端。
// * @author huliqing
// */
//public abstract class NetworkPlayState extends PlayState implements LanGame {
//    private final PlayService playService = Factory.get(PlayService.class);
//    protected final Network network = Network.getInstance();
//
//    // 客户端列表界面
//    private ClientsWin clientsWin;
//    private ScaleAnim clientsWinAnim;
//    private Icon lanBtn;
//    
//    protected ActorSelectView actorPanel;
//    
//    public NetworkPlayState(Application app, GameData gameData) {
//        super(app, gameData);
//    }
//
//    @Override
//    public void initialize(AppStateManager stateManager, Application app) {
//        super.initialize(stateManager, app); 
//        // 初始化Network
//        network.initialize(app);
//        
//        // 特效管理器、子弹管理器
////        stateManager.attach(EffectManager.getInstance());
////        stateManager.attach(BulletManager.getInstance());
//    }
//
//    @Override
//    public void stateDetached(AppStateManager stateManager) {
//        super.stateDetached(stateManager);
////        stateManager.detach(EffectManager.getInstance());
////        stateManager.detach(BulletManager.getInstance());
//    }
//
//    @Override
//    public void update(float tpf) {
//        super.update(tpf);
//        network.update(tpf);
//    }
//    
//    @Override
//    public void cleanup() {
//        network.cleanup();
//        super.cleanup(); 
//    }
//
//    @Override
//    public void changeGameState(GameState newGameState) {
//        super.changeGameState(newGameState);
////        gameState.getGame().addListener(new GameListener() {
////            @Override
////            public void onGameStarted(Game game) {
////                createLanUI();
////            }
////        });
//    }
//
//     /**
//     * 显示角色选择面板
//     * @param selectableActors 
//     */
//    public final void showSelectPanel(List<String> selectableActors) {
//        if (actorPanel == null) {
//            SimpleApplication sapp = (SimpleApplication) app;
//            
//            actorPanel = new ActorSelectView(LuoYing.getSettings().getWidth(), LuoYing.getSettings().getHeight(), sapp.getGuiNode());
//            actorPanel.setSelectedListener(new ActorSelectView.SelectedListener() {
//                @Override
//                public void onSelected(String actorId, String actorName) {
//                    actorPanel.removeFromParent();
//                    actorPanel.getActorView().removeFromParent();
// 
//                    onSelectPlayer(actorId, actorName);
//                }
//            });
//        }
//        actorPanel.setModels(selectableActors);
//        
//        UIState.getInstance().addUI(actorPanel);
////        gameState.addObject(actorPanel.getActorView(), false);
//        
////        CameraChaseEnv cce = SceneUtils.findEnv(gameState.getScene(), CameraChaseEnv.class);
////        if (cce != null) {
////            cce.setChase(actorPanel.getActorView());
////        }
//    }
//    
//    /**
//     * 当玩家在本地选择了一个角色进行游戏后进。
//     * @param actorId
//     * @param actorName
//     */
//    protected abstract void onSelectPlayer(String actorId, String actorName);
//    
//    @Override
//    public boolean isServer() {
//        return network.isServer();
//    }
//    
//    /**
//     * 刷新客户端界面列表
//     */
//    @Override
//    public void onClientListUpdated() {
//        if (clientsWin.isVisible()) {
//            clientsWin.setClients(getClients());
//        }
//    }
//
//    private void createLanUI() {
//         // ---- 联网状态按钮,用于打开局域网客户端列表面板
//        float fullWidth = Ly.getSettings().getWidth();
//        float fullHeight = Ly.getSettings().getHeight();
//        clientsWin = new ClientsWin(this, fullWidth * 0.75f, fullHeight * 0.8f);
//        clientsWin.setToCorner(UI.Corner.CC);
//        clientsWin.setCloseable(true);
//        clientsWin.setDragEnabled(true);
//        clientsWinAnim = new ScaleAnim();
//        clientsWinAnim.setStartScale(0.5f);
//        clientsWinAnim.setEndScale(1f);
//        clientsWinAnim.setRestore(true);
//        clientsWinAnim.setUseTime(0.4f);
//        clientsWinAnim.setLocalScaleOffset(new Vector3f(clientsWin.getWidth() * 0.5f
//                    , clientsWin.getHeight() * 0.5f
//                    , 0));
//        clientsWinAnim.setTarget(clientsWin);
//        clientsWinAnim.addListener(new Listener() {
//            @Override
//            public void onDone(Anim anim) {
//                playService.removeAnimation(anim);
//            }
//        });
//                
//        lanBtn = new Icon("Interface/icon/link.png");
//        lanBtn.setUseAlpha(true);
//        lanBtn.addClickListener(new name.huliqing.luoying.ui.UI.Listener() {
//            @Override
//            public void onClick(UI ui, boolean isPress) {
//                if (isPress) return;
//                displayLanPanel();
//            }
//        });
//        // 把按钮添加到工具栏
//        gameState.getMenuTool().addMenu(lanBtn, 0);
//    }
//    
//    private void displayLanPanel() {
//        if (clientsWin.getParent() == null) {
//            addObject(clientsWin, true);
//            clientsWin.setClients(getClients());
//            playService.addAnimation(clientsWinAnim);
//        } else {
//            removeObject(clientsWin);
//        }
//    } 
//    
//}
