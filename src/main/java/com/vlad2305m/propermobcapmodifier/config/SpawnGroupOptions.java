package com.vlad2305m.propermobcapmodifier.config;

import net.minecraft.world.entity.MobCategory;

public class SpawnGroupOptions {
    private int capacity = 0;
    private boolean peaceful = false;
    private boolean rare = false;
    private int despawnStartRange = 32;
    private int immediateDespawnRange = 128;

    public SpawnGroupOptions() {
    }

    public SpawnGroupOptions(int capacity, boolean peaceful, boolean rare, int despawnStartRange, int immediateDespawnRange) {
        this.capacity = capacity;
        this.peaceful = peaceful;
        this.rare = rare;
        this.despawnStartRange = despawnStartRange;
        this.immediateDespawnRange = immediateDespawnRange;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isPeaceful() {
        return peaceful;
    }

    public boolean isRare() {
        return rare;
    }

    public int getDespawnStartRange() {
        return despawnStartRange;
    }

    public int getImmediateDespawnRange() {
        return immediateDespawnRange;
    }

    public static SpawnGroupOptions from(MobCategory spawnGroup) {
        return new SpawnGroupOptions(
                spawnGroup.getMaxInstancesPerChunk(),
                spawnGroup.isFriendly(),
                spawnGroup.isPersistent(),
                spawnGroup.getNoDespawnDistance(),
                spawnGroup.getDespawnDistance()
        );
    }
}
