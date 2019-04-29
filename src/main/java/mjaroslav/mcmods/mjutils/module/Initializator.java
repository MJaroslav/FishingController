package mjaroslav.mcmods.mjutils.module;

import cpw.mods.fml.common.event.*;

public interface Initializator {
    default void preInit(FMLPreInitializationEvent event) {}
    default void init(FMLInitializationEvent event) {}
    default void postInit(FMLPostInitializationEvent event) {}
    default void construct(FMLConstructionEvent event) {}

    interface Configurable extends Initializator {}
}
