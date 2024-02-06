package cookie.thaumaturgy.mixin;

import com.mojang.nbt.CompoundTag;
import cookie.thaumaturgy.Thaumaturgy;
import cookie.thaumaturgy.interfaces.IEntityManaCharge;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public abstract class EntityPlayerMixin implements IEntityManaCharge {
	@Shadow
	public abstract void addChatMessage(String s);

	@Unique
	private int debug;
	@Unique
	private int airMana = 0;
	@Unique
	private int earthMana = 0;
	@Unique
	private int fireMana = 0;
	@Unique
	private int waterMana = 0;
	@Unique
	private int orderMana = 0;
	@Unique
	private int chaosMana = 0;

	@Override
	public int thaumaturgy_bta$getAir() {
		return airMana;
	}

	@Override
	public int thaumaturgy_bta$getEarth() {
		return earthMana;
	}

	@Override
	public int thaumaturgy_bta$getFire() {
		return fireMana;
	}

	@Override
	public int thaumaturgy_bta$getWater() {
		return waterMana;
	}

	@Override
	public int thaumaturgy_bta$getOrder() {
		return orderMana;
	}

	@Override
	public int thaumaturgy_bta$getChaos() {
		return chaosMana;
	}

	@Override
	public int thaumaturgy_bta$minusAir() {
		return airMana > 0 ? --airMana : 0;
	}

	@Override
	public int thaumaturgy_bta$minusEarth() {
		return earthMana > 0 ? --earthMana : 0;
	}

	@Override
	public int thaumaturgy_bta$minusFire() {
		return fireMana > 0 ? --fireMana : 0;
	}

	@Override
	public int thaumaturgy_bta$minusWater() {
		return waterMana > 0 ? --waterMana : 0;
	}

	@Override
	public int thaumaturgy_bta$minusOrder() {
		return orderMana > 0 ? --orderMana : 0;
	}

	@Override
	public int thaumaturgy_bta$minusChaos() {
		return chaosMana > 0 ? --chaosMana : 0;
	}

	@Override
	public int thaumaturgy_bta$increaseAir() {
		return airMana++;
	}

	@Override
	public int thaumaturgy_bta$increaseEarth() {
		return earthMana++;
	}

	@Override
	public int thaumaturgy_bta$increaseFire() {
		return fireMana++;
	}

	@Override
	public int thaumaturgy_bta$increaseWater() {
		return waterMana++;
	}

	@Override
	public int thaumaturgy_bta$increaseOrder() {
		return orderMana++;
	}

	@Override
	public int thaumaturgy_bta$increaseChaos() {
		return chaosMana++;
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void thaumaturgy_logMana(CallbackInfo ci) {
		if (debug++ >= 200) {
			debug = 0;
			if (airMana > 0) addChatMessage("Player Air: " + airMana);
			if (earthMana > 0) addChatMessage("Player Earth: " + earthMana);
			if (fireMana > 0) addChatMessage("Player Fire: " + fireMana);
			if (waterMana > 0) addChatMessage("Player Water: " + waterMana);
			if (orderMana > 0) addChatMessage("Player Order: " + orderMana);
			if (chaosMana > 0) addChatMessage("Player Chaos: " + chaosMana);
		}
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void thaumaturgy_addMana(CompoundTag tag, CallbackInfo ci) {
		tag.putInt("AirMana", airMana);
		tag.putInt("EarthMana", earthMana);
		tag.putInt("FireMana", fireMana);
		tag.putInt("WaterMana", waterMana);
		tag.putInt("OrderMana", orderMana);
		tag.putInt("ChaosMana", chaosMana);
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void thaumaturgy_readMana(CompoundTag tag, CallbackInfo ci) {
		airMana = tag.getInteger("AirMana");
		earthMana = tag.getInteger("EarthMana");
		fireMana = tag.getInteger("FireMana");
		waterMana = tag.getInteger("WaterMana");
		orderMana = tag.getInteger("OrderMana");
		chaosMana = tag.getInteger("ChaosMana");
	}
}
