package net.mcreator.oceanicequipment.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

import java.util.Map;

@OceanicEquipmentModElements.ModElement.Tag
public class WhenplayerhasgotachievmentminecraftnetherrootProcedure extends OceanicEquipmentModElements.ModElement {
	public WhenplayerhasgotachievmentminecraftnetherrootProcedure(OceanicEquipmentModElements instance) {
		super(instance, 31);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure Whenplayerhasgotachievmentminecraftnetherroot!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof ServerPlayerEntity) && (entity.world instanceof ServerWorld))
				? ((ServerPlayerEntity) entity).getAdvancements()
						.getProgress(((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
								.getAdvancement(new ResourceLocation("minecraft:nether/root")))
						.isDone()
				: false)) {
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(new StringTextComponent("Nether and water are incompatible!"));
			}
		}
	}
}
