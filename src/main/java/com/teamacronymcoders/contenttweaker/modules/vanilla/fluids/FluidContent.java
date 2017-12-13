package com.teamacronymcoders.contenttweaker.modules.vanilla.fluids;

import com.teamacronymcoders.contenttweaker.api.utils.CTUtils;
import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidContent extends Fluid {
    private FluidRepresentation fluidRepresentation;

    public FluidRepresentation(String unlocalizedName, String color) {
        this.unlocalizedName = unlocalizedName;
        
        int thickshaft = (int)(color.chars().filter(ch -> ch =='=').count())*32;
        int thinshaft = (int)(color.chars().filter(ch -> ch =='-').count())*32;
        int juice = (int)(color.chars().filter(ch -> ch =='~').count())*32;
        
        String hexy = "FF";
        hexy += thickshaft==0?"00":Integer.toHexString(thickshaft);
        hexy += thinshaft==0?"00":Integer.toHexString(thinshaft);
        hexy += juice==0?"00":Integer.toHexString(juice);
        color = hexy;
        if(color.length() == 6)
            color = "FF" + color;
        int intFromHex = (int) Long.parseLong(color, 16);
        this.color = intFromHex;
    }

    private void setValues() {
        this.setDensity(this.fluidRepresentation.getDensity());
        this.setLuminosity(this.fluidRepresentation.getLuminosity());
        this.setEmptySound(this.fluidRepresentation.getEmptySound().getInternal());
        this.setFillSound(this.fluidRepresentation.getFillSound().getInternal());
        this.setGaseous(this.fluidRepresentation.isGaseous());
        this.setViscosity(this.fluidRepresentation.getViscosity());
        this.setRarity(CTUtils.getEnum(this.fluidRepresentation.getRarity(), EnumRarity.class));
        this.setTemperature(this.fluidRepresentation.getTemperature());
    }

    @Override
    public int getColor() {
        return this.fluidRepresentation.isColorize() ? this.fluidRepresentation.getColor() : super.getColor();
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack) {
        return this.fluidRepresentation.isVaporize();
    }
}
