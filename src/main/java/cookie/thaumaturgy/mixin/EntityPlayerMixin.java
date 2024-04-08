package cookie.thaumaturgy.mixin;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityPlayer.class, remap = false)
public class EntityPlayerMixin {
	//TODO: Save research :)))

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void saveExtraData(CompoundTag tag, CallbackInfo ci) {

	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void readExtraData(CompoundTag tag, CallbackInfo ci) {

	}
}
