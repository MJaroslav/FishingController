package mjaroslav.mcmods.mjutils.common.config;

import mjaroslav.mcmods.mjutils.MJInfo;
import mjaroslav.mcmods.mjutils.MJUtils;
import mjaroslav.mcmods.mjutils.common.objects.ConfigurationBase;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

/**
 * Configuration for MJUtils mod
 *
 * @author MJaroslav
 */
public class MJUtilsConfig extends ConfigurationBase {
    /**
     * Category for server settings and those that require restarting the game.
     */
    public static final String CATEGORY_COMMON = "common";
    /**
     * Category for client settings and those that not require restarting the
     * game.
     */
    public static final String CATEGORY_CLIENT = "client";
    /**
     * Change chain mail armor to iron by iron ingot on anvil.
     */
    public static boolean chainToIron = true;

    // COMMON FIELDS
    /**
     * Change leather armor to chain mail by 2 iron bars on anvil.
     */
    public static boolean leatherToChain = true;
    /**
     * Show ore dictionary names in tooltip (in advanced tooltip mode).
     */
    public static boolean showOreDict = true;
    // CLIENT FIELDS
    /**
     * Show ore dictionary names always.
     */
    public static boolean showOreDictAlways = true;
    /**
     * Instance of this configuration
     */
    private Configuration instance;

    @Override
    public void readFields() {
        chainToIron = getInstance().getBoolean("chain_to_iron", CATEGORY_COMMON, true,
                "Change chainmail armor to iron by iron ingot on anvil.");
        leatherToChain = getInstance().getBoolean("leather_to_chain", CATEGORY_COMMON, true,
                "Change leather armor to chain mail by 2 iron bars on anvil.");
        showOreDict = getInstance().getBoolean("show_oredict", CATEGORY_CLIENT, true,
                "Show ore dict names in tooltip (in advanced tooltip mode).");
        showOreDictAlways = getInstance().getBoolean("show_oredict_always", CATEGORY_CLIENT, false,
                "Show ore dict names always.");
    }

    @Override
    public String getModId() {
        return MJInfo.MODID;
    }

    @Override
    public Logger getLogger() {
        return MJUtils.LOG;
    }

    @Override
    public Configuration getInstance() {
        return this.instance;
    }

    @Override
    public void setInstance(Configuration newConfig) {
        this.instance = newConfig;
    }
}
