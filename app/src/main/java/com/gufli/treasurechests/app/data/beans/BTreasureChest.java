package com.gufli.treasurechests.app.data.beans;

import com.gufli.treasurechests.app.data.converters.LocationConverter;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treasure_chests")
public class BTreasureChest extends BModel {

    @Id
    private UUID id;

    @Convert(converter = LocationConverter.class)
    private final Location location;

    @OneToMany(targetEntity = BTreasureLoot.class, mappedBy = "chest", fetch = FetchType.EAGER)
    private List<BTreasureLoot> loot;

    private int respawnTime = 60;

    public BTreasureChest(Location location) {
        this.location = location;
    }

    // getters

    public UUID id() {
        return id;
    }

    public Location location() {
        return location;
    }

    public int respawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }

    // loot

    public List<BTreasureLoot> loot() {
        return loot;
    }

    public BTreasureLoot addLoot(ItemStack item, double chance) {
        BTreasureLoot loot = new BTreasureLoot(this, item, chance);
        this.loot.add(loot);
        return loot;
    }

    public void removeLoot(BTreasureLoot loot) {
        this.loot.remove(loot);
    }

    //

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof BTreasureChest btc) && btc.id.equals(id);
    }
}
