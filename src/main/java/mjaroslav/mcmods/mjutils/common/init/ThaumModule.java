package mjaroslav.mcmods.mjutils.common.init;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import mjaroslav.mcmods.mjutils.MJInfo;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import mjaroslav.mcmods.mjutils.common.thaum.ThaumEventHandler;

@ModInitModule(modid = MJInfo.MODID)
public class ThaumModule implements IModModule {
  @Override
  public String getModuleName() {
    return "Thaum";
  }

  @Override
  public int getPriority() {
    return 4;
  }

  @Override
  public void preInit(FMLPreInitializationEvent event) {
  }

  @Override
  public void init(FMLInitializationEvent event) {
    FMLCommonHandler.instance().bus().register(new ThaumEventHandler());
  }

  @Override
  public void postInit(FMLPostInitializationEvent event) {
  }

  @Override
  public String[] modDependencies() {
    return new String[] { "Thaumcraft" };
  }

  @Override
  public boolean canLoad() {
    return true;
  }
}
