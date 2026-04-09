package fr.dialogue.azplugin.bungee.patch;

import static fr.dialogue.azplugin.common.AZPlatform.log;

import fr.dialogue.azplugin.bungee.agent.Main;
import fr.dialogue.azplugin.common.utils.agent.AgentSupport;
import java.util.logging.Level;
import net.md_5.bungee.api.plugin.Plugin;

public class AZBungeePatchPlugin extends Plugin {

    @Override
    public void onLoad() {
        try {
            AgentSupport.assertAgentLoaded(Main.class);
            log(Level.INFO, "Loaded!");
        } catch (Throwable ex) {
            throw AgentSupport.handleFatalError(ex, AgentSupport::doShutdown);
        }
    }

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new HandshakePatch());
        getLogger().info("Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled!");
    }
}
