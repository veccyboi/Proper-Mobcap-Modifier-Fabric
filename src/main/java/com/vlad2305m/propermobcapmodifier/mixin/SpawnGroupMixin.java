package com.vlad2305m.propermobcapmodifier.mixin;

import com.vlad2305m.propermobcapmodifier.config.ConfigManager;
import com.vlad2305m.propermobcapmodifier.config.SpawnGroupOptions;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobCategory.class)
public class SpawnGroupMixin {
    @Shadow
    @Final
    private String name;

    @Inject(method = "getMaxInstancesPerChunk", at = @At("TAIL"), cancellable = true)
    private void getCapacityHook(CallbackInfoReturnable<Integer> cir) {
        SpawnGroupOptions options = getOptions();
        if (options == null) {
            return;
        }

        cir.setReturnValue(options.getCapacity());
    }

    @Inject(method = "isFriendly", at = @At("TAIL"), cancellable = true)
    private void isPeacefulHook(CallbackInfoReturnable<Boolean> cir) {
        SpawnGroupOptions options = getOptions();
        if (options == null) {
            return;
        }

        cir.setReturnValue(options.isPeaceful());
    }

    @Inject(method = "isPersistent", at = @At("TAIL"), cancellable = true)
    private void isRareHook(CallbackInfoReturnable<Boolean> cir) {
        SpawnGroupOptions options = getOptions();
        if (options == null) {
            return;
        }

        cir.setReturnValue(options.isRare());
    }

    @Inject(method = "getNoDespawnDistance", at = @At("TAIL"), cancellable = true)
    private void getDespawnStartRangeHook(CallbackInfoReturnable<Integer> cir) {
        SpawnGroupOptions options = getOptions();
        if (options == null) {
            return;
        }

        cir.setReturnValue(options.getDespawnStartRange());
    }

    @Inject(method = "getDespawnDistance", at = @At("TAIL"), cancellable = true)
    private void getImmediateDespawnRangeHook(CallbackInfoReturnable<Integer> cir) {
        SpawnGroupOptions options = getOptions();
        if (options == null) {
            return;
        }

        cir.setReturnValue(options.getImmediateDespawnRange());
    }

    private SpawnGroupOptions getOptions() {
        if (!ConfigManager.isConfigUsable()) {
            return null;
        }

        return ConfigManager.getConfig().getSpawnGroups().get(name);
    }
}
