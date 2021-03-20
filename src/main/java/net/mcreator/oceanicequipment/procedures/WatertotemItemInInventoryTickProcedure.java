package net.mcreator.oceanicequipment.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.oceanicequipment.OceanicEquipmentModElements;

import java.util.Map;

@OceanicEquipmentModElements.ModElement.Tag
public class WatertotemItemInInventoryTickProcedure extends OceanicEquipmentModElements.ModElement {
	public WatertotemItemInInventoryTickProcedure(OceanicEquipmentModElements instance) {
		super(instance, 25);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure WatertotemItemInInventoryTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.isInWater())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, (int) 20, (int) 1, (false), (false)));
		}
	}
}
