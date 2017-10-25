package mjaroslav.mcmods.mjutils.common.init;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mjaroslav.mcmods.mjutils.MJInfo;
import mjaroslav.mcmods.mjutils.common.anvil.AnvilEventHandler;
import mjaroslav.mcmods.mjutils.common.anvil.AnvilRecipe;
import mjaroslav.mcmods.mjutils.common.anvil.AnvilUtils;
import mjaroslav.mcmods.mjutils.common.config.Config;
import mjaroslav.mcmods.mjutils.common.objects.IModModule;
import mjaroslav.mcmods.mjutils.common.objects.ModInitModule;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@ModInitModule(modid = MJInfo.MODID)
public class AnvilModule implements IModModule {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
	}

	@Override
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new AnvilEventHandler());
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		if (Config.chainToIron) {
			AnvilUtils.instance().addRecipe(new ItemStack(Items.iron_helmet, 1),
					new AnvilRecipe(new ItemStack(Items.chainmail_helmet, 1, 0), new ItemStack(Items.iron_ingot, 1)));
			AnvilUtils.instance().addRecipe(new ItemStack(Items.iron_chestplate, 1), new AnvilRecipe(
					new ItemStack(Items.chainmail_chestplate, 1, 0), new ItemStack(Items.iron_ingot, 1)));
			AnvilUtils.instance().addRecipe(new ItemStack(Items.iron_leggings, 1),
					new AnvilRecipe(new ItemStack(Items.chainmail_leggings, 1, 0), new ItemStack(Items.iron_ingot, 1)));
			AnvilUtils.instance().addRecipe(new ItemStack(Items.iron_boots, 1),
					new AnvilRecipe(new ItemStack(Items.chainmail_boots, 1, 0), new ItemStack(Items.iron_ingot, 1)));
		}
	}

	@Override
	public String getModuleName() {
		return "Anvil";
	}
}
